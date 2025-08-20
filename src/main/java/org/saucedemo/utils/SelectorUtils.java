package org.saucedemo.utils;

import org.openqa.selenium.By;
import org.saucedemo.base.ElementActions;
import org.saucedemo.testdata.model.Product;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public final class SelectorUtils {
    public static By getElementByDataTest(String dataTest) {
        return cssSelector("[data-test='" + dataTest + "']");
    }

    public static void addToCart(ElementActions acts, Product product) {
        By addToCartButton = id("add-to-cart-" + product.name().toLowerCase().replace(" ", "-"));
        acts.click(addToCartButton);
    }

    public static void removeFromTheCart(ElementActions acts, Product product) {
        By removeFromTheCartButton = id("remove-" + product.name().toLowerCase().replace(" ",
                "-"));
        acts.click(removeFromTheCartButton);
    }

    private SelectorUtils() {
    }
}
