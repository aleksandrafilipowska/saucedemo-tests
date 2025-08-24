package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;

import static org.openqa.selenium.By.id;
import static org.saucedemo.utils.SelectorUtils.getElementByDataTest;

public class CartPage extends BasePage {
    private final By inventoryItem = getElementByDataTest("inventory-item");
    private final By inventoryItemNameLabel = getElementByDataTest("inventory-item-name");
    private final By inventoryItemDescLabel = getElementByDataTest("inventory-item-desc");
    private final By inventoryItemPriceLabel = getElementByDataTest("inventory-item-price");
    private final By continueShoppingButton = id("continue-shopping");
    private final By checkoutButton = id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        waits.visible(continueShoppingButton);
    }

    public void continueShopping() {
        acts.click(continueShoppingButton);
        waits.gone(continueShoppingButton);
    }

    public CheckoutStepOnePage goToCheckout() {
        acts.click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }

    public boolean isCheckoutButtonEnabled() {
        return acts.isEnabled(checkoutButton);
    }

    public String getProductName() {
        return acts.getText(inventoryItemNameLabel);
    }

    public String getProductPrice() {
        return acts.getText(inventoryItemPriceLabel);
    }

    public int getNumberOfItemsInTheCart() {
        return acts.count(inventoryItem);
    }

}
