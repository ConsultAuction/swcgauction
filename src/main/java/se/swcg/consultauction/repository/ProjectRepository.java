package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.dto.ProjectDto;
import se.swcg.consultauction.entity.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository <Project, String > {
        List<Project> findAll();
        List<Project> findAllByContactEmail(String email);
        List<Project> findAllByUserUserId(String userId);
}
