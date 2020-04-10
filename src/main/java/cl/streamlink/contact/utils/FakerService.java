package cl.streamlink.contact.utils;


import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.*;
import cl.streamlink.contact.utils.enums.*;
import cl.streamlink.contact.web.dto.*;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
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
    private PositioningService positioningService;

    @Inject
    private ProjectService projectService;

    @Inject
    private NeedService needService;

    @Inject
    private ResourceService resourceService;

    @Inject
    private SocietyService societyService;

    @Inject
    private SocietyContactService societyContactService;

    @Inject
    private UserService userService;

    private Faker faker = new Faker(new Locale("fr", "fr"));


    public void generateNeed(String societyContactReference) throws ContactApiException {

        List<UserDTO> users = userService.getAllUsers();
        NeedDTO need = new NeedDTO();

        need.setSocietyContactReference(societyContactReference);

        need.setTitle(faker.name().title());
        need.setManagerReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
        need.setRhReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
        need.setNote(faker.lorem().paragraph());
        need.setStage(NeedStage.values()[faker.number().numberBetween(1, NeedStage.values().length - 1) - 1]);
        need.setType(NeedType.values()[faker.number().numberBetween(1, NeedType.values().length - 1) - 1]);

        need = needService.createNeed(need);

        NeedInformationDTO needInformation = new NeedInformationDTO();
        needInformation.setBudget(BigDecimal.valueOf(faker.number().randomNumber(4, true)));
        needInformation.setActivityArea(ActivityArea.values()[faker.number().numberBetween(0, ActivityArea.values().length)]);
        needInformation.setClosingDate(LocalDate.now().plusMonths(faker.number().numberBetween(10, 20)));
        needInformation.setResponseDate(LocalDate.now().plusMonths(faker.number().numberBetween(0, 6)));
        needInformation.setStartingDate(LocalDate.now().plusMonths(faker.number().numberBetween(5, 8)));
        needInformation.setDurationByMonth(faker.number().numberBetween(5, 15));
        needInformation.setCurrency(Currency.values()[faker.number().numberBetween(0, Currency.values().length)]);
        needInformation.setPlace(faker.address().country());

        needService.updateNeedInformation(needInformation, need.getReference());
        generatePositioning(need.getReference());

    }

    public void generateFakerSocietyData(Integer societySize, Integer societyContactSize) throws ContactApiException {

        List<UserDTO> users = userService.getAllUsers();

        for (Integer i = 0; i < societySize; i++) {

            SocietyDTO society = new SocietyDTO();

            society.setLabel(faker.company().name());
            society.setManagerReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
            society.setNote(faker.lorem().paragraph());
            society.setStaffNumber(faker.number().numberBetween(5, 500));
            society.setSupplierNumber(faker.code().asin());
            society.setStage(SocietyStage.values()[faker.number().numberBetween(0, SocietyStage.values().length - 1)]);
            society.setActivityArea(ActivityArea.values()[faker.number().numberBetween(0, ActivityArea.values().length - 1)]);
            society.setServices(Arrays.asList(faker.job().keySkills(),
                    faker.job().keySkills(), faker.job().keySkills()));

            society = societyService.createSociety(society);

            ContactDTO contact = generateContact(society.getReference());

            societyService.updateSocietyContact(contact, society.getReference());

            SocietyLegalInformationDTO legalInformation = new SocietyLegalInformationDTO();

            legalInformation.setSocietyReference(society.getReference());
            legalInformation.setApeCode(faker.code().asin());
            legalInformation.setLegalStatus(faker.code().asin());
            legalInformation.setRcs(faker.code().asin());
            legalInformation.setSiret(faker.code().asin());
            legalInformation.setTva(faker.code().asin());

            societyService.updateSocietyLegalInformation(legalInformation, society.getReference());

            for (int j = 0; j < societyContactSize; j++) {
                SocietyContactDTO societyContactDTO = new SocietyContactDTO();

                societyContactDTO.setFirstname(faker.name().firstName());
                societyContactDTO.setLastname(faker.name().lastName());
                societyContactDTO.setGender(Gender.values()[i % 2]);
                societyContactDTO.setManagerReference(users.get(faker.number().numberBetween(0, users.size()-1)).getReference());
                societyContactDTO.setNote(faker.lorem().paragraph());
                societyContactDTO.setStage(SocietyStage.values()[faker.number().numberBetween(0, SocietyStage.values().length-1)]);
                societyContactDTO.setTitle(faker.job().title());
                societyContactDTO.setService(faker.job().position());

                societyContactDTO.setTechnicalScope(String.join(", ", faker.job().keySkills(),
                        faker.job().keySkills(), faker.job().keySkills()));

                societyContactDTO.setFunctionalScope(String.join(", ", faker.job().keySkills(),
                        faker.job().keySkills(), faker.job().keySkills()));

                societyContactDTO.setSocietyReference(society.getReference());


                societyContactDTO = societyContactService.createSocietyContact
                        (societyContactDTO, society.getReference());

                ContactDTO cont = generateContact(societyContactDTO.getReference());

                societyContactService.updateSocietyContactContact(cont,
                        societyContactDTO.getReference(), society.getReference());
                if (j % 2 == 0) {
                    generateProject(societyContactDTO.getReference());
                    generateNeed(societyContactDTO.getReference());

                }
            }

        }
    }

    private void generateProject(String societyContactReference) throws ContactApiException {

        List<UserDTO> users = userService.getAllUsers();
        ProjectDTO project = new ProjectDTO();

        project.setSocietyContactReference(societyContactReference);

        project.setTitle(faker.name().title());
        project.setManagerReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
        project.setRhReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
        project.setNote(faker.lorem().paragraph());
        project.setStage(ProjectStage.values()[faker.number().numberBetween(1, ProjectStage.values().length - 1) - 1]);
        project.setType(ProjectType.values()[faker.number().numberBetween(1, ProjectType.values().length - 1) - 1]);

        project = projectService.createProject(project);

        ProjectInformationDTO projectInformation = new ProjectInformationDTO();
        projectInformation.setBudget(BigDecimal.valueOf(faker.number().randomNumber(4, true)));
        projectInformation.setActivityArea(ActivityArea.values()[faker.number().numberBetween(0, ActivityArea.values().length)]);
        projectInformation.setClosingDate(LocalDate.now().plusMonths(faker.number().numberBetween(10, 20)));
        projectInformation.setResponseDate(LocalDate.now().plusMonths(faker.number().numberBetween(0, 6)));
        projectInformation.setStartingDate(LocalDate.now().plusMonths(faker.number().numberBetween(5, 8)));
        projectInformation.setDurationByMonth(faker.number().numberBetween(5, 15));
        projectInformation.setCurrency(Currency.values()[faker.number().numberBetween(0, Currency.values().length)]);
        projectInformation.setPlace(faker.address().country());

        projectService.updateProjectInformation(projectInformation, project.getReference());
    }

    private void generatePositioning(String needReference) throws ContactApiException {

        List<ResourceResponseDTO> resources = resourceService.getResources(null);
        List<UserDTO> users = userService.getAllUsers();

        PositioningDTO positioning = new PositioningDTO();

        positioning.setNeedReference(needReference);

        positioning.setResourceReference(resources.get(faker.number().numberBetween(0, resources.size() - 1)).getReference());
        positioning.setResponsibleReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
        positioning.setStartDate(LocalDate.now().plusMonths(faker.number().numberBetween(5, 8)));
        positioning.setCjm(BigDecimal.valueOf(faker.number().randomNumber(3, true)));
        positioning.setTjm(BigDecimal.valueOf(faker.number().randomNumber(3, true)));

        positioning.setFreeDays(faker.number().numberBetween(14, 20));
        positioning.setInvoicedDays(faker.number().numberBetween(20, 40));

        positioning.setNote(faker.lorem().paragraph());
        positioning.setStage(PositioningStage.values()[faker.number().numberBetween(1, PositioningStage.values().length - 1) - 1]);

        positioningService.createPositioning(positioning);
    }


    public void deleteAll() throws ContactApiException {

        developerService.getResources(null)
                .forEach(developerResponseDTO -> {
                    try {
                        developerService.deleteResource(developerResponseDTO.getReference());
                    } catch (ContactApiException e) {
                        logger.error(e.getMessage(), e);
                    }
                });

        resourceService.getResources(null)
                .forEach(developerResponseDTO -> {
                    try {
                        resourceService.deleteResource(developerResponseDTO.getReference());
                    } catch (ContactApiException e) {
                        logger.error(e.getMessage(), e);
                    }
                });

        societyService.getSocieties(null)
                .forEach(societyResponseDTO -> {
                    try {
                        societyService.deleteSociety(societyResponseDTO.getReference());
                    } catch (ContactApiException e) {
                        logger.error(e.getMessage(), e);
                    }
                });

    }

    public void generateFakerResourceDate(Integer size) throws ContactApiException {

        List<UserDTO> users = userService.getAllUsers();


        for (Integer i = 0; i < size; i++) {

            ResourceDTO res = new ResourceDTO();

            res.setAvailability(LocalDate.now().plusMonths(faker.number().numberBetween(2, 18)));
            res.setFirstname(faker.name().firstName());
            res.setLastname(faker.name().lastName());
            res.setGender(Gender.values()[i % 2]);
            res.setManagerReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
            res.setRhReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
            res.setNote(faker.lorem().paragraph());

            res.setResourceStage(ResourceStage.values()[faker.number().numberBetween(2, ResourceStage.values().length) - 1]);
            res.setResourceType(ResourceType.values()[faker.number().numberBetween(2, ResourceType.values().length) - 1]);
            res.setRegistrationNumber(faker.idNumber().valid());


            res.setMobility(Arrays.asList(faker.address().country(), faker.address().country(), faker.address().country()));

            res.setEmail(new Faker(new Locale("en")).internet().emailAddress());
            res.setPassword("change-me");

            res = resourceService.createResource(res);

            ContactDTO contact = generateContact(res.getReference());
            contact.setEmail1(res.getEmail());

            resourceService.updateResourceContact(contact, res.getReference());

            PersonalInformationDTO information = new PersonalInformationDTO();

            information.setDeveloperReference(res.getReference());
            information.setBirthDate(LocalDate.now().minusYears(faker.number().numberBetween(25, 40)));
            information.setFamilySituation(FamilySituation.values()[faker.number().numberBetween(0, 6)]);
            information.setNationality(faker.address().country());
            information.setPlaceOfBirth(faker.address().city());
            information.setRole(faker.job().position());
            information.setTjm(new BigDecimal(Math.random()));

            information.setSocialSecurityNumber(faker.number().digits(6));

            resourceService.updateResourcePersonalInformation(information, res.getReference());

            SkillsInformationDTO skills = new SkillsInformationDTO();

            skills.setDeveloperReference(res.getReference());
            skills.setTitle(faker.job().title());
            skills.setExperience(Experience.values()[faker.number().numberBetween(2, Experience.values().length) - 1]);
            skills.setFormation(Formation.values()[faker.number().numberBetween(2, Formation.values().length) - 1]);

            skills.setQualifications(Arrays.asList(faker.educator().university(),
                    faker.educator().university(), faker.educator().university()));

            skills.setLanguages(String.join(", ", faker.job().keySkills(),
                    faker.job().keySkills(), faker.job().keySkills()));


            resourceService.updateResourceSkills(skills, res.getReference());

        }
    }

    public void generateFakerDeveloperData(Integer size) throws ContactApiException {

        List<UserDTO> users = userService.getAllUsers();


        for (Integer i = 0; i < size; i++) {

            DeveloperDTO dev = new DeveloperDTO();

            dev.setAvailability(LocalDate.now().plusMonths(faker.number().numberBetween(2, 18)));
            dev.setFirstname(faker.name().firstName());
            dev.setLastname(faker.name().lastName());
            dev.setGender(Gender.values()[i % 2]);
            dev.setManagerReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
            dev.setRhReference(users.get(faker.number().numberBetween(0, users.size() - 1)).getReference());
            dev.setNote(faker.lorem().paragraph());
            dev.setStage(Stage.values()[faker.number().numberBetween(1, Stage.values().length - 2) - 1]);

            dev.setMobility(Arrays.asList(faker.address().country(), faker.address().country(), faker.address().country()));

            dev = ((DeveloperDTO) developerService.createResource(dev));

            ContactDTO contact = generateContact(dev.getReference());

            developerService.updateResourceContact(contact, dev.getReference());

            PersonalInformationDTO information = new PersonalInformationDTO();

            information.setDeveloperReference(dev.getReference());
            information.setBirthDate(LocalDate.now().minusYears(faker.number().numberBetween(25, 40)));
            information.setFamilySituation(FamilySituation.values()[faker.number().numberBetween(0, 5)]);
            information.setNationality(faker.address().country());
            information.setPlaceOfBirth(faker.address().city());
            information.setSocialSecurityNumber(faker.number().digits(6));

            developerService.updateResourcePersonalInformation(information, dev.getReference());

            SkillsInformationDTO skills = new SkillsInformationDTO();

            skills.setDeveloperReference(dev.getReference());
            skills.setTitle(faker.job().title());
            skills.setExperience(Experience.values()[faker.number().numberBetween(2, Experience.values().length) - 1]);
            skills.setFormation(Formation.values()[faker.number().numberBetween(2, Formation.values().length) - 1]);

            skills.setQualifications(Arrays.asList(faker.educator().university(),
                    faker.educator().university(), faker.educator().university()));

            skills.setLanguages(String.join(", ", faker.job().keySkills(),
                    faker.job().keySkills(), faker.job().keySkills()));


            developerService.updateResourceSkills(skills, dev.getReference());

        }
    }

    private ContactDTO generateContact(String ownerReference) {
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
