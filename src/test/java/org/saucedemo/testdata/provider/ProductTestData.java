package org.saucedemo.testdata.provider;

import org.saucedemo.testdata.model.PriceSummary;
import org.saucedemo.testdata.model.Product;

public class ProductTestData {
    public static final Product TEST_PRODUCT_01 = new Product("4", "Sauce Labs Backpack", "$29.99");
    public static final Product TEST_PRODUCT_02 = new Product("3", "Test.allTheThings() T-Shirt " +
            "(Red)", "$15.99");

    public static final PriceSummary PRICE_SUMMARY_TP_01 = new PriceSummary("Item total: $29.99"
            , "Tax: $2.40", "Total: $32.39");
    public static final PriceSummary PRICE_SUMMARY_TP_02 = new PriceSummary("Item total: $15.99",
            "Tax: $1.28",
            "Total: $17.27");
    public static final PriceSummary PRICE_SUMMARY_TP_01_02 = new PriceSummary("Item total: $15.99",
            "Tax: $1.28",
            "Total: $17.27");

    private ProductTestData() {
    }
}
