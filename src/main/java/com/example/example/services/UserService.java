package com.example.example.services;


import com.example.example.Dtos.UserDTO;
import com.example.example.enitites.Role;
import com.example.example.enitites.User;
import com.example.example.repositories.RoleRepository;
import com.example.example.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO userDTO) {
        Role role = this.roleRepository.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        User user = new User(userDTO, roles);

        this.userRepository.save(user);
    }


    public boolean checkForNonExistingUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        boolean isExists = user == null;
        return this.userRepository.findByUsername(username) == null;
    }

    public boolean checkForNonExistingEmail(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList()).toArray(new String[0]))
                .build();
    }
}
