package org.saucedemo.pages.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.checkout.CartPage;

import static java.lang.Integer.parseInt;
import static org.saucedemo.utils.SelectorUtils.getElementByDataTest;

public class ProductsPage extends BasePage {
    private final By shoppingCartButton = getElementByDataTest("shopping-cart-link");
    private final By shoppingCartBadge = getElementByDataTest("shopping-cart-badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
        waits.visible(shoppingCartButton);
    }

    public int getCartBadgeItemsCount() {
        if (isCartEmpty()) return 0;

        String badgeCount = acts.getText(shoppingCartBadge).trim();
        
        try {
            return parseInt(badgeCount);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isCartEmpty() {
        return !acts.isPresent(shoppingCartBadge);
    }

    public CartPage goToShoppingCart() {
        acts.click(shoppingCartButton);
        return new CartPage(driver);
    }
}
