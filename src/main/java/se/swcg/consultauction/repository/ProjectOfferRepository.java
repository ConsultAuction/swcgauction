package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.entity.Project;
import se.swcg.consultauction.entity.ProjectOffer;
import se.swcg.consultauction.entity.User;

import java.util.List;

@Repository
public interface ProjectOfferRepository extends CrudRepository<ProjectOffer, String> {

            List<ProjectOffer> findAll();




}
