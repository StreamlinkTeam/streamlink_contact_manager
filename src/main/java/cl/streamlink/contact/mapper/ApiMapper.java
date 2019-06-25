package cl.streamlink.contact.mapper;

import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.repository.*;
import cl.streamlink.contact.web.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class, Arrays.class})
public abstract class ApiMapper {

    @Inject
    ProjectRepository projectRepository;

    @Inject
    NeedRepository needRepository;
    @Inject
    PositioningRepository positioningRepository;


    @Inject
    UserRepository userRepository;

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    ResourceRepository resourceRepository;

    @Inject
    SocietyRepository societyRepository;

    @Inject
    SocietyContactRepository societyContactRepository;

    @Inject
    TimeLineRepository timeLineRepository;


    @Value("${contact.cv.url}")
    String baseUrl;

    @Inject
    TimeListRepository timeListRepository;


    @Mappings({@Mapping(source = "resource.reference", target = "resourceReference")})
    public abstract TimeListDTO fromBeanToDTO(TimeList bean);

    @Mappings({@Mapping(target = "resource", expression = "java(resourceRepository.findOneByReference(dto.getResourceReference()).orElse(null))")})
    public abstract TimeList fromDTOToBean(TimeListDTO dto);


    @Mappings({@Mapping(target = "reference", ignore = true),
            @Mapping(target = "resource", expression = "java(resourceRepository.findOneByReference(dto.getResourceReference()).orElse(null))")})
    public abstract void updateBeanFromDto(TimeListDTO dto, @MappingTarget TimeList bean);


    @Mappings({@Mapping(source = "reference", target = "timeListReference"),
            @Mapping(source="positioning.reference",target = "positioningReference")})

    public abstract TimeLineDTO fromBeanToDTO(TimeLine bean);

    @Mappings({
            @Mapping(target = "positioning", expression = "java(positioningRepository.findOneByReference(dto.getPositioningReference()).orElse(null))")})
    public abstract TimeLine fromDTOToBean(TimeLineDTO dto);


    @Mappings({@Mapping(target = "reference", ignore = true),
            @Mapping(target = "positioning", expression = "java(positioningRepository.findOneByReference(dto.getPositioningReference()).orElse(null))")})
    public abstract void updateBeanFromDto(TimeLineDTO dto, @MappingTarget TimeLine bean);


    public abstract Resource fromDeveloperToResource(Developer bean);

    public abstract ResourceDTO fromDeveloperToResource(DeveloperDTO bean);

    public abstract ProjectPos fromPositioningToProject(Positioning bean);

    public abstract ProjectPosDTO fromPositioningToProject(PositioningDTO bean);

//	public abstract Project fromNeedToProject(Need bean);

//	public abstract Project fromNeedToProject(NeedDTO bean);

    // ****************************************************** Contract *******************************************************************
    @Mappings({@Mapping(source = "developer.reference", target = "developerReference"),
            @Mapping(source = "responsible.reference", target = "responsibleReference")})
    public abstract ContractDTO fromBeanToDTO(Contract bean);

