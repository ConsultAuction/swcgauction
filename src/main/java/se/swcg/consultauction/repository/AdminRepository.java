package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.Admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, String> {
    List<Admin> findAll();
    Optional<Admin> findByEmail(String email);
    List<Admin> findByRole(String role);
    List<Admin> findByActive(boolean active);
    List<Admin> findByLastActive(LocalDate date);

}
