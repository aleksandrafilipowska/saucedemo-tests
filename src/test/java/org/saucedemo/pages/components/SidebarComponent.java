package org.saucedemo.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;

public class SidebarComponent extends BasePage {
    private final By inventoryLink = id("inventory_sidebar_link");
    private final By aboutLink = id("about_sidebar_link");
    private final By logoutLink = id("logout_sidebar_link");
    private final By resetLink = id("reset_sidebar_link");
    private final By closeSidebarButton = id("react-burger-cross-btn");
    private final By sidebar = className("bm-menu");

    public SidebarComponent(WebDriver driver) {
        super(driver);
        waits.visible(inventoryLink);
    }

    public void goToAllItemsPage() {
        acts.click(inventoryLink);
    }

    public void goToAboutPage() {
        acts.click(aboutLink);
    }

    public void resetAppState() {
        acts.click(resetLink);
    }

    public void closeSidebar() {
        acts.click(closeSidebarButton);
    }

    public boolean isSidebarVisible() {
        waits.gone(sidebar);
        return acts.isDisplayedNow(sidebar);
    }

    public void logout() {
        acts.click(logoutLink);
        waits.gone(logoutLink);
    }
}
