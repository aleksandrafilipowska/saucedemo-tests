package org.saucedemo.utils;

import org.openqa.selenium.By;

public class Utils {

    public static By getElementByDataTest(String dataTest) {
        return By.cssSelector("[data-test='" + dataTest + "']");
    }
}