    @Mappings({
            @Mapping(target = "developer", expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Contract fromDTOToBean(ContractDTO dto);

    // ****************************************************** Positioning
    // *******************************************************************

//	@Mappings({ @Mapping(source = "resource.reference", target = "resourceReference"),
//			@Mapping(source = "project.reference", target = "projectReference"),
//			@Mapping(source = "responsible.reference", target = "responsibleReference"),
//			@Mapping(target = "client", expression = "java(bean.getProject().getSocietyContact().getSociety().getLabel().concat(\" - \")"
//					+ ".concat(bean.getProject().getSocietyContact().getFirstname()).concat(\" \")"
//					+ ".concat(bean.getProject().getSocietyContact().getLastname()))"),
//			@Mapping(source = "project.title", target = "projectTitle"),
//			@Mapping(target = "resourceFullName", expression = "java(bean.getResource().getFirstname()+\" \"+bean.getResource().getLastname())") })
//	public abstract PositioningDTO fromBeanToDTO(Positioning bean);
//
//	@Mappings({
//			@Mapping(target = "resource", expression = "java(resourceRepository.findOneByReference(dto.getResourceReference()).orElse(null))"),
//			@Mapping(target = "project", expression = "java(projectRepository.findOneByReference(dto.getProjectReference()).orElse(null))"),
//			@Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),
//			@Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true) })
//	public abstract Positioning fromDTOToBean(PositioningDTO dto);


    @Mappings({
            @Mapping(source = "resource.reference", target = "resourceReference"),
            @Mapping(source = "need.reference", target = "needReference"),
            @Mapping(source = "responsible.reference", target = "responsibleReference"),
            @Mapping(target = "client", expression = "java(bean.getNeed().getSocietyContact().getSociety().getLabel().concat(\" - \")"
                    + ".concat(bean.getNeed().getSocietyContact().getFirstname()).concat(\" \")"
                    + ".concat(bean.getNeed().getSocietyContact().getLastname()))"),
            @Mapping(source = "need.title", target = "needTitle"),
            @Mapping(target = "resourceFullName", expression = "java(bean.getResource().getFirstname()+\" \"+bean.getResource().getLastname())")})
    public abstract PositioningDTO fromBeanToDTO(Positioning bean);

    @Mappings({
            @Mapping(target = "resource", expression = "java(resourceRepository.findOneByReference(dto.getResourceReference()).orElse(null))"),
            @Mapping(target = "need", expression = "java(needRepository.findOneByReference(dto.getNeedReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Positioning fromDTOToBean(PositioningDTO dto);
    // ****************************************************** Contact
    // *******************************************************************

    @Mappings({@Mapping(source = "ownerReference", target = "ownerReference")
            })
    public abstract ContactDTO fromBeanToDTO(Contact bean, String ownerReference );

    public abstract Contact fromDTOToBean(ContactDTO dto);

    // ****************************************************** PersonalInformation
    // *******************************************************************

    @Mappings({@Mapping(source = "developerReference", target = "developerReference")})
    public abstract PersonalInformationDTO fromBeanToDTO(PersonalInformation bean, String developerReference);

    public abstract PersonalInformation fromDTOToBean(PersonalInformationDTO dto);

    // ****************************************************** ProjectInformation
    // *******************************************************************

    @Mappings({@Mapping(source = "projectReference", target = "projectReference")})
    public abstract ProjectInformationDTO fromBeanToDTO(ProjectInformation bean, String projectReference);

    public abstract ProjectInformation fromDTOToBean(ProjectInformationDTO dto);

    // ****************************************************** SkillsInformation
    // *******************************************************************

    @Mappings({@Mapping(source = "developerReference", target = "developerReference")})
    public abstract SkillsInformationDTO fromBeanToDTO(SkillsInformation bean, String developerReference);

    public abstract SkillsInformation fromDTOToBean(SkillsInformationDTO dto);

    // ****************************************************** WishedContract
    // *******************************************************************

    @Mappings({@Mapping(source = "developer.reference", target = "developerReference"),
            @Mapping(source = "responsible.reference", target = "responsibleReference")})
    public abstract WishedContractDTO fromBeanToDTO(WishedContract bean);

    @Mappings({
            @Mapping(target = "developer", expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),})
    public abstract WishedContract fromDTOToBean(WishedContractDTO dto);

    // ****************************************************** Developer
    // *******************************************************************

    @Mappings({@Mapping(target = "mobility", expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),
            @Mapping(target = "resource", expression = "java(bean instanceof Resource)")})
    public abstract DeveloperResponseDTO fromBeanToDTOResponse(Developer bean);

    @Mappings({@Mapping(target = "mobility", expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),})
    public abstract ResourceResponseDTO fromBeanToDTOResponse(Resource bean);

    public abstract SocietyContactResponseDTO fromBeanToDTOResponse(SocietyContact bean);

    public abstract SocietyResponseDTO fromBeanToDTOResponse(Society bean);

    public abstract ProjectResponseDTO fromBeanToDTOResponse(Project bean);

    @Mappings({@Mapping(source = "manager.reference", target = "managerReference")})
    public abstract AbstractProfileDTO fromBeanToDTO(AbstractProfile bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract AbstractProfile fromDTOToBean(AbstractProfileDTO dto);

    @Mappings({@Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference"),
            @Mapping(target = "resource", expression = "java(bean instanceof Resource)"),
            @Mapping(target = "mobility", expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),})
    public abstract DeveloperDTO fromBeanToDTO(Developer bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))"),
            @Mapping(target = "mobility", expression = "java(String.join(\", \", dto.getMobility()))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Developer fromDTOToBean(DeveloperDTO dto);

    @Mappings({@Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference"),
            @Mapping(target = "mobility", expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),
    })
    public abstract ResourceDTO fromBeanToDTO(Resource bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))"),
            @Mapping(target = "mobility", expression = "java(String.join(\", \", dto.getMobility()))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Resource fromDTOToBean(ResourceDTO dto);

    @Mappings({@Mapping(source = "societyContact.reference", target = "societyContactReference"),
            @Mapping(source = "project.reference", target = "projectReference"),
            @Mapping(source = "responsible.reference", target = "responsibleReference"),
            @Mapping(target = "responsibleFullName", expression = "java(bean.getResponsible().getFirstname()+\" \"+bean.getResponsible().getLastname())"),
            @Mapping(target = "societyContactFullName", expression = "java(bean.getSocietyContact().getFirstname()+\" \"+bean.getSocietyContact().getLastname())")})
    public abstract SocietyActionDTO fromBeanToSocietyActionDTO(Action bean);

    @Mappings({
            @Mapping(target = "societyContact", expression = "java(societyContactRepository.findOneByReference(dto.getSocietyContactReference()).orElse(null))"),
            @Mapping(target = "project", expression = "java(projectRepository.findOneByReference(dto.getProjectReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),
            @Mapping(target = "developer", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Action fromDTOToBean(SocietyActionDTO dto);

    @Mappings({@Mapping(source = "developer.reference", target = "developerReference"),
            @Mapping(source = "responsible.reference", target = "responsibleReference"),
            @Mapping(target = "responsibleFullName", expression = "java(bean.getResponsible().getFirstname()+\" \"+bean.getResponsible().getLastname())")})
    public abstract DeveloperActionDTO fromBeanToDeveloperActionDTO(Action bean);

    @Mappings({
            @Mapping(target = "developer", expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),
            @Mapping(target = "societyContact", ignore = true), @Mapping(target = "project", ignore = true),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Action fromDTOToBean(DeveloperActionDTO dto);

    @Mappings({@Mapping(source = "developer.reference", target = "developerReference"),
            @Mapping(source = "responsible.reference", target = "responsibleReference"),
            @Mapping(target = "responsibleFullName", expression = "java(bean.getResponsible().getFirstname()+\" \"+bean.getResponsible().getLastname())")})
    public abstract EvaluationDTO fromBeanToDTO(Evaluation bean);

    @Mappings({
            @Mapping(target = "developer", expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Evaluation fromDTOToBean(EvaluationDTO dto);

    public abstract LanguageDTO fromBeanToDTO(Language bean);

    public abstract Language fromDTOToBean(LanguageDTO dto);

    @Mapping(target = "password", ignore = true)
    public abstract UserDTO fromBeanToDTO(User bean);

    public abstract User fromDTOToBean(UserDTO dto);

    @Mappings({@Mapping(target = "developerReference", source = "developer.reference"),
            @Mapping(target = "url", expression = "java(baseUrl.concat(bean.getName()))")})
    public abstract CurriculumVitaeDTO fromBeanToDTO(CurriculumVitae bean);

    @Mappings({
            @Mapping(target = "developer", expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))")})
    public abstract CurriculumVitae fromDTOToBean(CurriculumVitaeDTO dto);

    @Mappings({@Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "society.reference", target = "societyReference"),})
    public abstract SocietyContactDTO fromBeanToDTO(SocietyContact bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "society", expression = "java(societyRepository.findOneByReference(dto.getSocietyReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract SocietyContact fromDTOToBean(SocietyContactDTO dto);

    @Mappings({@Mapping(source = "manager.reference", target = "managerReference")})
    public abstract SocietyDTO fromBeanToDTO(Society bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Society fromDTOToBean(SocietyDTO dto);

    @Mappings({@Mapping(source = "societyContact.reference", target = "societyContactReference"),
            @Mapping(source = "societyContact.society.reference", target = "societyReference"),
            @Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference"),})
    public abstract ProjectDTO fromBeanToDTO(Project bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))"),
            @Mapping(target = "societyContact", expression = "java(societyContactRepository.findOneByReference(dto.getSocietyContactReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Project fromDTOToBean(ProjectDTO dto);



    @Mappings({@Mapping(source = "resource.reference", target = "resourceReference"),
            @Mapping(source = "need.reference", target = "needReference"),
            @Mapping(source = "responsible.reference", target = "responsibleReference"),
            @Mapping(source = "need.title", target = "needTitle"),
            @Mapping(source = "need.societyContact.society.label", target = "client"),
            @Mapping(target = "resourceFullName", expression = "java(bean.getResource().getFirstname()+\" \"+bean.getResource().getLastname())"),
    })
    public abstract ProjectPosDTO fromBeanToDTO(ProjectPos bean);

    @Mappings({
            @Mapping(target = "resource", expression = "java(resourceRepository.findOneByReference(dto.getResourceReference()).orElse(null))"),
            @Mapping(target = "need", expression = "java(needRepository.findOneByReference(dto.getNeedReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),

})
    public abstract ProjectPos fromDTOToBean(ProjectPosDTO dto);

    @Mappings({@Mapping(source = "societyReference", target = "societyReference")})
    public abstract SocietyLegalInformationDTO fromBeanToDTO(SocietyLegalInformation bean, String societyReference);

    public abstract SocietyLegalInformation fromDTOToBean(SocietyLegalInformationDTO dto);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "mobility", expression = "java(String.join(\" , \", dto.getMobility()))"),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))")})
    public abstract void updateBeanFromDto(DeveloperDTO dto, @MappingTarget Developer bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "stage", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "mobility", expression = "java(String.join(\" , \", dto.getMobility()))"),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))")})
    public abstract void updateBeanFromDto(ResourceDTO dto, @MappingTarget Resource bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "societyContact", expression = "java(societyContactRepository.findOneByReference(dto.getSocietyContactReference()).orElse(null))"),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))")})
    public abstract void updateBeanFromDto(ProjectDTO dto, @MappingTarget Project bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "societyContact", expression = "java(societyContactRepository.findOneByReference(dto.getSocietyContactReference()).orElse(null))"),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))")})
    public abstract void updateBeanFromDto(NeedDTO dto, @MappingTarget Need bean);

    @Mappings({@Mapping(target = "reference", ignore = true)})
    public abstract void updateBeanFromDto(LanguageDTO dto, @MappingTarget Language bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "password", ignore = true)})
    public abstract void updateBeanFromDto(UserDTO dto, @MappingTarget User bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "developer", ignore = true),
            @Mapping(target = "responsible", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)})
    public abstract void updateBeanFromDto(ContractDTO dto, @MappingTarget Contract bean);

    @Mappings({
//            @Mapping(target = "reference", ignore = true),
//            @Mapping(target = "resource", ignore = true),
//            @Mapping(target = "responsible", ignore = true),
//            @Mapping(target = "need", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
//            @Mapping(target = "modifiedDate", ignore = true)
            })
    public abstract void updateBeanFromDto(PositioningDTO dto, @MappingTarget Positioning bean);

    public abstract void updateBeanFromDto(ContactDTO dto, @MappingTarget Contact bean);

    public abstract void updateBeanFromDto(PersonalInformationDTO dto, @MappingTarget PersonalInformation bean);

    public abstract void updateBeanFromDto(ProjectInformationDTO dto, @MappingTarget ProjectInformation bean);

    public abstract void updateBeanFromDto(NeedInformationDTO dto, @MappingTarget NeedInformation bean);

    @Mappings({
            @Mapping(target = "country"),
            @Mapping(target = "city"),
            @Mapping(target = "postal"),
            @Mapping(target = "address"),
            @Mapping(target = "currency"),
            @Mapping(target = "presentationDate"),
            @Mapping(target = "need", expression = "java(needRepository.findOneByReference(dto.getNeedReference()).orElse(null))"),
            @Mapping(target = "responsible", expression = "java(userRepository.findOneByReference(dto.getResponsibleReference()).orElse(null))"),
            @Mapping(target = "resource", expression = "java(resourceRepository.findOneByReference(dto.getResourceReference()).orElse(null))")})
    public abstract void updateBeanFromDto(ProjectPosDTO dto, @MappingTarget ProjectPos bean);

    /*
     * @Mappings( {@Mapping(target = "languages", expression =
     * "java(dto.getLanguages().stream()" +
     * ".map(lan -> languageRepository.findOneByReference(lan.getReference()).orElse(null))"
     * + ".filter(lan -> lan != null).collect(Collectors.toSet()))") } )
     */
    public abstract void updateBeanFromDto(SkillsInformationDTO dto, @MappingTarget SkillsInformation bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "responsible", ignore = true),
            @Mapping(target = "developer", ignore = true)})
    public abstract void updateBeanFromDto(WishedContractDTO dto, @MappingTarget WishedContract bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true), @Mapping(target = "developer", ignore = true),
            @Mapping(target = "responsible", ignore = true)})
    public abstract void updateBeanFromDto(EvaluationDTO dto, @MappingTarget Evaluation bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true), @Mapping(target = "societyContact", ignore = true),
            @Mapping(target = "developer", ignore = true), @Mapping(target = "responsible", ignore = true)})
    public abstract void updateBeanFromDto(DeveloperActionDTO dto, @MappingTarget Action bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "societyContact", expression = "java(societyContactRepository.findOneByReference(dto.getSocietyContactReference()).orElse(null))"),
            @Mapping(target = "project", ignore = true), @Mapping(target = "developer", ignore = true),
            @Mapping(target = "responsible", ignore = true)})
    public abstract void updateBeanFromDto(SocietyActionDTO dto, @MappingTarget Action bean);

