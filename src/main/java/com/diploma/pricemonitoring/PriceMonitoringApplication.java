package com.diploma.pricemonitoring;

import com.diploma.pricemonitoring.parse.Parser;
import com.diploma.pricemonitoring.parse.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@SpringBootApplication
public class PriceMonitoringApplication {

    @Autowired
    private Parser parser;

    public static void main(String[] args) throws IOException {
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        SpringApplication.run(PriceMonitoringApplication.class, args);
    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void execute() throws IOException {
//        parser.getProducts(ProductType.SMARTPHONE);
//    }

}
