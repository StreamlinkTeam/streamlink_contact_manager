package cl.streamlink.contact.utils;


import cl.streamlink.contact.web.dto.hireability.Results;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

public class TestClass {


    public static void main(String[] args) throws IOException {

//        MultiValueMap<String, Object> parts =
//                new LinkedMultiValueMap<>();
//        parts.add("document", new File("C:\\ldk\\cv","CHEMAKH_Lazher_Ingénieur Informatique __FR.docx"));
//        parts.add("product_code", "e11adbba5cffb9a8b529097145a2a86f");
//
//
        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity =
//                new HttpEntity<>(parts, headers);
//
//        ResponseEntity<String> response =
//                restTemplate.exchange("http://processing.resumeparser.com/requestprocessing.html",
//                        HttpMethod.POST, requestEntity, String.class);
//
//        System.out.println(response.getBody());

//        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
//        map.add("document", new FileSystemResource(new File("C:\\ldk\\cv", "CHEMAKH_Lazher_Ingénieur Informatique __FR.docx")));
//        map.add("product_code", "e11adbba5cffb9a8b529097145a2a86f");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
//                map, headers);
//        ResponseEntity<String> result = restTemplate.exchange(
//                "http://processing.resumeparser.com/requestprocessing.html",
//                HttpMethod.POST, requestEntity, String.class);


//        System.out.println(result.getBody());

        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Results value = xmlMapper.readValue(new File("C:\\Users\\Utilisateur\\Desktop", "json.xml"),
                Results.class);


        System.err.println(value);
        String xml = xmlMapper.writeValueAsString(new Results());

        System.err.println(xml);
//        String output = null;
//
//        // Note: You can also replace the 3 keys below with:
//        // formParam("product_code", "Your PRODUCT CODE").
//        Response r = given().
//                formParam("product_code", "e11adbba5cffb9a8b529097145a2a86f").
//                formParam("document_title", "Something.docx").
//                multiPart("document", new File("C:\\ldk\\cv","CHEMAKH_Lazher_Ingénieur Informatique __FR.docx")).
//                when().
//                post("http://processing.resumeparser.com/requestprocessing.html");
//
//        output = r.getBody().asString();
//        System.out.println(output);
    }
}
