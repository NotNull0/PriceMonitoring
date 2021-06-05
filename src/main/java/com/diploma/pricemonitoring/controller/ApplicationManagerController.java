package com.diploma.pricemonitoring.controller;

import com.diploma.pricemonitoring.dto.UserRegistrationDto;
import com.diploma.pricemonitoring.parse.ProductType;
import com.diploma.pricemonitoring.service.ApplicationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/manager")
public class ApplicationManagerController {

    @Autowired
    private ApplicationManagerService applicationManagerService;

    @GetMapping("/execute/parser")
    public ResponseEntity<?> execute(@RequestParam ProductType productType ) throws IOException {
        applicationManagerService.execute(productType);
        return ResponseEntity.ok("Nice cook bro");
    }

}
