package org.saucedemo.pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ProductDetailsPage extends BasePage {
    private final By inventoryItemNameLabel = getElementByDataTest("inventory-item-name");
    private final By inventoryItemDescLabel = getElementByDataTest("inventory-details-desc");
    private final By inventoryItemPriceLabel = getElementByDataTest("inventory-item-price");
    private final By addToCartButton = By.id("add-to-cart");
    private final By removeFromCartButton = By.id("remove");
    private final By goBackToProductsPageButton = By.id("back-to-products");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(inventoryItemNameLabel));
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void removeFromCart() {
        click(removeFromCartButton);
    }

    public void goBackToProductsPage() {
        click(goBackToProductsPageButton);
    }
}
