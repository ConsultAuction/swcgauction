package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.entity.Project;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAll();

    List<ProjectDto> findAllByContactEmail(String email);

    ProjectDto findById(String projectId);


}
