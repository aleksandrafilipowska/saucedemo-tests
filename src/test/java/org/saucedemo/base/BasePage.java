package org.saucedemo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.saucedemo.pages.components.SidebarComponent;

import java.time.Duration;

import static org.openqa.selenium.By.id;
import static org.saucedemo.utils.SelectorUtils.getElementByDataTest;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final ElementActions acts;

    private final By burgerMenuButton = id("react-burger-menu-btn");
    private final By secondaryHeader = getElementByDataTest("title");
    private final By errorMessage = getElementByDataTest("error");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.acts = new ElementActions(driver, this.wait);
    }

    public String getSecondaryHeaderText() {
        return driver.findElement(secondaryHeader).getText();
    }

    public String getErrorMessage() {
        return acts.getText(errorMessage);
    }

    public SidebarComponent openSidebar(WebDriver driver) {
        acts.click(burgerMenuButton);
        return new SidebarComponent(driver);
    }
}
