package cl.streamlink.contact.utils;

import cl.streamlink.contact.domain.*;
import gate.*;
import gate.util.GateException;
import gate.util.Out;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import static gate.Utils.stringFor;

public class ResumeParserService {

    private static final String UNDEFINED = "UNDEFINED";

    private static File parseToHTMLUsingApacheTikka(String file)
            throws IOException, SAXException, TikaException {
        // determine extension
        String ext = FilenameUtils.getExtension(file);
        String outputFileFormat = "";
        // ContentHandler handler;
        if (ext.equalsIgnoreCase("html") | ext.equalsIgnoreCase("pdf")
                | ext.equalsIgnoreCase("doc") | ext.equalsIgnoreCase("docx")) {
            outputFileFormat = ".html";
            // handler = new ToXMLContentHandler();
        } else if (ext.equalsIgnoreCase("txt") | ext.equalsIgnoreCase("rtf")) {
            outputFileFormat = ".txt";
        } else {
            Out.prln("Input format of the file " + file
                    + " is not supported.");
            return null;
        }
        String OUTPUT_FILE_NAME = FilenameUtils.removeExtension(file)
                + outputFileFormat;
        ContentHandler handler = new ToXMLContentHandler();
        // ContentHandler handler = new BodyContentHandler();
        // ContentHandler handler = new BodyContentHandler(
        // new ToXMLContentHandler());
        InputStream stream = new FileInputStream(file);
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try {
            parser.parse(stream, handler, metadata);
            FileWriter htmlFileWriter = new FileWriter(OUTPUT_FILE_NAME);
            htmlFileWriter.write(handler.toString());
            htmlFileWriter.flush();
            htmlFileWriter.close();
            return new File(OUTPUT_FILE_NAME);
        } finally {
            stream.close();
        }
    }

