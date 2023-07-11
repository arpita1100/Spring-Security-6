package com.app.config;

import com.app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(request->request
                .requestMatchers("/user").authenticated()
                .requestMatchers("/get/**").permitAll()
                .requestMatchers("/nauth").permitAll()
                .requestMatchers("/secure").authenticated()
        );
        http.oauth2Login(oauth->oauth.successHandler(customSuccessHandler));
        http.formLogin(form->form.successHandler(customSuccessHandler)).userDetailsService(customUserDetailsService);


        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder)
    {
        try
        {
            return builder.getAuthenticationManager();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
