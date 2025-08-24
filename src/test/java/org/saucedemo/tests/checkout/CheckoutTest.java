package org.saucedemo.tests.checkout;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.saucedemo.base.BaseTest;
import org.saucedemo.pages.checkout.CartPage;
import org.saucedemo.pages.checkout.CheckoutCompletePage;
import org.saucedemo.pages.checkout.CheckoutStepOnePage;
import org.saucedemo.pages.checkout.CheckoutStepTwoPage;
import org.saucedemo.pages.components.SidebarComponent;
import org.saucedemo.pages.products.ProductsPage;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.*;
import static org.saucedemo.testdata.provider.CheckoutTestData.*;
import static org.saucedemo.testdata.provider.LoginTestData.STANDARD_USER;
import static org.saucedemo.testdata.provider.ProductTestData.PRICE_SUMMARY_TP_01;
import static org.saucedemo.testdata.provider.ProductTestData.TEST_PRODUCT_01;
import static org.saucedemo.testdata.provider.TextConstants.CART_PAGE_HEADER;
import static org.saucedemo.testdata.provider.TextConstants.PRODUCTS_PAGE_HEADER;
import static org.saucedemo.utils.SelectorUtils.addToCart;

@Epic("Checkout")
@Feature("Order Placement Process")
@Severity(BLOCKER)
public class CheckoutTest extends BaseTest {

    @BeforeEach
    void login() {
        loginAs(STANDARD_USER);
    }

    @Story("User adds one item to cart and proceeds to successfully place the order. After that" +
            " they return to Products Page.")
    @Description("Validates that the user can add an item to the cart, and the item across the " +
            "whole checkout flow stays consistent with the one added by the user. The checkout " +
            "can be completed and the user can be redirected to Products Page by clicking 'Back " +
            "Home' button. The cart has 0 items inside.")
    @Test
    void shouldPlaceOrderSuccessfully() {
        ProductsPage productsPage = new ProductsPage(driver);
        addToCart(productsPage.acts, TEST_PRODUCT_01);
        assertEquals(1, productsPage.getCartBadgeItemsCount());

        CartPage cartPage = productsPage.goToShoppingCart();
        assertEquals(TEST_PRODUCT_01.name(), cartPage.getProductName());
        assertEquals(TEST_PRODUCT_01.price(), cartPage.getProductPrice());
        assertEquals(1, cartPage.getNumberOfItemsInTheCart());

        CheckoutStepOnePage checkoutStepOnePage = cartPage.goToCheckout();
        checkoutStepOnePage.fillOutCheckoutForm(CHECKOUT_FORM_DATA_01);

        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.clickContinueButton();
        checkoutStepTwoPage.assertProductDetails(TEST_PRODUCT_01);
        checkoutStepTwoPage.assertPriceSummary(PRICE_SUMMARY_TP_01);

        CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.completeCheckout();
        checkoutCompletePage.assertCheckoutCompleteMessages();

        checkoutCompletePage.goBackToProductsPage();
        assertEquals(PRODUCTS_PAGE_HEADER, productsPage.getSecondaryHeaderText());
        assertTrue(productsPage.isCartEmpty());

        productsPage.goToShoppingCart();
        assertEquals(0, cartPage.getNumberOfItemsInTheCart());
    }

    @Story("User is not able to complete the order without entering valid checkout form data.")
    @Description("Validates that the checkout can only proceed once the user fills in the " +
            "checkout form with the correct data.")
    @Test
    void shouldFailToCheckoutWithoutValidUserData() {
        ProductsPage productsPage = new ProductsPage(driver);
        addToCart(productsPage.acts, TEST_PRODUCT_01);
        assertEquals(1, productsPage.getCartBadgeItemsCount());

        CartPage cartPage = productsPage.goToShoppingCart();
        assertEquals(TEST_PRODUCT_01.name(), cartPage.getProductName());
        assertEquals(TEST_PRODUCT_01.price(), cartPage.getProductPrice());
        assertEquals(1, cartPage.getNumberOfItemsInTheCart());

        CheckoutStepOnePage checkoutStepOnePage = cartPage.goToCheckout();
        checkoutStepOnePage.fillOutCheckoutForm(CHECKOUT_FORM_DATA_EMPTY);
        checkoutStepOnePage.clickContinueButtonExpectFail();
        assertEquals(CHECKOUT_FORM_MISSING_FIRST_NAME_ERROR_MESSAGE,
                checkoutStepOnePage.getErrorMessage());
    }

