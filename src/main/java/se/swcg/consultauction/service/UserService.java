package se.swcg.consultauction.service;

import se.swcg.consultauction.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    User create(User user);
    User findById(String userId);
    List<User> findAll();
    List<User> findByLanguage(String language);
    List<User> findByLastActive(LocalDate lastActive);
    List<User> findAllByActive(boolean active);
    List<User> findByAvailable(boolean available);
    User update(User user);
    void delete(User user);

}
