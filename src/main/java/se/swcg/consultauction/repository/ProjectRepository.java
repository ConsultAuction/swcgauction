package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.Project;

import java.util.List;

public interface ProjectRepository extends CrudRepository <Project, String > {

        List<Project> findAll();

        List<Project> findAllByContactEmail(String email);




}
