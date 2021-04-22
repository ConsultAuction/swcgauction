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
@Transactional(readOnly = true)
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
    public List<ProjectOfferDto> findByAcceptedByUserId(UserDto user) {
        List<ProjectOfferDto> tmp = findAll();

        List<ProjectOfferDto> arg = new ArrayList<>();

        for (ProjectOfferDto p: tmp){
          if (user.getUserId().equals(p.getConsultantId()) && p.isAccepted() ){
              arg.add(p);
          }
        }

        return arg;
    }

    @Override
    public List<ProjectOfferDto> findByRejectByUserId(UserDto user) {
        List<ProjectOfferDto> tmp = findAll();

        List<ProjectOfferDto> arg = new ArrayList<>();

        for (ProjectOfferDto p: tmp){
            if (user.getUserId().equals(p.getConsultantId()) && p.isRejected() ){
                arg.add(p);
            }
        }

        return arg;
    }

    @Override
    public List<ProjectOfferDto> findBySelectedByUserId(UserDto user) {
        List<ProjectOfferDto> tmp = findAll();

        List<ProjectOfferDto> arg = new ArrayList<>();

        for (ProjectOfferDto p: tmp){
            if (user.getUserId().equals(p.getConsultantId()) && p.isSelected() ){
                arg.add(p);
            }
        }

        return arg;
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
}
