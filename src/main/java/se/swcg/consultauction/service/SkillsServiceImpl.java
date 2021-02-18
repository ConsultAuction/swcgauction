package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.dto.SkillsDto;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ProjectRepository;
import se.swcg.consultauction.repository.SkillsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    SkillsRepository repository;

    @Autowired
    DtoConversionService converter;


    @Override
    public List<SkillsDto> findAll() {
        return converter.SkillsToDto(repository.findAll());
    }

    @Override
    public SkillsDto findById(String skillsId) {
        return converter.SkillsToDto(repository.findById(skillsId).orElseThrow(()->
                new ResourceNotFoundException("Could not find Skill with ID: " +skillsId)));
    }

    @Override
    public List<SkillsDto> findByName(String skillsName) {
        return converter.SkillsToDto(repository.findByName(skillsName));
    }
}
