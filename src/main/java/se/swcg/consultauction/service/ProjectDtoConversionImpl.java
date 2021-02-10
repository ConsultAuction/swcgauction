package se.swcg.consultauction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.dto.ProjectForm;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.entity.Project;
import se.swcg.consultauction.entity.ProjectOffer;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectDtoConversionImpl implements ProjectDtoConversion {

    @Override
    public Project dtoToProject(ProjectDto dto) {
        return new Project(dto.getProjectId(),dto.getProjectName(),dto.getStartDate(),dto.getEndDate(),
                dto.getWorkLoad(),dto.getDescription(),dto.getLocated(), dto.isDistanceWork(), dto.isCompanyHardware(),
                dto.getContactName(),dto.getContactEmail(),dto.getContactPhoneNumber(),dto.getClient());
    }

    @Override
    public ProjectDto projectToDto(Project project) {
        return null;
    }

    @Override
    public List<ProjectDto> projectToDto(List<Project> project) {
        if (project == null) {
            project = new ArrayList<>();
        }

        List<ProjectDto> projectDtos = new ArrayList<>();

        for (Project p: project) {
            projectDtos.add(projectToDto(p));
        }

        return projectDtos;
    }

    @Override
    public ProjectForm DtoToProjectForm(ProjectDto dto) {
        return new ProjectForm(dto.getProjectId(),dto.getProjectName(),dto.getStartDate(),dto.getEndDate(),
                dto.getWorkLoad(),dto.getDescription(),dto.getLocated(),dto.isDistanceWork(),dto.isCompanyHardware(),
                dto.getContactName(),dto.getContactEmail(),dto.getContactPhoneNumber(), dto.getClient());
    }

    @Override
    public Project ProjectFormToProject(ProjectForm dto) {
        return new Project(dto.getProjectName(),dto.getStartDate(),dto.getEndDate(),
                dto.getWorkLoad(),dto.getDescription(),dto.getLocated(),dto.isDistanceWork(),dto.isCompanyHardware(),
                dto.getContactName(),dto.getContactEmail(),dto.getContactEmail(),dto.getClient());
    }
}
