package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, String> {
}
