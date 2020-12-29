package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();
    List<User> findByQualificationsLanguageLanguageIgnoreCase(String Language);
    List<User> findByLastActive(LocalDate lastActive);
    List<User> findAllByActive(boolean active);
    List<User> findByAvailable(boolean available);
}
