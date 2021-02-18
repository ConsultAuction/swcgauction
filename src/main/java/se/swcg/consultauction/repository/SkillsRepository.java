package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.dto.SkillsDto;
import se.swcg.consultauction.entity.Skills;

import java.util.List;
import java.util.Optional;

public interface SkillsRepository extends CrudRepository <Skills, String > {

        List<Skills> findAll();

        List<Skills> findByName(String skillsName);


}
