package org.saucedemo.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;

public class SidebarComponent extends BasePage {
    private final By inventoryLink = By.id("inventory_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetLink = By.id("reset_sidebar_link");
    private final By closeSidebarButton = By.id("react-burger-cross-btn");

    public SidebarComponent(WebDriver driver) {
        super(driver);
    }

    public void closeSidebar() {
        click(closeSidebarButton);
    }
}
