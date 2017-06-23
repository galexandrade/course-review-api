package com.teamtreehouse.security;


import com.teamtreehouse.security.exception.JwtTokenMalformedException;
import com.teamtreehouse.security.model.AuthenticatedUser;
import com.teamtreehouse.security.model.JwtAuthenticationToken;
import com.teamtreehouse.security.transfer.JwtUserDto;
import com.teamtreehouse.security.util.JwtToken;
import com.teamtreehouse.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Used for checking the token from the request and supply the UserDetails if the token is valid
 *
 * @author pascal alma
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtToken jwtToken;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        User user = jwtToken.parseToken(token);

        if (user == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(user.getRoles());

        return new AuthenticatedUser(user.getId(), user.getUsername(), token, authorityList);
    }

}
