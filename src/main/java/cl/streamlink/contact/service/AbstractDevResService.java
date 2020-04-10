package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.mapper.DevResMapper;
import cl.streamlink.contact.repository.AbstractDevResRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.*;
import net.minidev.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDevResService
        <E extends AbstractDevResProfile, T extends AbstractDevResRepository<E>, D extends AbstractDevResProfileDTO<E>, R extends AbstractDevResResponseDTO<E>, M extends DevResMapper<E, D, R>> {

    @Inject
    protected T devResRepository;

    @Inject
    protected M mapper;

    @Inject
    protected ApiMapper apiMapper;

    @Inject
    protected CurriculumVitaeService curriculumVitaeService;

    @Inject
    protected EvaluationService evaluationService;

    @Inject
    private ContractService contractService;

    @Inject
    private ActionService actionService;

    protected abstract String getEntityName();

    public abstract R generateDeveloperFromCv(MultipartFile cv) throws ContactApiException;

    public abstract D getResourceByEmail(String email) throws ContactApiException;

    public abstract AbstractDevResProfileDTO<? extends AbstractDevResProfile> createResource(D dto) throws ContactApiException;

    public abstract JSONObject deleteResource(String developerReference) throws ContactApiException;

    public abstract AbstractDevResProfileDTO<? extends AbstractDevResProfile> updateResource(D resourceDTO, String developerReference) throws ContactApiException;

    public D getResource(String reference) throws ContactApiException {

        return mapper.fromBeanToDTO(findResourceByReferenceOrThrowExceptionIfNotFound(reference));
    }

    public List<D> getResourceByManger(String managerReference) {
        return devResRepository.findByManagerReference(managerReference)
                .stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public List<R> getResources(String reference) throws ContactApiException {

        if (reference != null)
            return Collections.singletonList(mapper.fromBeanToDTOResponse(findResourceByReferenceOrThrowExceptionIfNotFound(reference)));

        else
            return devResRepository.findAll().stream().map(mapper::fromBeanToDTOResponse)
                    .collect(Collectors.toList());
    }

    public List<R> searchResources(String term) {

        if (MiscUtils.isEmpty(term) || term.length() < 3)
            return Collections.EMPTY_LIST;

        else return devResRepository.findByFirstnameContaining(term).stream()
                .map(resource -> mapper.fromBeanToDTOResponse(resource))
                .collect(Collectors.toList());
    }

    public ContactDTO updateResourceContact(ContactDTO contact, String reference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(reference);

        apiMapper.updateBeanFromDto(contact, devRes.getContact());

        devRes = devResRepository.save(devRes);

        return apiMapper.fromBeanToDTO(devRes.getContact(), reference);

    }

    public ContactDTO getResourceContact(String reference) throws ContactApiException {

        ContactDTO contact = getResources(reference).get(0).getContact();
        contact.setOwnerReference(reference);
        return contact;
    }


    public SkillsInformationDTO updateResourceSkills(SkillsInformationDTO skillsInformation, String reference) throws ContactApiException {


        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(reference);
        apiMapper.updateBeanFromDto(skillsInformation, devRes.getSkillsInformation());

        devRes = devResRepository.save(devRes);

        return apiMapper.fromBeanToDTO(devRes.getSkillsInformation(), reference);

    }

    public SkillsInformationDTO getResourceSkills(String reference) throws ContactApiException {

        SkillsInformationDTO skillsInformation = getResources(reference).get(0).getSkillsInformation();
        skillsInformation.setDeveloperReference(reference);
        return skillsInformation;
    }

    public PersonalInformationDTO updateResourcePersonalInformation(PersonalInformationDTO personalInformation, String reference) throws ContactApiException {

        E developer = findResourceByReferenceOrThrowExceptionIfNotFound(reference);

        apiMapper.updateBeanFromDto(personalInformation, developer.getPersonalInformation());

        developer = devResRepository.save(developer);

        return apiMapper.fromBeanToDTO(developer.getPersonalInformation(), reference);
    }

    public PersonalInformationDTO getResourcePersonalInformation(String reference) throws ContactApiException {
        PersonalInformationDTO personalInformation = getResources(reference).get(0).getPersonalInformation();
        personalInformation.setDeveloperReference(reference);
        return personalInformation;
    }

    public List<CurriculumVitaeDTO> findResourceCvs(String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);

        return curriculumVitaeService.findResourceCvs(devRes);
    }

    public JSONObject removeResourceCv(String reference, String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);

        return curriculumVitaeService.removeResourceCv(reference, devRes);


    }

    public CurriculumVitaeDTO addResourceCv(MultipartFile cv, String developerReference) throws IOException, ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);

        return curriculumVitaeService.addResourceCv(cv, devRes);

    }

    public EvaluationDTO createEvaluation(EvaluationDTO evaluation, String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return evaluationService.createEvaluation(evaluation, devRes);
    }

    public EvaluationDTO updateEvaluation(EvaluationDTO evaluation, String reference, String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return evaluationService.updateEvaluation(evaluation, reference, devRes);

    }

    public List<EvaluationDTO> getEvaluation(String reference, String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return evaluationService.getEvaluation(reference, devRes);
    }

    public JSONObject deleteEvaluation(String reference, String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return evaluationService.deleteEvaluation(reference, devRes);
    }

    private E findResourceByReferenceOrThrowExceptionIfNotFound(String developerReference) throws ContactApiException {
        return devResRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder(getEntityName(), developerReference));
    }

    public ResourceActionDTO updateAction(ResourceActionDTO action, String reference, String developerReference) throws ContactApiException {
        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return actionService.updateResourceAction(action, reference, devRes);
    }

    public List<ResourceActionDTO> getAction(String reference, String developerReference) throws ContactApiException {
        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return actionService.getResourceAction(reference, devRes);
    }

    public JSONObject deleteAction(String reference, String developerReference) throws ContactApiException {
        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return actionService.deleteResourceAction(reference, devRes);
    }

    public ResourceActionDTO createAction(ResourceActionDTO action, String developerReference) throws ContactApiException {
        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return actionService.createResourceAction(action, devRes);
    }

    public ContractDTO createContract(ContractDTO contract, String developerReference) throws ContactApiException {
        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return contractService.createContract(contract, devRes);
    }

    public ContractDTO updateContract(ContractDTO contract, String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return contractService.updateContract(contract, devRes);
    }

    public ContractDTO getContract(String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return contractService.getContract(devRes);
    }

    public JSONObject deleteContract(String developerReference) throws ContactApiException {
        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return contractService.deleteContract(devRes);
    }

    public WishedContractDTO updateWishedContract(WishedContractDTO wishedContract, String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return contractService.updateWishedContract(wishedContract, devRes);
    }

    public WishedContractDTO getWishedContract(String developerReference) throws ContactApiException {

        E devRes = findResourceByReferenceOrThrowExceptionIfNotFound(developerReference);
        return contractService.getWishedContract(devRes);
    }
}
