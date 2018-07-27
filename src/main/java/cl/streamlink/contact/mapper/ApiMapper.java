package cl.streamlink.contact.mapper;

import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.LanguageRepository;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.web.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import javax.inject.Inject;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class})
public abstract class ApiMapper {

    @Inject
    LanguageRepository languageRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    DeveloperRepository developerRepository;

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
            @Mapping(source = "developerReference", target = "developerReference")
    })
    public abstract ContactDTO fromBeanToDTO(Contact bean, String developerReference);

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

    @Mappings(
            {@Mapping(target = "languages", expression = "java(dto.getLanguages().stream()" +
                    ".map(lan -> languageRepository.findOneByReference(lan.getReference()).orElse(null))" +
                    ".filter(lan -> lan != null).collect(Collectors.toSet()))")
            }
    )
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

    @Mappings({
            @Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference")
    })
    public abstract DeveloperDTO fromBeanToDTO(Developer bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))"),
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
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
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

    @Mappings(
            {@Mapping(target = "languages", expression = "java(dto.getLanguages().stream()" +
                    ".map(lan -> languageRepository.findOneByReference(lan.getReference()).orElse(null))" +
                    ".filter(lan -> lan != null).collect(Collectors.toSet()))")
            }
    )
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


}
