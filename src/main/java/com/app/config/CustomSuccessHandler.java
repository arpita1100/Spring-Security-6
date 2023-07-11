package com.app.config;

import com.app.entity.User;
import com.app.repository.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DefaultRedirectStrategy defaultRedirectStrategy;

    private String SUCCESS_URL="/user";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        System.out.println(request.getRequestURI());
        if(authentication.getPrincipal() instanceof DefaultOidcUser)
        {
            DefaultOidcUser user=(DefaultOidcUser) authentication.getPrincipal();
            String fullname=user.getFullName();
            String username=user.getEmail();
            String tokenValue=user.getIdToken().getTokenValue();
            System.out.println(fullname);
            System.out.println(username);
            System.out.println(tokenValue);
            if(userRepo.findByUsername(username)==null)
            {
                System.out.println("saving start...");
                userRepo.save(new User(username,fullname,"google","google"));
                System.out.println("saved");
            }
        }
        defaultRedirectStrategy.sendRedirect(request,response,SUCCESS_URL);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }
}
