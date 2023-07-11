package com.app.controller;

import com.app.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public User user(Authentication authentication)
    {
        if(authentication.getPrincipal() instanceof DefaultOidcUser)
        {
            DefaultOidcUser oidcUser=(DefaultOidcUser) authentication.getPrincipal();
            User user=new User();
            String username=oidcUser.getEmail();
            String fullname=oidcUser.getFullName();
            user.setAuthType("google");
            user.setUsername(username);
            user.setFullname(fullname);
            return user;
        }
        User user=(User) authentication.getPrincipal();
        return user;
    }
    @GetMapping("/secure")
    public String secure()
    {
        return "Secure";
    }
//    @GetMapping("/get/{username}")
//    public Authentication getAuth(@PathVariable String username)
//    {
//        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(username,"google");
//        if(SecurityContextHolder.getContext().getAuthentication()==null)
//        {
//            try {
//                authenticationManager.authenticate(token);
//            }catch(Exception e)
//            {
//
//            }
//            SecurityContextHolder.getContext().setAuthentication(token);
//        }
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//    @PostMapping("/nauth")
//    public Authentication auth(@RequestBody User user, HttpServletRequest request)
//    {
//        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
//        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
//        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//        authenticationManager.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(token);

//        UsernamePasswordAuthenticationToken token1=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
//        try {
//            authenticationManager.authenticate(token1);
//        }catch(Exception e)
//        {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//        authentication.setAuthenticated(true);
//        return authentication;
        //        System.out.println("nauth chla ...");
//        try {
//            System.out.println("authenticating nauth");
//            authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(token);
//            System.out.println("no error in nauth");
//        }catch(Exception e)
//        {
//            System.out.println("errorr....nauth");
//            e.printStackTrace();
//        }
//        return null;
//    }

}
