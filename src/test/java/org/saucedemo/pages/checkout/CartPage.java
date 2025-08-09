package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.products.ProductsPage;
import org.saucedemo.testdata.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.saucedemo.utils.SelectorUtils.getElementByDataTest;

public class CartPage extends BasePage {
    private final By inventoryItemNameLabel = getElementByDataTest("inventory-item-name");
    private final By inventoryItemDescLabel = getElementByDataTest("inventory-item-desc");
    private final By inventoryItemPriceLabel = getElementByDataTest("inventory-item-price");
    private final By continueShoppingButton = id("continue-shopping");
    private final By checkoutButton = id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(continueShoppingButton));
    }

    public void continueShopping() {
        acts.click(continueShoppingButton);
        new ProductsPage(driver);
    }

    public CheckoutStepOnePage goToCheckout() {
        acts.click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }

    public boolean isCheckoutButtonEnabled() {
        return driver.findElement(checkoutButton).isEnabled();
    }

    public void assertProductDetails(Product expectedProduct) {
        assertEquals(expectedProduct.name(), acts.getText(inventoryItemNameLabel));
        assertEquals(expectedProduct.price(), acts.getText(inventoryItemPriceLabel));
    }

    public int getNumberOfItemsInTheCart() {
        return driver.findElements(getElementByDataTest("inventory-item")).size();
    }

    public void removeFromTheCart(Product product) {
        By removeFromTheCartButton = id("remove-" + product.name().toLowerCase().replace(" ",
                "-"));
        acts.click(removeFromTheCartButton);
    }

}
