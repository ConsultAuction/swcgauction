package se.swcg.consultauction;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.*;
import se.swcg.consultauction.repository.ConsultantDetailsRepository;
import se.swcg.consultauction.repository.ProgrammingLanguagesRepository;
import se.swcg.consultauction.repository.UserRepository;
import se.swcg.consultauction.security.SecurityConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLine implements CommandLineRunner {
    @Autowired
    ConsultantDetailsRepository consultantDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProgrammingLanguagesRepository programmingLanguagesRepository;

    /*@Autowired
    BCryptPasswordEncoder passwordEncoder;*/

    @Override
    public void run(String... args) throws Exception {

        /*System.out.println(Keys.secretKeyFor(SignatureAlgorithm.HS512).getFormat());

        List<Experience> experience = new ArrayList<>();
        experience.add(new Experience("Lexicon"));
        experience.add(new Experience("Scania"));

        List<Languages> languages = new ArrayList<>();
        languages.add(new Languages("Java"));
        languages.add(new Languages("React"));

        consultantDetailsRepository.save(
                new ConsultantDetails(true,
                        false,
                        true,
                        800,
                        new User(null,
                                "Marcus",
                                "Persson",
                                "m@p.com",
                                passwordEncoder.encode("Password12!"),
                                SecurityConstants.ROLE_CONSULTANT,
                                LocalDate.now(),
                                LocalDate.now(),
                                true,
                                null,
                                new Contact("KalmarGatan 12", "333 57", "Kalmar", "Sweden", "0701234456")
                                ),
                        experience,
                        languages,
                        null
                        )
        );

        userRepository.save(
                new User(null,
                        "Steve",
                        "Stevsson",
                        "s@s.com",
                        passwordEncoder.encode("Password12!"),
                        SecurityConstants.ROLE_CLIENT,
                        LocalDate.now(),
                        LocalDate.now(),
                        true,
                        null,
                new Contact("KalmarGatan 8", "333 55", "Kalmar", "Sweden", "0701234567")));

        programmingLanguagesRepository.save(new ProgrammingLanguages("Java"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C#"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("C++"));
        programmingLanguagesRepository.save(new ProgrammingLanguages("JavaScript"));*/
    }
}
