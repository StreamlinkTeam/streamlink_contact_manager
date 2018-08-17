package cl.streamlink.contact.mapper;

import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.LanguageRepository;
import cl.streamlink.contact.repository.SocietyRepository;
import cl.streamlink.contact.repository.UserRepository;
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
    LanguageRepository languageRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    SocietyRepository societyRepository;

    @Value("${contact.cv.url}")
    String baseUrl;

    @Mappings({
            @Mapping(source = "developer.reference", target = "developerReference")
    })
    public abstract ContractDTO fromBeanToDTO(Contract bean);

    @Mappings({
            @Mapping(target = "developer",
                    expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    public abstract Contract fromDTOToBean(ContractDTO dto);

    @Mappings({
            @Mapping(source = "ownerReference", target = "ownerReference")
    })
    public abstract ContactDTO fromBeanToDTO(Contact bean, String ownerReference);

    public abstract Contact fromDTOToBean(ContactDTO dto);

    @Mappings({
            @Mapping(source = "developerReference", target = "developerReference")
    })
    public abstract PersonalInformationDTO fromBeanToDTO(PersonalInformation bean, String developerReference);

    public abstract PersonalInformation fromDTOToBean(PersonalInformationDTO dto);

    @Mappings({
            @Mapping(source = "developerReference", target = "developerReference")
    })
    public abstract SkillsInformationDTO fromBeanToDTO(SkillsInformation bean, String developerReference);

    /*@Mappings(
            {@Mapping(target = "languages", expression = "java(dto.getLanguages().stream()" +
                    ".map(lan -> languageRepository.findOneByReference(lan.getReference()).orElse(null))" +
                    ".filter(lan -> lan != null).collect(Collectors.toSet()))")
            }
    )*/
    public abstract SkillsInformation fromDTOToBean(SkillsInformationDTO dto);

    @Mappings({
            @Mapping(source = "developer.reference", target = "developerReference")
    })
    public abstract WishedContractDTO fromBeanToDTO(WishedContract bean);

    @Mappings({
            @Mapping(target = "developer",
                    expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))")
    })
    public abstract WishedContract fromDTOToBean(WishedContractDTO dto);

    public abstract DeveloperResponseDTO fromBeanToDTOResponse(Developer bean);

    public abstract SocietyResponseDTO fromBeanToDTOResponse(Society bean);

//    @Mappings({
//            @Mapping(source = "society.reference", target = "societyReference"),
//    })
//    public abstract SocietyContactResponseDTO fromBeanToDTOResponse(SocietyContact bean);


    @Mappings({
            @Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference"),
            @Mapping(target = "mobility",
                    expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),

    })
    public abstract DeveloperDTO fromBeanToDTO(Developer bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))"),
            @Mapping(target = "mobility",
                    expression = "java(String.join(\", \", dto.getMobility()))"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    public abstract Developer fromDTOToBean(DeveloperDTO dto);

    @Mappings({
            @Mapping(source = "developer.reference", target = "developerReference"),
            @Mapping(source = "responsable.reference", target = "responsableReference")
    })
    public abstract ActionDTO fromBeanToDTO(Action bean);

    @Mappings({
            @Mapping(target = "developer",
                    expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))"),
            @Mapping(target = "responsable", expression = "java(userRepository.findOneByReference(dto.getResponsableReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    public abstract Action fromDTOToBean(ActionDTO dto);

    @Mappings({
            @Mapping(source = "developer.reference", target = "developerReference"),
            @Mapping(source = "responsable.reference", target = "responsableReference"),
            @Mapping(target = "responsableName", expression = "java(bean.getResponsable().getFirstname()+\" \"+bean.getResponsable().getLastname())")
    })
    public abstract EvaluationDTO fromBeanToDTO(Evaluation bean);

    @Mappings({
            @Mapping(target = "developer",
                    expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))"),
            @Mapping(target = "responsable", expression = "java(userRepository.findOneByReference(dto.getResponsableReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    public abstract Evaluation fromDTOToBean(EvaluationDTO dto);

    public abstract LanguageDTO fromBeanToDTO(Language bean);

    public abstract Language fromDTOToBean(LanguageDTO dto);

    @Mapping(target = "password", ignore = true)
    public abstract UserDTO fromBeanToDTO(User bean);

    public abstract User fromDTOToBean(UserDTO dto);

    @Mappings({
            @Mapping(target = "developerReference", source = "developer.reference"),
            @Mapping(target = "url", expression = "java(baseUrl.concat(bean.getName()))")
    })
    public abstract CurriculumVitaeDTO fromBeanToDTO(CurriculumVitae bean);

    @Mappings({
            @Mapping(target = "developer", expression = "java(developerRepository.findOneByReference(dto.getDeveloperReference()).orElse(null))")
    })
    public abstract CurriculumVitae fromDTOToBean(CurriculumVitaeDTO dto);

    @Mappings({
            @Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "society.reference", target = "societyReference"),
    })
    public abstract SocietyContactDTO fromBeanToDTO(SocietyContact bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "society", expression = "java(societyRepository.findOneByReference(dto.getSocietyReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    public abstract SocietyContact fromDTOToBean(SocietyContactDTO dto);

    @Mappings({
            @Mapping(source = "manager.reference", target = "managerReference")
    })
    public abstract SocietyDTO fromBeanToDTO(Society bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    public abstract Society fromDTOToBean(SocietyDTO dto);

    @Mappings({
            @Mapping(source = "societyReference", target = "societyReference")
    })
    public abstract SocietyLegalInformationDTO fromBeanToDTO(SocietyLegalInformation bean, String societyReference);

    public abstract SocietyLegalInformation fromDTOToBean(SocietyLegalInformationDTO dto);


    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "mobility",
                    expression = "java(String.join(\" , \", dto.getMobility()))"),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))")
    })
    public abstract void updateBeanFromDto(DeveloperDTO dto, @MappingTarget Developer bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true)
    })
    public abstract void updateBeanFromDto(LanguageDTO dto, @MappingTarget Language bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true)
    })
    public abstract void updateBeanFromDto(UserDTO dto, @MappingTarget User bean);


    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "developer", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true)
    })
    public abstract void updateBeanFromDto(ContractDTO dto, @MappingTarget Contract bean);


    public abstract void updateBeanFromDto(ContactDTO dto, @MappingTarget Contact bean);


    public abstract void updateBeanFromDto(PersonalInformationDTO dto, @MappingTarget PersonalInformation bean);

    /*@Mappings(
            {@Mapping(target = "languages", expression = "java(dto.getLanguages().stream()" +
                    ".map(lan -> languageRepository.findOneByReference(lan.getReference()).orElse(null))" +
                    ".filter(lan -> lan != null).collect(Collectors.toSet()))")
            }
    )*/
    public abstract void updateBeanFromDto(SkillsInformationDTO dto, @MappingTarget SkillsInformation bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "developer", ignore = true),
    })
    public abstract void updateBeanFromDto(WishedContractDTO dto, @MappingTarget WishedContract bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "developer", ignore = true),
            @Mapping(target = "responsable", ignore = true)
    })
    public abstract void updateBeanFromDto(EvaluationDTO dto, @MappingTarget Evaluation bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "developer", ignore = true),
            @Mapping(target = "responsable", ignore = true)
    })
    public abstract void updateBeanFromDto(ActionDTO dto, @MappingTarget Action bean);

    public abstract void updateBeanFromDto(SocietyLegalInformationDTO dto, @MappingTarget SocietyLegalInformation bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))")
    })
    public abstract void updateBeanFromDto(SocietyDTO dto, @MappingTarget Society bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "society", ignore = true)
    })
    public abstract void updateBeanFromDto(SocietyContactDTO dto, @MappingTarget SocietyContact bean);


}
