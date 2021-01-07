package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.entity.ProgrammingLanguages;

import java.util.List;

@Repository
public interface ProgrammingLanguagesRepository extends CrudRepository<ProgrammingLanguages, String> {
    List<ProgrammingLanguages> findAll();
}
