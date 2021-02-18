package se.swcg.consultauction.service;

import se.swcg.consultauction.dto.SkillsDto;
import se.swcg.consultauction.entity.Skills;

import java.util.List;

public interface SkillsService {

    List<SkillsDto> findAll();

    SkillsDto findById(String skillsId);

    List<SkillsDto> findByName(String skillsName);


}
