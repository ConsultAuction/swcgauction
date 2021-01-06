package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.PrePopLanguages;

import java.util.List;
import java.util.Optional;

public interface PrePopLanguagesRepository extends CrudRepository<PrePopLanguages, String> {

}
