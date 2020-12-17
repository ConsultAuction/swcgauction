package se.swcg.consultauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository repo;

    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User findById(String userId) {
        return repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Could not find a user with id: " + userId));
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findByLanguage(String language) {
        return null;
    }

    @Override
    public List<User> findByLastActive(LocalDate lastActive) {
        return null;
    }

    @Override
    public List<User> findAllByActive(boolean active) {
        return null;
    }

    @Override
    public List<User> findByAvailable(boolean available) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
