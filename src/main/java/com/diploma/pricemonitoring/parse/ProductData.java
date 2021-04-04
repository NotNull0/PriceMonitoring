package com.diploma.pricemonitoring.parse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductData<T> {
    Set<T> getProducts(ProductType productType) throws IOException;
}
