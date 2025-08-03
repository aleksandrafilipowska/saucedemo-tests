package org.saucedemo.pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.checkout.CartPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ProductsPage extends BasePage {
    private final By shoppingCartButton = getElementByDataTest("shopping-cart-link");
    private final By shoppingCartBadge = getElementByDataTest("shopping-cart-badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(shoppingCartButton));
    }

    public void addToCart(String productName) {
        By addToCartButton = By.id("add-to-cart-" + productName.toLowerCase().replace(" ", "-"));
        click(addToCartButton);
    }

    public void removeFromTheCart(String productName) {
        By removeFromTheCartButton = By.id("remove-" + productName.toLowerCase().replace(" ", "-"));
        click(removeFromTheCartButton);
    }

    public ProductDetailsPage goToProductDetails(String productId) {
        By goToProductDetailsButton = By.id("item-" + productId + "-title-link");
        click(goToProductDetailsButton);
        return new ProductDetailsPage(driver);
    }

    public String getCartBadgeItemsCount() {
        return getText(shoppingCartBadge);
    }

    public CartPage goToShoppingCart() {
        click(shoppingCartButton);
        return new CartPage(driver);
    }
}
