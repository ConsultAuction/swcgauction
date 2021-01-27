package se.swcg.consultauction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.swcg.consultauction.entity.Admin;
import se.swcg.consultauction.repository.AdminRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
* Not USED!
*
*
*
* */
@Component
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        System.out.println(authentication.toString());
        System.out.println("Name: " + authentication.getName());
        System.out.println("Password: " + authentication.getCredentials().toString());
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<Admin> admin = repo.findByEmail(email);

        //System.out.println(admin.toString());
        //System.out.println(bCryptPasswordEncoder.matches(pwd, admin.get().getPassword()));

        if (admin.isPresent()) {
            if (passwordEncoder.matches(pwd, admin.get().getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(admin.get().getRole()));
                return new UsernamePasswordAuthenticationToken(email, pwd, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("No user registered with this email");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
