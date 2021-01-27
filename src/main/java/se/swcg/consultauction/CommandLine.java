package se.swcg.consultauction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.entity.ProgrammingLanguages;
import se.swcg.consultauction.repository.AdminRepository;
import se.swcg.consultauction.repository.ProgrammingLanguagesRepository;

import java.time.LocalDate;

@Component
public class CommandLine implements CommandLineRunner {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ProgrammingLanguagesRepository programmingLanguagesRepository;

    @Override
    public void run(String... args) throws Exception {
        adminRepository.save(new Admin("Adam", "Lundbeg", "a@l.com", new BCryptPasswordEncoder().encode("Password12!"), "Admin", true, LocalDate.now()));

        programmingLanguagesRepository.save(new ProgrammingLanguages("Java"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C#"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C++"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("JavaScript"));
    }
}
