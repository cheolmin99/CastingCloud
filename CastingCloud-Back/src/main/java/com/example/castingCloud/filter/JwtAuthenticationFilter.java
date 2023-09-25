package com.example.castingCloud.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.castingCloud.provider.TokenProvider;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenProvider tokenProvider;

    protected void doFilterInternal(HttpServletRequest reqeust, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        try {
            String token = parseBearerToken(reqeust);
            if(token != null && !token.equalsIgnoreCase("null")) {
                String actorEmail = tokenProvider.vaildate(token);

                AbstractAuthenticationToken abstractAuthenticationToken = new UsernamePasswordAuthenticationToken(actorEmail, null, AuthorityUtils.NO_AUTHORITIES);

                abstractAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(reqeust));

                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(abstractAuthenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(reqeust, response);

    }
    
    private String parseBearerToken (HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
            
}
