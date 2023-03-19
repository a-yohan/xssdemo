package com.example.xssdemo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.xssdemo.dto.RegisterFormDto;
import com.example.xssdemo.model.User;
import com.example.xssdemo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper mapper = new ObjectMapper();

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public User registerNewUser(RegisterFormDto form) {
        User user = mapper.convertValue(form, User.class);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole("ROLE_USER");
        user = userRepository.save(user);
        SecurityContextHolder.getContext().setAuthentication(
            UsernamePasswordAuthenticationToken.authenticated(user, form.getPassword(), user.getAuthorities())
        );
        return user;
    }
}
