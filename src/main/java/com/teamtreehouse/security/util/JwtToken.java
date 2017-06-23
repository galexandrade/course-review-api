package com.teamtreehouse.security.util;

import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.teamtreehouse.security.transfer.JwtUserDto;
import org.springframework.stereotype.Component;

/**
 * Class validates a given token by using the secret configured in the application
 *
 * @author pascal alma
 */
@Component
public class JwtToken {
    private final UserRepository users;

    //@Value("${jwt.token.secret}")
    private String secret = "something-secret-you-cannot-keep-it";

    public JwtToken(UserRepository users) {
        this.users = users;
    }

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseToken(String token) {
        User user = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = users.findOne(Long.parseLong((String) body.get("userId")));

        } catch (JwtException e) {
            // Simply print the exception and null will be returned for the userDto
            e.printStackTrace();
        }
        return user;
    }

    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId() + "");
        claims.put("role", user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
