#################### BUILD STEP
FROM maven:3.6.1-jdk-8 as builder

#Working directory
WORKDIR /usr/src

#Copy project
COPY . .

#Check content
RUN ls /usr/src

#Package
RUN mvn package -DskipTests

################### RUN STP

FROM java:8-jre-alpine

#Create destination folder
RUN mkdir /destination/logs/archived


RUN mkdir -p /destination/logs/archived

RUN mkdir -p /destination/files/cvs

RUN mkdir -p /destination/files/avatars

ENV LOGBACK_LOG_DIRECTORY = /destination/logs

#Copy package
COPY --from=builder /usr/src/target/condidat-0.0.1-SNAPSHOT.jar /destination/condidat.jar

RUN ls /destination/app.jar

#Run app
ENTRYPOINT ["java","-jar","/destination/condidat.jar","--spring.profiles.active=azure"]
