package se.swcg.consultauction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommandLine implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(LocalDate.now());
    }
}
