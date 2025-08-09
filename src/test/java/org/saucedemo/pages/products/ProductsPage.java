package org.saucedemo.pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.checkout.CartPage;
import org.saucedemo.testdata.model.Product;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.saucedemo.utils.SelectorUtils.getElementByDataTest;

public class ProductsPage extends BasePage {
    private final By shoppingCartButton = getElementByDataTest("shopping-cart-link");
    private final By shoppingCartBadge = getElementByDataTest("shopping-cart-badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(shoppingCartButton));
    }

    public void addToCart(Product product) {
        By addToCartButton = id("add-to-cart-" + product.name().toLowerCase().replace(" ", "-"));
        acts.click(addToCartButton);
    }

    public void removeFromTheCart(Product product) {
        By removeFromTheCartButton = id("remove-" + product.name().toLowerCase().replace(" ",
                "-"));
        acts.click(removeFromTheCartButton);
    }

    public String getCartBadgeItemsCount() {
        return acts.getText(shoppingCartBadge);
    }

    public boolean isCartEmpty() {
        return acts.isElementNotPresent(shoppingCartBadge);
    }

    public CartPage goToShoppingCart() {
        acts.click(shoppingCartButton);
        return new CartPage(driver);
    }
}
