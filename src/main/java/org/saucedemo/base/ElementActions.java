package org.saucedemo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

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

    public boolean isDisplayedNow(By locator) {
        return driver.findElements(locator).stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean isPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

}
