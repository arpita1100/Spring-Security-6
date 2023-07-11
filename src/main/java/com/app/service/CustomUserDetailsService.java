package com.app.service;

import com.app.entity.User;
import com.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User savedUser=userRepo.findByUsername(username);
        if(savedUser!=null)
        System.out.println(savedUser.getUsername());
        else
            System.out.println("user null hai");
        if(savedUser==null)
        {
            throw new UsernameNotFoundException("User with this username does not exist in database");
        }else
            return savedUser;
    }
}
