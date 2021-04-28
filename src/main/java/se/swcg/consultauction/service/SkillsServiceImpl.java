package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.swcg.consultauction.dto.SkillsDto;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.SkillsRepository;

import java.util.List;

@Service
public class SkillsServiceImpl implements SkillsService {

    @Autowired
    private SkillsRepository repository;

    @Autowired
    private DtoConversionService converter;


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
        return converter.SkillsToDto(repository.findBySkillsName(skillsName));
    }
}
