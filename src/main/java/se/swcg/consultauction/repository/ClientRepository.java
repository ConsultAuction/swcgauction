package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
import se.swcg.consultauction.entity.Client;

import java.util.Collection;

public interface ClientRepository extends CrudRepository<Client, String > {

            Collection<Client> findByCompanyNameIgnoreCase(String companyName);

            Client findByEmail(String email);

}
