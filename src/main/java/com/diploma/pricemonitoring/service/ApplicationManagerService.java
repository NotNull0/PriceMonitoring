package com.diploma.pricemonitoring.service;

import com.diploma.pricemonitoring.parse.Parser;
import com.diploma.pricemonitoring.parse.ProductType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApplicationManagerService {
    private final Parser parser;

    public ApplicationManagerService(Parser parser) {
        this.parser = parser;
    }

    public void execute(ProductType productType) throws IOException {
        if (productType.equals(ProductType.NOTEBOOK)) {
            parser.getProducts(ProductType.NOTEBOOK);
        } else if (productType.equals(ProductType.SMARTPHONE)) {
            parser.getProducts(ProductType.SMARTPHONE);
        } else if (productType.equals(ProductType.TABLETOP))
            parser.getProducts(ProductType.TABLETOP);
    }
}

