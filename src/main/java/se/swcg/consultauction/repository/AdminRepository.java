package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.entity.Admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {

    Optional<Admin> findByEmailAndPassword(String email, String Password);



    List<Admin> findAll();
    Optional<Admin> findByEmail(String email);
    List<Admin> findByRole(String role);
    List<Admin> findByActive(boolean active);
    List<Admin> findByLastActive(LocalDate date);

}
