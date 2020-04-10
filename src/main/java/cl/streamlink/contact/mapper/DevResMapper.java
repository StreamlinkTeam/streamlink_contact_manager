package cl.streamlink.contact.mapper;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.web.dto.AbstractDevResProfileDTO;
import cl.streamlink.contact.web.dto.AbstractDevResResponseDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

public interface DevResMapper<E extends AbstractDevResProfile, D extends AbstractDevResProfileDTO<E>, R extends AbstractDevResResponseDTO<E>> {

    @Mappings({@Mapping(source = "manager.reference", target = "managerReference"),
            @Mapping(source = "rh.reference", target = "rhReference"),
            @Mapping(target = "mobility", expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),})
    D fromBeanToDTO(E bean);

    @Mappings({
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))"),
            @Mapping(target = "mobility", expression = "java(String.join(\", \", dto.getMobility()))"),
            @Mapping(target = "createdDate", ignore = true), @Mapping(target = "modifiedDate", ignore = true)})
    E fromDTOToBean(D dto);

    @Mappings({
            @Mapping(target = "reference", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "mobility", expression = "java(String.join(\" , \", dto.getMobility()))"),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "manager", expression = "java(userRepository.findOneByReference(dto.getManagerReference()).orElse(null))"),
            @Mapping(target = "rh", expression = "java(userRepository.findOneByReference(dto.getRhReference()).orElse(null))")})
    void updateBeanFromDto(D dto, @MappingTarget E bean);

    @Mappings({@Mapping(target = "mobility", expression = "java(Arrays.asList(bean.getMobility().split(\",\")))"),})
    R fromBeanToDTOResponse(E bean);

}
