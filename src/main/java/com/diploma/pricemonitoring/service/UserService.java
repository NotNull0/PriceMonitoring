package com.diploma.pricemonitoring.service;

import com.diploma.pricemonitoring.dto.UserRegistrationDto;
import com.diploma.pricemonitoring.model.User;

import java.security.Principal;

public interface UserService {
    User registration(UserRegistrationDto user);

    UserRegistrationDto findOne(Long id);

    UserRegistrationDto findOneByPrincipal(Principal principal);

}
