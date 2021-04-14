package com.diploma.pricemonitoring.controller;

import com.diploma.pricemonitoring.service.smartphone.interf.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smartphone")
public class SmartphoneController {
    @Autowired
    private SmartphoneService smartphoneService;

    @GetMapping("/find-all")
    public ResponseEntity findAll(Pageable pageable) {
        return ResponseEntity.ok(smartphoneService.findAllPageableBaseModel(pageable));
    }
}
