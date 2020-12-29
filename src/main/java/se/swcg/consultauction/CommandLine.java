package se.swcg.consultauction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.repository.AdminRepository;

import java.time.LocalDate;

@Component
public class CommandLine implements CommandLineRunner {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {
        Admin admin = new Admin("Adam", "Lundbeg", "a@l.com", "password", "Admin", true, LocalDate.now());

        adminRepository.save(admin);
    }
}
