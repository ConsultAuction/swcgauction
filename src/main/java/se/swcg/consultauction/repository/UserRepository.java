package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();
    //List<User> findByQualificationsLanguageLanguageIgnoreCase(String Language);
    List<User> findByCompanyNameIgnoreCase(String companyName);
    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);
    List<User> findByLastActive(LocalDate lastActive);
    List<User> findByActive(boolean active);
    //List<User> findByAvailableForHire(boolean available);





}
