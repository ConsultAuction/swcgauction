package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.entity.Project;

import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.model.CreateProjectRequest;
import se.swcg.consultauction.model.UpdateProjectRequest;
import se.swcg.consultauction.repository.ProjectRepository;
import se.swcg.consultauction.security.SecurityRoles;

import java.util.List;

import static se.swcg.consultauction.service.ServiceHelper.checkIfListIsEmpty;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private DtoConversionService converter;

    @Override
    public List<ProjectDto> findAll() {
        return checkIfListIsEmpty(
                converter.projectToDto(
                        projectRepo.findAll())
                ,"Could not find any projects"
        );
    }

    @Override
    public List<ProjectDto> findAllByContactEmail(String email) {
        return checkIfListIsEmpty(
                converter.projectToDto(
                        projectRepo.findAllByContactEmail(email))
                ,"Could not find any projects by email"
        );
    }

    @Override
    public ProjectDto findById(String projectId) {
        return converter.projectToDto(
                projectRepo.findById(projectId).orElseThrow(() ->
                new ResourceNotFoundException("Could not find ProjectOffer with Id: " +projectId)));
    }

    @Override
    public List<ProjectDto> findAllProjectByClientId(String clientId) {
        return checkIfListIsEmpty(
                converter.projectToDto(
                        projectRepo.findAllByUser_UserId(clientId))
                , "Could not find any project with this clientId"
        );
    }

    @Override
    public ProjectDto createProject(CreateProjectRequest projectRequest) {

        User foundUser = converter.dtoToUser(
                userService.findById(projectRequest.getUserId()));

        if (!foundUser.getRole().equals(SecurityRoles.CLIENT.name())) {
            throw new IllegalArgumentException("This user is not permitted to create a project");
        }

        Project newProject = new Project(
                projectRequest.getProjectName(),
                projectRequest.getStartDate(),
                projectRequest.getEndDate(),
                projectRequest.getWorkLoad(),
                projectRequest.getDescription(),
                projectRequest.getLocated(),
                projectRequest.isDistanceWork(),
                projectRequest.isCompanyHardware(),
                projectRequest.getContactName(),
                projectRequest.getContactEmail(),
                projectRequest.getContactPhoneNumber(),
                foundUser
        );

        return converter.projectToDto(
                projectRepo.save(newProject));
    }

    @Override
    public ProjectDto updateProject(String projectId, UpdateProjectRequest projectRequest) {
        Project foundProject = converter.dtoToProject(findById(projectId));

        foundProject.setProjectName(projectRequest.getProjectName());
        foundProject.setStartDate(projectRequest.getStartDate());
        foundProject.setEndDate(projectRequest.getEndDate());
        foundProject.setWorkLoad(projectRequest.getWorkLoad());
        foundProject.setDescription(projectRequest.getDescription());
        foundProject.setLocated(projectRequest.getLocated());
        foundProject.setDistanceWork(projectRequest.isDistanceWork());
        foundProject.setCompanyHardware(projectRequest.isCompanyHardware());
        foundProject.setContactName(projectRequest.getContactName());
        foundProject.setContactEmail(projectRequest.getContactEmail());
        foundProject.setContactPhoneNumber(projectRequest.getContactPhoneNumber());

        return converter.projectToDto(
                projectRepo.save(foundProject));
    }

    @Override
    public boolean deleteProject(String projectId) {
        projectRepo.delete(
                converter.dtoToProject(findById(projectId)));

        return !projectRepo.findById(projectId).isPresent();
    }
}

