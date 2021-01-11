package se.swcg.consultauction.repository;

import org.springframework.data.repository.CrudRepository;
//import org.w3c.dom.stylesheets.LinkStyle;
import org.springframework.stereotype.Repository;
import se.swcg.consultauction.entity.Client;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, String > {

            List<Client> findByCompanyNameIgnoreCase(String companyName);
            List<Client> findAll();
            Optional<Client> findByEmail(String email);

}
