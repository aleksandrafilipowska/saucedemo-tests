package org.saucedemo.pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.checkout.CartPage;
import org.saucedemo.testdata.model.Product;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ProductsPage extends BasePage {
    private final By shoppingCartButton = getElementByDataTest("shopping-cart-link");
    private final By shoppingCartBadge = getElementByDataTest("shopping-cart-badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(shoppingCartButton));
    }

    public void addToCart(Product product) {
        By addToCartButton = By.id("add-to-cart-" + product.name().toLowerCase().replace(" ", "-"));
        click(addToCartButton);
    }

    public void removeFromTheCart(Product product) {
        By removeFromTheCartButton = By.id("remove-" + product.name().toLowerCase().replace(" ",
                "-"));
        click(removeFromTheCartButton);
    }

    public String getCartBadgeItemsCount() {
        return getText(shoppingCartBadge);
    }

    public boolean isCartEmpty() {
        return isElementNotPresent(shoppingCartBadge);
    }

    public CartPage goToShoppingCart() {
        click(shoppingCartButton);
        return new CartPage(driver);
    }
}
