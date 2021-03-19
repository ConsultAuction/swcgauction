package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.entity.Project;
import se.swcg.consultauction.model.CreateProjectRequest;
import se.swcg.consultauction.model.UpdateProjectRequest;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAll();
    List<ProjectDto> findAllByContactEmail(String email);
    ProjectDto findById(String projectId);

    ProjectDto createProject(CreateProjectRequest projectRequest);
    ProjectDto updateProject(String projectId, UpdateProjectRequest projectRequest);
    boolean deleteProject(String projectId);
}
