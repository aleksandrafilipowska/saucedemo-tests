package org.saucedemo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WaitActions {

    protected WebDriverWait wait;

    public WaitActions(WebDriverWait wait) {
        this.wait = wait;
    }

    public void visible(By locator) {
        wait.until(visibilityOfElementLocated(locator));
    }

    public void gone(By locator) {
        wait.until(invisibilityOfElementLocated(locator));
    }

    public void clickable(By locator) {
        wait.until(elementToBeClickable(locator));
    }


}
