package se.swcg.consultauction.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.LocalDate;

public class SecurityConstants {
    //public static final long EXPIRATION_TIME = 864000000; //10 days
    //public static final String TOKEN_PREFIX = "Bearer ";
    //public static final String HEADER_STRING = "Authorization";
    public static final String SING_UP_URL_CLIENT = "/api/user/client";
    public static final String SING_UP_URL_CONSULTANT = "/api/user/consultant";
    //public static final SecretKey TOKEN_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //public static final String ROLE_CLIENT = "CLIENT";
    //public static final String ROLE_CONSULTANT = "CONSULTANT";
    //public static final String ROLE_ADMIN = "ADMIN";

    public static final boolean DEFAULT_ACTIVE = true;
    public static final boolean DEFAULT_AVAILABLE_FOR_HIRE = false;
}
