package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.saucedemo.testdata.provider.CheckoutTestData.COMPLETE_HEADER_MESSAGE;
import static org.saucedemo.testdata.provider.CheckoutTestData.COMPLETE_TEXT_MESSAGE;

public class CheckoutCompletePage extends BasePage {

    private final By backToProductsButton = By.id("back-to-products");
    private final By completeHeader = getElementByDataTest("complete-header");
    private final By completeText = getElementByDataTest("complete-text");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(backToProductsButton));
    }

    public void goBackToProductsPage() {
        click(backToProductsButton);
    }

    public void assertCheckoutCompleteMessages() {
        assertEquals(COMPLETE_HEADER_MESSAGE, getText(completeHeader));
        assertEquals(COMPLETE_TEXT_MESSAGE, getText(completeText));
    }

}
