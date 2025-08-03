package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.products.ProductsPage;
import org.saucedemo.testdata.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CartPage extends BasePage {
    private final By inventoryItemNameLabel = getElementByDataTest("inventory-item-name");
    private final By inventoryItemDescLabel = getElementByDataTest("inventory-item-desc");
    private final By inventoryItemPriceLabel = getElementByDataTest("inventory-item-price");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(continueShoppingButton));
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        return new ProductsPage(driver);
    }

    public CheckoutStepOnePage goToCheckout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }

    public boolean isCheckoutButtonEnabled() {
        return driver.findElement(checkoutButton).isEnabled();
    }

    public void assertProductDetails(Product expectedProduct) {
        assertEquals(expectedProduct.name(), getText(inventoryItemNameLabel));
        assertEquals(expectedProduct.price(), getText(inventoryItemPriceLabel));
    }

    public int getNumberOfItemsInTheCart() {
        return driver.findElements(getElementByDataTest("inventory-item")).size();
    }

}
