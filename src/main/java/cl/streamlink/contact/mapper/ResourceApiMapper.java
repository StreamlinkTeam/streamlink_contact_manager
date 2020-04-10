package cl.streamlink.contact.mapper;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.web.dto.ResourceDTO;
import cl.streamlink.contact.web.dto.ResourceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class, Arrays.class})
public abstract class ResourceApiMapper implements DevResMapper<Resource, ResourceDTO, ResourceResponseDTO> {

    @Inject
    UserRepository userRepository;

    @Mappings({@Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference"),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "mobility", expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),
    })
    public abstract ResourceDTO fromBeanToDTO(Resource bean);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "mobility", expression = "java(String.join(\" , \", dto.getMobility()))"),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))")})
    public abstract void updateBeanFromDto(ResourceDTO dto, @MappingTarget Resource bean);
}
