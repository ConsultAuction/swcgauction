package se.swcg.consultauction;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.*;
import se.swcg.consultauction.repository.ProgrammingLanguagesRepository;
import se.swcg.consultauction.repository.UserRepository;
import se.swcg.consultauction.security.SecurityRoles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLine implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProgrammingLanguagesRepository programmingLanguagesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(Keys.secretKeyFor(SignatureAlgorithm.HS512).getFormat());

        List<Experience> experience = new ArrayList<>();
        experience.add(new Experience("Lexicon"));
        experience.add(new Experience("Scania"));

        List<Languages> languages = new ArrayList<>();
        languages.add(new Languages("Java"));
        languages.add(new Languages("React"));

        userRepository.save(
                new User(null,
                        "Anders",
                        "Andersson",
                        "a@a.com",
                        passwordEncoder.encode("Password12!"),
                        SecurityRoles.ADMIN.name(),
                        LocalDate.now(),
                        LocalDate.now(),
                        true,
                        null,
                        new Contact("KalmarGatan 4", "333 15", "Kalmar", "Sweden", "0701784567"),
                        null)
        );

        userRepository.save(
                new User(null,
                "Marcus",
                "Persson",
                "m@p.com",
                passwordEncoder.encode("Password12!"),
                SecurityRoles.CONSULTANT.name(),
                LocalDate.now(),
                LocalDate.now(),
                true,
                null,
                new Contact("KalmarGatan 12", "333 57", "Kalmar", "Sweden", "0701234456"),
                new ConsultantDetails(true,
                        false,
                        true,
                        800,
                        experience,
                        languages,
                        null
                )
            )
        );


        userRepository.save(
                new User("Lexicon",
                        "Steve",
                        "Stevsson",
                        "s@s.com",
                        passwordEncoder.encode("Password12!"),
                        SecurityRoles.CLIENT.name(),
                        LocalDate.now(),
                        LocalDate.now(),
                        true,
                        null,
                new Contact("KalmarGatan 8", "333 55", "Kalmar", "Sweden", "0701234567"),
                        null)
        );

        programmingLanguagesRepository.save(new ProgrammingLanguages("Java"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C#"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C++"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("JavaScript"));
    }
}
