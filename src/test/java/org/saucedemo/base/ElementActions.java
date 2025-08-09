package org.saucedemo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ElementActions {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public ElementActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void sendKeys(By locator, String text) {
        WebElement element = wait.until(visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        wait.until(elementToBeClickable(locator)).click();
    }

    public String getText(By locator) {
        return wait.until(visibilityOfElementLocated(locator)).getText();
    }

    public String getValue(By locator) {
        return wait.until(visibilityOfElementLocated(locator)).getAttribute("value");
    }

    public boolean waitUntilElementIsInvisible(By locator, Duration timeout) {
        try {
            return new WebDriverWait(driver, timeout)
                    .until(invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementNotPresent(By locator) {
        return wait.until(not(visibilityOfElementLocated(locator)));
    }

}
