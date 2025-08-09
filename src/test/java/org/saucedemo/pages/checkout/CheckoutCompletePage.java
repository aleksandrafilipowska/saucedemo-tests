package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.saucedemo.testdata.provider.CheckoutTestData.COMPLETE_HEADER_MESSAGE;
import static org.saucedemo.testdata.provider.CheckoutTestData.COMPLETE_TEXT_MESSAGE;
import static org.saucedemo.utils.SelectorUtils.getElementByDataTest;

public class CheckoutCompletePage extends BasePage {

    private final By backToProductsButton = id("back-to-products");
    private final By completeHeader = getElementByDataTest("complete-header");
    private final By completeText = getElementByDataTest("complete-text");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(backToProductsButton));
    }

    public void goBackToProductsPage() {
        acts.click(backToProductsButton);
    }

    public void assertCheckoutCompleteMessages() {
        assertEquals(COMPLETE_HEADER_MESSAGE, acts.getText(completeHeader));
        assertEquals(COMPLETE_TEXT_MESSAGE, acts.getText(completeText));
    }

}
