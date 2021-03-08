package se.swcg.consultauction.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.UrlPathHelper;
import se.swcg.consultauction.dto.UserDto;
import se.swcg.consultauction.model.UserLoginRequest;
import se.swcg.consultauction.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Log LOG = LogFactory.getLog(AuthenticationFilter.class);

    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";

    private final static UrlPathHelper urlPathHelper = new UrlPathHelper();

    private final UserService service;

    public AuthenticationFilter( UserService service) {
        this.service = service;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginRequest loginRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequest.class);

            UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

            // Allow subclasses to set the "details" property
            setDetails(request, token);

            UserDto userDto = service.findByEmail(loginRequest.getUsername());
            System.out.println(userDto.toString());
            response.addHeader("UserId", userDto.getUserId());

            return this.getAuthenticationManager().authenticate(token);
        } catch(IOException e) {
            LOG.error(ERROR_MESSAGE, e);
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }
    }


    /*@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        chain.doFilter(request, response);
    }*/

    /*@Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        logger.debug("failed authentication while attempting to access "
                + urlPathHelper.getPathWithinApplication((HttpServletRequest) request));

        //Add more descriptive message
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication Failed");
    }*/

   /* @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String userName = (authResult.getName());
        //String tokenSecret =  new SecurityConstants().getTokenSecret();

        *//*String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SecurityConstants.TOKEN_SECRET)
                .compact();*//*

        UserDto userDto = service.findByEmail(userName);

        //response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader("UserId", userDto.getUserId());
    }*/
}