    @Story("User cancels checkout process on the checkout form page, and is redirected back to " +
            "cart.")
    @Description("Validates that checkout flow can be cancelled on the checkout form point, and " +
            "retain cart data. The user is being redirected to the cart after clicking on cancel " +
            "button.")
    @Test
    void shouldReturnToCartAfterClickingCancelOnFormPage() {
        ProductsPage productsPage = new ProductsPage(driver);
        addToCart(productsPage.acts, TEST_PRODUCT_01);
        assertEquals(1, productsPage.getCartBadgeItemsCount());

        CartPage cartPage = productsPage.goToShoppingCart();
        assertEquals(TEST_PRODUCT_01.name(), cartPage.getProductName());
        assertEquals(TEST_PRODUCT_01.price(), cartPage.getProductPrice());
        assertEquals(1, cartPage.getNumberOfItemsInTheCart());

        CheckoutStepOnePage checkoutStepOnePage = cartPage.goToCheckout();
        checkoutStepOnePage.clickCancelButton();

        assertEquals(CART_PAGE_HEADER, cartPage.getSecondaryHeaderText());
        assertEquals(TEST_PRODUCT_01.name(), cartPage.getProductName());
        assertEquals(TEST_PRODUCT_01.price(), cartPage.getProductPrice());
        assertEquals(1, cartPage.getNumberOfItemsInTheCart());
    }

    @Story("User cancels checkout process on the last order step and is redirected to Products " +
            "Page")
    @Description("Validates that checkout flow can be cancelled on the last order step, and " +
            "retain cart data. The user is being redirected to the Products Page after clicking " +
            "on cancel button.")
    @Test
    void shouldReturnToProductsPageAfterClickingCancelOnLastOrderStep() {
        ProductsPage productsPage = new ProductsPage(driver);
        addToCart(productsPage.acts, TEST_PRODUCT_01);
        assertEquals(1, productsPage.getCartBadgeItemsCount());

        CartPage cartPage = productsPage.goToShoppingCart();
        assertEquals(TEST_PRODUCT_01.name(), cartPage.getProductName());
        assertEquals(TEST_PRODUCT_01.price(), cartPage.getProductPrice());
        assertEquals(1, cartPage.getNumberOfItemsInTheCart());

        CheckoutStepOnePage checkoutStepOnePage = cartPage.goToCheckout();
        checkoutStepOnePage.fillOutCheckoutForm(CHECKOUT_FORM_DATA_01);

        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.clickContinueButton();
        checkoutStepTwoPage.clickCancelButton();

        assertEquals(PRODUCTS_PAGE_HEADER, productsPage.getSecondaryHeaderText());

        assertEquals(1, productsPage.getCartBadgeItemsCount());

        productsPage.goToShoppingCart();
        assertEquals(TEST_PRODUCT_01.name(), cartPage.getProductName());
        assertEquals(TEST_PRODUCT_01.price(), cartPage.getProductPrice());
        assertEquals(1, cartPage.getNumberOfItemsInTheCart());
    }

    @Story("User is unable to proceed to checkout with an empty cart.")
    @Description("Validates that the checkout flow is disabled for an empty cart.")
    @Issue("ISSUE-002")
    @Test
    @Disabled
    void shouldNotBeAbleToCheckoutEmptyCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        SidebarComponent sidebarComponent = productsPage.openSidebar();
        sidebarComponent.resetAppState();

        CartPage cartPage = productsPage.goToShoppingCart();
        assertFalse(cartPage.isCheckoutButtonEnabled());
    }

}
