package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.entity.Project;

import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ProjectRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectRepository repository;

    @Autowired
    DtoConversionService converter;


    @Override
    public List<ProjectDto> findAll() {
        return converter.projectToDto(repository.findAll());
    }

    @Override
    public List<ProjectDto> findAllByContactEmail(String email) {
        return converter.projectToDto(repository.findAllByContactEmail(email));
    }

    @Override
    public ProjectDto findById(String projectId) {
        return converter.projectToDto(repository.findById(projectId).orElseThrow(() ->
                new ResourceNotFoundException("Could not find ProjectOffer with Id: " +projectId)));
    }

}

