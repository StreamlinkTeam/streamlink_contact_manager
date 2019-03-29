package cl.streamlink.contact.utils;


import java.io.IOException;

public class TestClass {


//    private static Developer loadDeveloperFromHireAbility(HireAbilityJSONResults resume) {
//
//        Developer developer = new Developer();
//
//
//        developer.setGender(Gender.fromString(resume.getGender()));
//        developer.setFirstname(resume.getGivenName());
//        developer.setLastname(resume.getFamilyName());
//        developer.setStage(Stage.ToTreat);
//
//        Contact contact = new Contact();
//
//        contact.setEmail1(resume.getEmails().stream().findFirst().orElse(new HireAbilityContact()).getValue());
//
//        if (resume.getEmails().size() >= 2)
//            contact.setEmail2(resume.getEmails().get(1).getValue());
//
//        if (resume.getEmails().size() >= 3)
//            contact.setEmail3(resume.getEmails().get(2).getValue());
//
//        contact.setTel1(resume.getPhones().stream().findFirst().orElse(new HireAbilityContact()).getValue());
//
//        if (resume.getPhones().size() >= 2)
//            contact.setTel2(resume.getPhones().get(1).getValue());
//
//        if (resume.getPhones().size() >= 3)
//            contact.setTel3(resume.getPhones().get(2).getValue());
//
//        contact.setWebsite(resume.getWebsites().stream().findFirst().orElse(new HireAbilityContact()).getValue());
//
//        HireAbilityAddress address = resume.getAddresses().stream().findFirst().orElse(null);
//
//        if (address != null) {
//            contact.setAddress(address.getAddressLine());
//            contact.setCity(address.getCityName());
//            contact.setNpa(address.getPostalCode());
//
//            Locale obj = new Locale("", address.getCountryCode());
//
//            contact.setCountry(address.getCountryCode());
//        }
//
//        PersonalInformation personalInformation = new PersonalInformation();
//        personalInformation.setBirthDate(resume.getDateOfBirth());
//        personalInformation.setFamilySituation(FamilySituation.fromString(resume.getFamilySituation()));
//
//
//        SkillsInformation skillsInformation = new SkillsInformation();
//        skillsInformation.setTitle(resume.getPositionHistories().stream().findFirst()
//                .orElse(new HireAbilityPositionHistory()).getPositionTitle());
//        skillsInformation.setFormation(Formation.NOT_DEFINED);
//        skillsInformation.setExperience(Experience.NOT_DEFINED);
////
//        skillsInformation.setQualifications(!resume.getEducationOrganizations().isEmpty() ?
//                resume.getEducationOrganizations().stream()
//                        .flatMap(education ->education.getDegrees().stream())
//                        .filter(Objects::nonNull)
//                        .map(HireAbilityDegree::getName)
//                        .collect(Collectors.toList()) : new ArrayList<>());
//
//        skillsInformation.getQualifications().addAll(!resume.getCertifications().isEmpty() ?
//                resume.getCertifications().stream()
//                        .filter(Objects::nonNull)
//                        .map(HireAbilityDegree::getName)
//                        .collect(Collectors.toList()) : new ArrayList<>());
//
//        skillsInformation.getQualifications().addAll(!resume.getLicences().isEmpty() ?
//                resume.getLicences().stream()
//                        .filter(Objects::nonNull)
//                        .map(HireAbilityDegree::getName)
//                        .collect(Collectors.toList()) : new ArrayList<>());
//
//        skillsInformation.setLanguages(!resume.getCompetencies().isEmpty() ?
//                resume.getCompetencies().stream()
//                        .map(HireabilityCompetency::getCompetencyName)
//                        .collect(Collectors.joining(",")) : "UNDEFINED");
//
//        developer.setContact(contact);
//        developer.setPersonalInformation(personalInformation);
//        developer.setSkillsInformation(skillsInformation);
//
//        return developer;
//    }

    public static void main(String[] args) throws IOException {


        //System.err.println(new Date(1536255949));
        // System.err.println(new Date().getTime());
//        MultiValueMap<String, Object> parts =
//                new LinkedMultiValueMap<>();
//        parts.add("document", new File("C:\\ldk\\cv","CHEMAKH_Lazher_Ingénieur Informatique __FR.docx"));
//        parts.add("product_code", "e11adbba5cffb9a8b529097145a2a86f");
//
//
        //       RestTemplate restTemplate = new RestTemplate();
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


//        System.out.println(result.getBody()); ObjectMapper xmlMapper = new ObjectMapper();
//////        xmlMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
//////        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////        Results value = xmlMapper.readValue(new File("C:\\Users\\Utilisateur\\Desktop", "hireability_fr.json"),
////                Results.class);
////
////
////        System.err.println(value);
////
////        Developer dev = loadDeveloperFromHireAbility(value.getResults().stream().flatMap(result -> result.getHireAbilityJSONResults().stream()).filter(HireAbilityJSONResults::isNotEmpty)
////                .findFirst().orElse(new HireAbilityJSONResults()));
////        System.err.println(dev);

//

//
    }
}

