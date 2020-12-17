package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.User;

import java.time.LocalDate;
import java.util.Collection;

public interface UserRepository extends CrudRepository<User, String> {

    Collection<User> findAll();
    Collection<User> findByQualificationContainingLanguageIgnoreCase(String Language);
    Collection<User> findByLastActive(LocalDate lastActive);
    Collection<User> findAllByActive(boolean active);
    Collection<User> findByAvailable(boolean available);
}
