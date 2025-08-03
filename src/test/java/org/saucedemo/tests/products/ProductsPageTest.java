package org.saucedemo.tests.products;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.checkout.CartPage;
import org.saucedemo.pages.login.LoginPage;
import org.saucedemo.pages.products.ProductsPage;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.saucedemo.testdata.provider.LoginTestData.STANDARD_USER;
import static org.saucedemo.testdata.provider.ProductTestData.TEST_PRODUCT_01;
import static org.saucedemo.testdata.provider.ProductTestData.TEST_PRODUCT_02;

@Epic("Products Page")
@Feature("Adding and Removing Products from the Cart")
@Severity(BLOCKER)
public class ProductsPageTest extends BaseTest {

    @BeforeEach
    public void loginBeforeEach() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsValidUser(STANDARD_USER);
    }

    @Story("User clicks on the 'Add to cart' button to add an item to the cart.")
    @Description("Validates that state of cart icon on Products Page changed.")
    @Test
    public void shouldSuccessfullyAddProductToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart(TEST_PRODUCT_01);
        assertEquals("1", productsPage.getCartBadgeItemsCount());
    }

    @Story("User adds two items and removes them on Products Page.")
    @Description("Validates that the state of cart icon on Products Page changes accordingly when" +
            " adding and removing items from the cart.")
    @Test
    public void shouldAddAndRemoveProductsFromCartViaProductsPage() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart(TEST_PRODUCT_01);
        assertEquals("1", productsPage.getCartBadgeItemsCount());

        productsPage.addToCart(TEST_PRODUCT_02);
        assertEquals("2", productsPage.getCartBadgeItemsCount());

        productsPage.removeFromTheCart(TEST_PRODUCT_01);
        assertEquals("1", productsPage.getCartBadgeItemsCount());

        productsPage.removeFromTheCart(TEST_PRODUCT_02);
        assertTrue(productsPage.isCartEmpty());
    }

    @Story("User adds 2 items on the Products Page and then enters the Cart and removes them.")
    @Description("Validates that the state of cart icon on Products Page changes accordingly when" +
            " adding items to the cart. After entering the cart, removing the items, and going " +
            "back to Products Page, the cart icon should be showing that the cart is empty.")
    @Test
    public void shouldAddProductsOnProductsPageAndRemoveThemInCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addToCart(TEST_PRODUCT_01);
        assertEquals("1", productsPage.getCartBadgeItemsCount());

        productsPage.addToCart(TEST_PRODUCT_02);
        assertEquals("2", productsPage.getCartBadgeItemsCount());

        CartPage cartPage = productsPage.goToShoppingCart();
        cartPage.removeFromTheCart(TEST_PRODUCT_01);
        cartPage.continueShopping();
        assertEquals("1", productsPage.getCartBadgeItemsCount());

        productsPage.goToShoppingCart();
        cartPage.removeFromTheCart(TEST_PRODUCT_02);
        cartPage.continueShopping();
        assertTrue(productsPage.isCartEmpty());
    }

}
