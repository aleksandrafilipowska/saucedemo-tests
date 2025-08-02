package org.saucedemo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.saucedemo.pages.components.SidebarComponent;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.saucedemo.utils.Utils.getElementByDataTest;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By secondaryHeader = getElementByDataTest("title");
    private final By errorMessage = getElementByDataTest("error");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = wait.until(visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        wait.until(elementToBeClickable(locator)).click();
    }

    protected String getText(By locator) {
        return wait.until(visibilityOfElementLocated(locator)).getText();
    }

    protected String getValue(By locator) {
        return wait.until(visibilityOfElementLocated(locator)).getAttribute("value");
    }

    public String getSecondaryHeaderText() {
        return driver.findElement(secondaryHeader).getText();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    protected SidebarComponent openSidebar(WebDriver driver) {
        click(burgerMenuButton);
        return new SidebarComponent(driver);
    }
}