    public static JSONObject loadGateAndAnnie(File file) throws GateException,
            IOException, URISyntaxException {
        Out.prln("Initialising basic system...");

        if (!Gate.isInitialised()) {
            Gate.setGateHome(new File("C:\\ldk\\streamlink_condidats_manager\\src\\main\\resources\\GATEFiles"));
            Gate.setPluginsHome(new File("C:\\ldk\\streamlink_condidats_manager\\src\\main\\resources\\GATEFiles\\plugins"));
            Gate.init();
        }

        Out.prln("...basic system initialised");

        // initialise ANNIE (this may take several minutes)
        Annie annie = new Annie();
        annie.initAnnie();

        // create a GATE corpus and add a document for each command-line
        // argument
        Corpus corpus = Factory.newCorpus("Annie corpus");

        URL u = file.toURI().toURL();
        FeatureMap params = Factory.newFeatureMap();
        params.put("sourceUrl", u);
        params.put("preserveOriginalContent", new Boolean(true));
        params.put("collectRepositioningInfo", new Boolean(true));
        Out.prln("Creating doc for " + u);
        Document resume = (Document) Factory.createResource(
                "gate.corpora.DocumentImpl", params);
        corpus.add(resume);

        // tell the pipeline about the corpus and run it
        annie.setCorpus(corpus);
        annie.execute();

        Iterator iter = corpus.iterator();
        JSONObject parsedJSON = new JSONObject();
        Out.prln("Started parsing...");
        // while (iter.hasNext()) {
        if (iter.hasNext()) { // should technically be while but I am just
            // dealing with one document
            JSONObject profileJSON = new JSONObject();
            Document doc = (Document) iter.next();
            AnnotationSet defaultAnnotSet = doc.getAnnotations();

            AnnotationSet curAnnSet;
            Iterator it;
            Annotation currAnnot;

            // Name
            curAnnSet = defaultAnnotSet.get("NameFinder");
            if (curAnnSet.iterator().hasNext()) { // only one name will be
                // found.
                currAnnot = curAnnSet.iterator().next();
                String gender = (String) currAnnot.getFeatures().get("gender");
                if (gender != null && gender.length() > 0) {
                    profileJSON.put("gender", gender);
                }

                // Needed name Features
                JSONObject nameJson = new JSONObject();
                String[] nameFeatures = new String[]{"firstName",
                        "middleName", "surname"};
                for (String feature : nameFeatures) {
                    String s = (String) currAnnot.getFeatures().get(feature);
                    if (s != null && s.length() > 0) {
                        nameJson.put(feature, s);
                    }
                }
                profileJSON.put("name", nameJson);
            } // name

            // title
            curAnnSet = defaultAnnotSet.get("TitleFinder");
            if (curAnnSet.iterator().hasNext()) { // only one title will be
                // found.
                currAnnot = (Annotation) curAnnSet.iterator().next();
                String title = stringFor(doc, currAnnot);
                if (title != null && title.length() > 0) {
                    profileJSON.put("title", title);
                }
            }// title

            // email,address,phone,url
            String[] annSections = new String[]{"EmailFinder",
                    "AddressFinder", "PhoneFinder", "URLFinder"};
            String[] annKeys = new String[]{"email", "address", "phone",
                    "url"};
            for (short i = 0; i < annSections.length; i++) {
                String annSection = annSections[i];
                curAnnSet = defaultAnnotSet.get(annSection);
                it = curAnnSet.iterator();
                JSONArray sectionArray = new JSONArray();
                while (it.hasNext()) { // extract all values for each
                    // address,email,phone etc..
                    currAnnot = (Annotation) it.next();
                    String s = stringFor(doc, currAnnot);
                    if (s != null && s.length() > 0) {
                        sectionArray.add(s);
                    }
                }
                if (sectionArray.size() > 0) {
                    profileJSON.put(annKeys[i], sectionArray);
                }
            }
            if (!profileJSON.isEmpty()) {
                parsedJSON.put("basics", profileJSON);
            }

            // awards,credibility,education_and_training,extracurricular,misc,skills,summary
            String[] otherSections = new String[]{"summary",
                    "education_and_training", "skills", "accomplishments",
                    "awards", "credibility", "extracurricular", "misc"};
            for (String otherSection : otherSections) {
                curAnnSet = defaultAnnotSet.get(otherSection);
                it = curAnnSet.iterator();
                JSONArray subSections = new JSONArray();
                while (it.hasNext()) {
                    JSONObject subSection = new JSONObject();
                    currAnnot = (Annotation) it.next();
                    String key = (String) currAnnot.getFeatures().get(
                            "sectionHeading");
                    String value = stringFor(doc, currAnnot);
                    if (!StringUtils.isBlank(key)
                            && !StringUtils.isBlank(value)) {
                        subSection.put(key, value);
                    }
                    if (!subSection.isEmpty()) {
                        subSections.add(subSection);
                    }
                }
                if (!subSections.isEmpty()) {
                    parsedJSON.put(otherSection, subSections);
                }
            }

            // work_experience
            curAnnSet = defaultAnnotSet.get("work_experience");
            it = curAnnSet.iterator();
            JSONArray workExperiences = new JSONArray();
            while (it.hasNext()) {
                JSONObject workExperience = new JSONObject();
                currAnnot = (Annotation) it.next();
                String key = (String) currAnnot.getFeatures().get(
                        "sectionHeading");
                if (key.equals("work_experience_marker")) {
                    // JSONObject details = new JSONObject();
                    String[] annotations = new String[]{"date_start",
                            "date_end", "jobtitle", "organization"};
                    for (String annotation : annotations) {
                        String v = (String) currAnnot.getFeatures().get(
                                annotation);
                        if (!StringUtils.isBlank(v)) {
                            // details.put(annotation, v);
                            workExperience.put(annotation, v);
                        }
                    }
                    // if (!details.isEmpty()) {
                    // workExperience.put("work_details", details);
                    // }
                    key = "text";

                }
                String value = stringFor(doc, currAnnot);
                if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                    workExperience.put(key, value);
                }
                if (!workExperience.isEmpty()) {
                    workExperiences.add(workExperience);
                }

            }
            if (!workExperiences.isEmpty()) {
                parsedJSON.put("work_experience", workExperiences);
            }

        }// if
        Out.prln("Completed parsing...");
        return parsedJSON;
    }

    public static Developer loadDeveloperFromJson(JSONObject data) {

        Developer developer = new Developer();

        JSONObject basicsJson = (JSONObject) data.getOrDefault("basics", new JSONObject());
        JSONObject nameJson = (JSONObject) basicsJson.getOrDefault("name", new JSONObject());

        JSONArray skillsJson = (JSONArray) data.getOrDefault("skills", new JSONArray());
        JSONArray educationJson = (JSONArray) data.getOrDefault("education_and_training", new JSONArray());


        developer.setGender(Gender.fromString((String) basicsJson.getOrDefault("gender", UNDEFINED)));
        developer.setFirstname((String) nameJson.getOrDefault("firstName", UNDEFINED));
        developer.setLastname((String) nameJson.getOrDefault("surname", UNDEFINED));
        developer.setStage(Stage.ToTreat);

        Contact contact = new Contact();
        if (!((JSONArray) basicsJson.getOrDefault("email", new JSONArray())).isEmpty())
            contact.setEmail1((String) ((JSONArray) basicsJson.getOrDefault("email", new JSONArray())).get(0));
        if (!((JSONArray) basicsJson.getOrDefault("phone", new JSONArray())).isEmpty())
            contact.setTel1((String) ((JSONArray) basicsJson.getOrDefault("phone", new JSONArray())).get(0));
        if (!((JSONArray) basicsJson.getOrDefault("address", new JSONArray())).isEmpty())
            contact.setAddress((String) ((JSONArray) basicsJson.getOrDefault("address", new JSONArray())).get(0));
        if (!((JSONArray) basicsJson.getOrDefault("url", new JSONArray())).isEmpty())
            contact.setWebsite((String) ((JSONArray) basicsJson.getOrDefault("url", new JSONArray())).get(0));

        SkillsInformation skillsInformation = new SkillsInformation();
        skillsInformation.setTitle(basicsJson.getAsString("title"));
        skillsInformation.setFormation(Formation.NOT_DEFINED);
        skillsInformation.setExperience(Experience.NOT_DEFINED);

        skillsInformation.setQualifications(!educationJson.isEmpty() ?
                educationJson.stream()
                        .map(val -> ((JSONObject) val))
                        .filter(val -> !val.isEmpty())
                        .map(val -> ((String) val.get(val.keySet().iterator().next())))
                        .collect(Collectors.toList()) : new ArrayList<>());

        skillsInformation.setLanguages(!skillsJson.isEmpty() ?
                skillsJson.stream()
                        .map(val -> ((JSONObject) val))
                        .filter(val -> !val.isEmpty())
                        .map(val -> ((String) val.get(val.keySet().iterator().next())))
                        .collect(Collectors.joining(",")) : UNDEFINED);

        developer.setContact(contact);
        developer.setSkillsInformation(skillsInformation);

        return developer;
    }

    public static Developer parseResume(String inputFileName) {
        try {
            File tikkaConvertedFile = parseToHTMLUsingApacheTikka(inputFileName);
            if (tikkaConvertedFile != null) {
                JSONObject parsedJSON = loadGateAndAnnie(tikkaConvertedFile);

                return loadDeveloperFromJson(parsedJSON);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Out.prln("Sad Face :( .Something went wrong.");
            e.printStackTrace();
        }

        return null;

    }

    public static void main(String[] args) {


        String inputFileName = "C:\\ldk\\cv\\CHEMAKH_Lazher_Ingénieur Informatique __FR.pdf";
        String outputFileName = "C:\\ldk\\cv\\parsed_resume.json";

        try {
            File tikkaConvertedFile = parseToHTMLUsingApacheTikka(inputFileName);
            if (tikkaConvertedFile != null) {
                JSONObject parsedJSON = loadGateAndAnnie(tikkaConvertedFile);

                Out.prln("Writing to output...");
                FileWriter jsonFileWriter = new FileWriter(outputFileName);
                jsonFileWriter.write(parsedJSON.toJSONString());
                jsonFileWriter.flush();
                jsonFileWriter.close();
                Out.prln("Output written to file " + outputFileName);

                System.out.println(loadDeveloperFromJson(parsedJSON));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Out.prln("Sad Face :( .Something went wrong.");
            e.printStackTrace();
        }
    }
}
