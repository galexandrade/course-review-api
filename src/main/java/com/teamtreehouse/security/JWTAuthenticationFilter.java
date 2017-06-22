package com.teamtreehouse.security;

import com.teamtreehouse.security.model.JwtUser;
import com.teamtreehouse.security.services.JwtService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {
    @Autowired
    private JwtService jwtTokenService;

    @Value("${jwt.auth.header}")
    String authHeader;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String authHeaderVal = httpRequest.getHeader( /*authHeader*/ "Authorization");

        if (null==authHeaderVal)
        {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try
        {
            JwtUser jwtUser = jwtTokenService.getUser(authHeaderVal.substring(7));
            httpRequest.setAttribute("jwtUser", jwtUser);
        }
        catch(JwtException e)
        {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