    public abstract void updateBeanFromDto(SocietyLegalInformationDTO dto, @MappingTarget SocietyLegalInformation bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))")})
    public abstract void updateBeanFromDto(SocietyDTO dto, @MappingTarget Society bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "society", ignore = true)})
    public abstract void updateBeanFromDto(SocietyContactDTO dto, @MappingTarget SocietyContact bean);

    @Mappings({@Mapping(target = "reference", ignore = true), @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),})
    public abstract void updateBeanFromDto(AbstractProfileDTO dto, @MappingTarget AbstractProfile bean);

    @Mappings({@Mapping(source = "societyContact.reference", target = "societyContactReference"),
            @Mapping(source = "societyContact.society.reference", target = "societyReference"),
            @Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference"),})
    public abstract NeedDTO fromBeanToDTO(Need bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))"),
            @Mapping(target = "societyContact", expression = "java(societyContactRepository.findOneByReference(dto.getSocietyContactReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    public abstract Need fromDTOToBean(NeedDTO dto);

    @Mappings({@Mapping(source = "needReference", target = "needReference")})
    public abstract NeedInformationDTO fromBeanToDTO(NeedInformation bean, String needReference);

    public abstract NeedInformation fromDTOToBean(NeedInformationDTO dto);

    public abstract NeedResponseDTO fromBeanToDTOResponse(Need bean);


}
