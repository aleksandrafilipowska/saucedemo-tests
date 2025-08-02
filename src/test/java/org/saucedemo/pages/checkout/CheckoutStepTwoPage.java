package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.login.LoginPage;

import static org.saucedemo.utils.Utils.getElementByDataTest;

public class CheckoutStepTwoPage extends BasePage {
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");

    private final By inventoryItemNameLabel = getElementByDataTest("inventory-item-name");
    private final By inventoryItemPriceLabel = getElementByDataTest("inventory-item-price");
    private final By priceSubtotalLabel = getElementByDataTest("subtotal-label");
    private final By priceTaxLabel = getElementByDataTest("tax-label");
    private final By priceTotalLabel = getElementByDataTest("total-label");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutCompletePage completeCheckout() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public LoginPage cancelOrder() {
        click(cancelButton);
        return new LoginPage(driver);
    }
}
