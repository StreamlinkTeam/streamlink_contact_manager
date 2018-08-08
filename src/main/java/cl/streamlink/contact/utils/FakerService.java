package cl.streamlink.contact.utils;


import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.web.dto.*;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
    private UserService userService;

    private Faker faker = new Faker(new Locale("fr", "fr"));


    public void generateFakerData(Integer size) {

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

            ContactDTO contact = new ContactDTO();

            contact.setDeveloperReference(developer.getReference());

            contact.setEmail1(new Faker(new Locale("en")).internet().emailAddress());
            contact.setEmail2(new Faker(new Locale("en")).internet().emailAddress());
            contact.setEmail3(new Faker(new Locale("en")).internet().emailAddress());

            contact.setTel1(faker.phoneNumber().phoneNumber());
            contact.setTel2(faker.phoneNumber().phoneNumber());
            contact.setTel3(faker.phoneNumber().phoneNumber());
            contact.setFax(faker.phoneNumber().phoneNumber());

            contact.setAddress(faker.address().streetAddress());
            contact.setCity(faker.address().city());
            contact.setCountry(faker.address().country());
            contact.setNpa(faker.address().zipCode());

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


}
