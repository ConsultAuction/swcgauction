package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.dto.SkillsDto;
import se.swcg.consultauction.entity.Skills;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillsRepository extends CrudRepository <Skills, String > {

        List<Skills> findAll();

        List<Skills> findBySkillsName(String skillsName);
}
