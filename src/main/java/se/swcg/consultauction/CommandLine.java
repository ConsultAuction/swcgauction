package se.swcg.consultauction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.Contact;
import se.swcg.consultauction.entity.ProgrammingLanguages;
import se.swcg.consultauction.entity.User;
import se.swcg.consultauction.repository.ProgrammingLanguagesRepository;
import se.swcg.consultauction.repository.UserRepository;

import java.time.LocalDate;

@Component
public class CommandLine implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProgrammingLanguagesRepository programmingLanguagesRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(
                new User(null,
                        null,
                        "Steve",
                        "Stevsson",
                        "s@s.com",
                        passwordEncoder.encode("Password12!"),
                        "Client",
                        LocalDate.now(),
                        LocalDate.now(),
                        true,
                        false,
                        null,
                new Contact("KalmarGatan 8", "333 55", "Kalmar", "Sweden", "0701234567")));

        programmingLanguagesRepository.save(new ProgrammingLanguages("Java"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C#"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C++"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("JavaScript"));
    }
}
