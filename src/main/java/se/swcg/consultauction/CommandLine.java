package se.swcg.consultauction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.entity.PrePopLanguages;
import se.swcg.consultauction.repository.AdminRepository;
import se.swcg.consultauction.repository.PrePopLanguagesRepository;
import se.swcg.consultauction.service.PrePopLanguagesService;

import java.time.LocalDate;

// @Profile("!test") prevents CommandLine from running during test.
@Profile("!test")
@Component
public class CommandLine implements CommandLineRunner {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PrePopLanguagesRepository prePopLanguagesRepository;

    @Override
    public void run(String... args) throws Exception {
        Admin admin = new Admin("Adam", "Lundbeg", "a@l.com", "password", "Admin", true, LocalDate.now());

        adminRepository.save(admin);

        prePopLanguagesRepository.save(new PrePopLanguages("Java"));
    }
}
