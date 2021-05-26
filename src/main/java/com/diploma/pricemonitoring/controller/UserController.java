package com.diploma.pricemonitoring.controller;

import com.diploma.pricemonitoring.dto.UserRegistrationDto;
import com.diploma.pricemonitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find-user")
    public ResponseEntity<UserRegistrationDto> findUser(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.findOne(userId));
    }

    @PostMapping("/registration")
    public ResponseEntity<Long> registration(@RequestBody UserRegistrationDto user) {
        return ResponseEntity.ok(userService.registration(user).getId());
    }

}
