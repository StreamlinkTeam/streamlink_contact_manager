package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.ResourceStage;
import cl.streamlink.contact.utils.enums.ResourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends AbstractDevResRepository<Resource> {


    Page<Resource> findByFirstnameContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrLastnameContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrSkillsInformationTitleContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrSkillsInformationLanguagesContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeIn
            (String value, List<ResourceStage> stages, List<Formation> formations, List<Experience> experiences, List<ResourceType> types,
             String value1, List<ResourceStage> stages1, List<Formation> formations1, List<Experience> experiences1, List<ResourceType> types1,
             String value2, List<ResourceStage> stages2, List<Formation> formations2, List<Experience> experiences2, List<ResourceType> types2,
             String value3, List<ResourceStage> stages3, List<Formation> formations3, List<Experience> experiences3, List<ResourceType> types3,
             Pageable pageable);


    Optional<Resource> findOneByEmail(String email);

}
