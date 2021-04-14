package com.diploma.pricemonitoring;

import com.diploma.pricemonitoring.parse.EkatalogProductData;
import com.diploma.pricemonitoring.parse.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;


@SpringBootApplication
public class PriceMonitoringApplication {

    @Autowired
    private EkatalogProductData ekatalogProductData;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PriceMonitoringApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void execute() throws IOException {
//        ekatalogProductData.getProducts(ProductType.NOTEBOOK);
//    }

}
