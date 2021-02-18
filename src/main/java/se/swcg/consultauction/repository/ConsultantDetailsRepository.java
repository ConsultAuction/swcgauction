package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.ConsultantDetails;

import java.util.Optional;

public interface ConsultantDetailsRepository extends CrudRepository<ConsultantDetails, String> {
    Optional<ConsultantDetails> findByUserUserId(String id);
}
