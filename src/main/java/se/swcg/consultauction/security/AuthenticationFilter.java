package se.swcg.consultauction.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import se.swcg.consultauction.dto.AdminDto;
import se.swcg.consultauction.model.AdminLoginRequestModel;
import se.swcg.consultauction.service.AdminService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private AdminService service;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AdminService service) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            AdminLoginRequestModel creds = new ObjectMapper()
                    .readValue(request.getInputStream(), AdminLoginRequestModel.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String userName = ((SecurityAdmin) authResult.getPrincipal()).getUsername();
        //String tokenSecret =  new SecurityConstants().getTokenSecret();

        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SecurityConstants.TOKEN_SECRET)
                .compact();

        //AdminService service = (AdminService) SpringApplicationContext.getBean("adminServiceImpl");
        AdminDto adminDto = service.findByEmail(userName);

        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader("UserId", adminDto.getAdminId());
    }
}
