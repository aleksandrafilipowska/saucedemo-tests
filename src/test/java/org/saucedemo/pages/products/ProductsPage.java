package org.saucedemo.pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.checkout.CartPage;

import static org.saucedemo.utils.Utils.getElementByDataTest;

public class ProductsPage extends BasePage {
    private final By shoppingCartButton = getElementByDataTest("shopping-cart-link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String productName) {
        By addToCartButton = By.id("add-to-cart-" + productName.toLowerCase().replace(" ", "-"));
        click(addToCartButton);
    }

    public void removeFromTheCart(String productName) {
        By removeFromTheCartButton = By.id("remove-" + productName.toLowerCase().replace(" ", "-"));
        click(removeFromTheCartButton);
    }

    public CartPage goToShoppingCart() {
        click(shoppingCartButton);
        return new CartPage(driver);
    }
}
