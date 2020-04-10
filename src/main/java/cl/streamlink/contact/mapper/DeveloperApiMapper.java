package cl.streamlink.contact.mapper;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.web.dto.DeveloperDTO;
import cl.streamlink.contact.web.dto.DeveloperResponseDTO;
import org.mapstruct.Mapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Collectors.class, Arrays.class})
public abstract class DeveloperApiMapper implements DevResMapper<Developer, DeveloperDTO, DeveloperResponseDTO> {

    @Inject
    UserRepository userRepository;
}
