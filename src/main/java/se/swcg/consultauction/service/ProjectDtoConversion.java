package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.dto.ProjectForm;
import se.swcg.consultauction.dto.ProjectOfferDto;
import se.swcg.consultauction.dto.ProjectOfferForm;
import se.swcg.consultauction.entity.Project;
import se.swcg.consultauction.entity.ProjectOffer;

import java.util.List;

public interface ProjectDtoConversion {

    Project dtoToProject(ProjectDto dto);

    ProjectDto projectToDto(Project project);

    List<ProjectDto> projectToDto(List<Project> project);

    ProjectForm DtoToProjectForm(ProjectDto dto);

    Project ProjectFormToProject(ProjectForm dto);


}
