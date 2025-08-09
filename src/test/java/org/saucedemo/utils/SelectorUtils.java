package org.saucedemo.utils;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;

public final class SelectorUtils {
    public static By getElementByDataTest(String dataTest) {
        return cssSelector("[data-test='" + dataTest + "']");
    }

    private SelectorUtils() {
    }
}
