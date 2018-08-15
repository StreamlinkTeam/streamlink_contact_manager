package cl.streamlink.contact.utils;


import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.service.SocietyContactService;
import cl.streamlink.contact.service.SocietyService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.web.dto.*;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.Lob;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class FakerService {

    private final static Logger logger = LoggerFactory.getLogger(FakerService.class);


    @Inject
    private DeveloperService developerService;

    @Inject
    private SocietyService societyService;

    @Inject
    private SocietyContactService societyContactService;

    @Inject
    private UserService userService;

    private Faker faker = new Faker(new Locale("fr", "fr"));

    public void generateFakerSocietyData(Integer societySize,Integer societyContactSize) {

        List<UserDTO> users = userService.getAllUsers();

        for (Integer i = 0; i < societySize; i++) {

            SocietyDTO society = new SocietyDTO();

            society.setLabel(faker.company().name());
            society.setManagerReference(users.get(faker.number().numberBetween(0, users.size())).getReference());
            society.setNote(faker.lorem().paragraph());
            society.setStaffNumber(faker.number().numberBetween(5,500));
            society.setSupplierNumber(faker.code().asin());
            society.setStage(SocietyStage.values()[faker.number().numberBetween(0, SocietyStage.values().length)]);
            society.setActivityArea(SocietyActivityArea.values()[faker.number().numberBetween(0, SocietyActivityArea.values().length)]);
            society.setServices(Arrays.asList(faker.job().keySkills(),
                    faker.job().keySkills(), faker.job().keySkills()));

            SocietyResponseDTO societyResponse = societyService.createSociety(society);

            ContactDTO contact = generateContact(societyResponse.getReference()) ;

            societyService.updateSocietyContact(contact, societyResponse.getReference());

            SocietyLegalInformationDTO legalInformation = new SocietyLegalInformationDTO();

            legalInformation.setSocietyReference(societyResponse.getReference());
            legalInformation.setAPECode(faker.code().asin());
            legalInformation.setLegalStatus(faker.code().asin());
            legalInformation.setRCS(faker.code().asin());
            legalInformation.setSiret(faker.code().asin());
            legalInformation.setTVA(faker.code().asin());

            societyService.updateSocietyLegalInformation(legalInformation, societyResponse.getReference());

            for (Integer j = 0; j < societyContactSize; j++) {
                SocietyContactDTO societyContactDTO = new SocietyContactDTO();

                societyContactDTO.setFirstname(faker.name().firstName());
                societyContactDTO.setLastname(faker.name().lastName());
                societyContactDTO.setGender(Gender.values()[i % 2]);
                societyContactDTO.setManagerReference(users.get(faker.number().numberBetween(0, users.size())).getReference());
                societyContactDTO.setNote(faker.lorem().paragraph());
                societyContactDTO.setStage(SocietyStage.values()[faker.number().numberBetween(0, SocietyStage.values().length)]);
                societyContactDTO.setFunction(faker.job().title());

                societyContactDTO.setTechnicalScope(String.join(", ", faker.job().keySkills(),
                        faker.job().keySkills(), faker.job().keySkills()));

                societyContactDTO.setFunctionalScope(String.join(", ", faker.job().keySkills(),
                        faker.job().keySkills(), faker.job().keySkills()));

                societyContactDTO.setSocietyReference(societyResponse.getReference());


                societyContactDTO  = societyContactService.createSocietyContact
                        (societyContactDTO,societyResponse.getReference());

                ContactDTO cont = generateContact(societyContactDTO.getReference());

                societyContactService.updateSocietyContactContact(cont, societyContactDTO.getReference(),societyResponse.getReference());
            }

        }
    }

    public void deleteAll() {

        developerService.getDevelopers(null)
                .forEach(developerResponseDTO -> developerService.deleteDeveloper(developerResponseDTO.getReference()));

        societyService.getSocieties(null)
                .forEach(societyResponseDTO -> societyService.deleteSociety(societyResponseDTO.getReference()));

    }

    public void generateFakerDeveloperData(Integer size) {

        List<UserDTO> users = userService.getAllUsers();


        for (Integer i = 0; i < size; i++) {

            DeveloperDTO dev = new DeveloperDTO();

            dev.setAvailability(LocalDate.now().plusMonths(faker.number().numberBetween(2, 18)));
            dev.setFirstname(faker.name().firstName());
            dev.setLastname(faker.name().lastName());
            dev.setGender(Gender.values()[i % 2]);
            dev.setManagerReference(users.get(faker.number().numberBetween(0, users.size())).getReference());
            dev.setRhReference(users.get(faker.number().numberBetween(0, users.size())).getReference());
            dev.setNote(faker.lorem().paragraph());
            dev.setStage(Stage.values()[faker.number().numberBetween(0, 6)]);

            dev.setMobility(Arrays.asList(faker.address().country(), faker.address().country(), faker.address().country()));

            DeveloperResponseDTO developer = developerService.createDeveloper(dev);

            ContactDTO contact = generateContact(developer.getReference());

            developerService.updateDeveloperContact(contact, developer.getReference());

            PersonalInformationDTO information = new PersonalInformationDTO();

            information.setDeveloperReference(developer.getReference());
            information.setBirthDate(LocalDate.now().minusYears(faker.number().numberBetween(25, 40)));
            information.setFamilySituation(FamilySituation.values()[faker.number().numberBetween(0, 6)]);
            information.setNationality(faker.address().country());
            information.setSocialSecurityNumber(faker.number().digits(6));

            developerService.updateDeveloperPersonalInformation(information, developer.getReference());

            SkillsInformationDTO skills = new SkillsInformationDTO();

            skills.setDeveloperReference(developer.getReference());
            skills.setTitle(faker.job().title());
            skills.setExperience(Experience.values()[faker.number().numberBetween(0, Experience.values().length-1)]);
            skills.setFormation(Formation.values()[faker.number().numberBetween(0, Formation.values().length-1)]);

            skills.setQualifications(Arrays.asList(faker.educator().university(),
                    faker.educator().university(), faker.educator().university()));

            skills.setLanguages(String.join(", ", faker.job().keySkills(),
                    faker.job().keySkills(), faker.job().keySkills()));


            developerService.updateDeveloperSkills(skills, developer.getReference());

        }
    }

    private ContactDTO generateContact(String ownerReference)
    {
        ContactDTO contact = new ContactDTO();

        contact.setOwnerReference(ownerReference);

        contact.setEmail1(new Faker(new Locale("en")).internet().emailAddress());
        contact.setEmail2(new Faker(new Locale("en")).internet().emailAddress());
        contact.setEmail3(new Faker(new Locale("en")).internet().emailAddress());

        contact.setTel1(faker.phoneNumber().phoneNumber());
        contact.setTel2(faker.phoneNumber().phoneNumber());
        contact.setTel3(faker.phoneNumber().phoneNumber());
        contact.setFax(faker.phoneNumber().phoneNumber());
        contact.setWebsite(faker.internet().url());


        contact.setAddress(faker.address().streetAddress());
        contact.setCity(faker.address().city());
        contact.setCountry(faker.address().country());
        contact.setNpa(faker.address().zipCode());

        return contact;
    }


}
