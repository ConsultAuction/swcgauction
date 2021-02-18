package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.entity.ConsultantDetails;

import java.util.Optional;

@Repository
public interface ConsultantDetailsRepository extends CrudRepository<ConsultantDetails, String> {
    Optional<ConsultantDetails> findByUserUserId(String id);
}
