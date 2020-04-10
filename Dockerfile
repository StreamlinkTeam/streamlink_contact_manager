#################### BUILD STEP
FROM maven:3.6.1-jdk-8 as builder

#Working directory
WORKDIR /usr/src

#Copy project
COPY . .

#Check content
RUN ls /usr/src

#Package
RUN mvn package -DskipTests -Pazure

################### RUN STP

FROM java:8-jre-alpine

#Create destination folder
RUN mkdir -p /destination/logs/archived

RUN mkdir -p /destination/files/cvs

RUN mkdir -p /destination/files/avatars

ENV LOGBACK_LOG_DIRECTORY = /destination/logs

#Copy package
COPY --from=builder /usr/src/target/streamlink_condidats_manger-azure.jar /destination/streamlink_condidats_manger-azure.jar

RUN ls /destination/streamlink_condidats_manger-azure.jar

#Run app
ENTRYPOINT ["java","-DLOGBACK_LOG_DIRECTORY=/destination/logs","-jar","/destination/streamlink_condidats_manger-azure.jar","--spring.profiles.active=azure"]
EXPOSE 9091
