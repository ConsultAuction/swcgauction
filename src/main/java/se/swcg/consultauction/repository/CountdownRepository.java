package se.swcg.consultauction.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.entity.CountdownDate;

@Repository
public interface CountdownRepository extends CrudRepository<CountdownDate, String> {
}
