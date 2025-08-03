package org.saucedemo.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.login.LoginPage;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SidebarComponent extends BasePage {
    private final By inventoryLink = By.id("inventory_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");
    private final By closeSidebarButton = By.id("react-burger-cross-btn");
    private final By sidebar = By.className(".bm-menu");

    public SidebarComponent(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(inventoryLink));
    }

    public void goToAllItemsPage() {
        click(inventoryLink);
    }

    public void goToAboutPage() {
        click(aboutLink);
    }

    public void resetAppState() {
        click(resetLink);
    }

    public void closeSidebar() {
        click(closeSidebarButton);
    }

    public boolean isSidebarVisible() {
        return waitUntilElementIsInvisible(sidebar, ofSeconds(3));
    }

    public void logout() {
        click(logoutLink);
        new LoginPage(driver);
    }
}
