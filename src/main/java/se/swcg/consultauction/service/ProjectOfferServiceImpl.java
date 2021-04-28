package se.swcg.consultauction.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.Bids;
import se.swcg.consultauction.entity.Project;
import se.swcg.consultauction.entity.ProjectOffer;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.model.CreateProjectOfferRequest;
import se.swcg.consultauction.repository.ProjectOfferRepository;
import se.swcg.consultauction.security.SecurityRoles;

import java.util.*;

import static se.swcg.consultauction.service.ServiceHelper.checkIfListIsEmpty;


@Service
public class ProjectOfferServiceImpl implements ProjectOfferService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectOfferRepository repository;

    @Autowired
    private DtoConversionService converter;


    @Override
    public List<ProjectOfferDto> findAll() {
        return converter.projectOfferToDto(repository.findAll());
    }

    @Override
    public ProjectOfferDto findById(String projectOfferId) {
        return converter.projectOfferToDto(repository.findById(projectOfferId).orElseThrow(() ->
                new ResourceNotFoundException("Could not find ProjectOffer with Id: " +projectOfferId)));
    }

    @Override
    public List<ProjectOfferDto> findByConsultantId(String consultantId) {
        return checkIfListIsEmpty(
                converter.projectOfferToDto(
                        repository.findAllByConsultantId(consultantId)),
                "Could not find any project offers");
    }

    @Override
    public List<ProjectOfferDto> findByClientId(String clientId) {
        return checkIfListIsEmpty(
            converter.projectOfferToDto(
                    repository.findAllByClientId(clientId)),
            "Could not find any project offers");
    }

    public ProjectOfferDto setAccepted(String offerId) {
        ProjectOffer foundOffer = converter.dtoToProjectOffer(findById(offerId));

        foundOffer.setAccepted(true);

        return converter.projectOfferToDto(
                repository.save(foundOffer));
    }

    public ProjectOfferDto setRejected(String offerId) {
        ProjectOffer foundOffer = converter.dtoToProjectOffer(findById(offerId));

        foundOffer.setRejected(true);

        return converter.projectOfferToDto(
                repository.save(foundOffer));
    }

    public ProjectOfferDto setSelected(String offerId) {
        ProjectOffer foundOffer = converter.dtoToProjectOffer(findById(offerId));

        foundOffer.setSelected(true);

        return converter.projectOfferToDto(
                repository.save(foundOffer));
    }



    @Override
    public ProjectOfferDto createProjectOffer(CreateProjectOfferRequest projectOfferRequest) {

        Project foundProject = converter.dtoToProject(projectService.findById(projectOfferRequest.getProjectId()));

        ProjectOffer newProjectOffer = new ProjectOffer(
                projectOfferRequest.getConsultantId(),
                false,
                false,
                projectOfferRequest.getStartTime(),
                false,
                new HashSet<Bids>(Collections.singletonList(new Bids(projectOfferRequest.getBids()))),
                foundProject.getProjectName(),
                foundProject.getStartDate(),
                foundProject.getEndDate(),
                foundProject.getWorkLoad(),
                foundProject.getDescription(),
                foundProject.getLocated(),
                foundProject.isDistanceWork(),
                foundProject.isCompanyHardware(),
                foundProject.getContactName(),
                foundProject.getContactEmail(),
                foundProject.getContactPhoneNumber(),
                foundProject.getUser().getUserId()
        );

        System.out.println(newProjectOffer.toString());

        return converter.projectOfferToDto(repository.save(newProjectOffer));
    }

    @Override
    public ProjectOfferDto updateProjectOffer(String ProjectOfferId, CreateProjectOfferRequest projectOfferRequest) {
        return null;
    }

    @Override
    public void deleteAllSelectedWithFalse() {
        List<ProjectOffer> foundOffers = repository.findAllBySelected(false);
        repository.deleteAll(foundOffers);
    }
}
