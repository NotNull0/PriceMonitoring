package com.diploma.pricemonitoring.service;

import com.diploma.pricemonitoring.dto.UserRegistrationDto;
import com.diploma.pricemonitoring.model.User;
import com.diploma.pricemonitoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registration(UserRegistrationDto user) {
        User modelUser = new User();
        modelUser.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        modelUser.setEmail(user.getEmail());
        modelUser.setName(user.getName());
        modelUser.setPassword(passwordEncoder.encode(user.getPassword()));
        modelUser.setRole(user.getRole());
        return userRepository.save(modelUser);
    }

    @Override
    public UserRegistrationDto findOne(Long id) {
        return map(userRepository.findById(id).get());
    }

    private UserRegistrationDto map(User user) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail(user.getEmail());
        userRegistrationDto.setName(user.getName());
        userRegistrationDto.setPassword(user.getPassword());
        userRegistrationDto.setRole(user.getRole());
        return userRegistrationDto;
    }

    @Override
    public UserRegistrationDto findOneByPrincipal(Principal principal) {
        String name = principal.getName();
        return map(userRepository.findByEmail(name));
    }
}
