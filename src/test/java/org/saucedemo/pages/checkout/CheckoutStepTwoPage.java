package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.testdata.model.PriceSummary;
import org.saucedemo.testdata.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CheckoutStepTwoPage extends BasePage {
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");

    private final By inventoryItemNameLabel = getElementByDataTest("inventory-item-name");
    private final By inventoryItemDescLabel = getElementByDataTest("inventory-details-desc");
    private final By inventoryItemPriceLabel = getElementByDataTest("inventory-item-price");
    private final By priceSubtotalLabel = getElementByDataTest("subtotal-label");
    private final By priceTaxLabel = getElementByDataTest("tax-label");
    private final By priceTotalLabel = getElementByDataTest("total-label");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(inventoryItemNameLabel));
    }

    public void assertProductDetails(Product expectedProduct) {
        assertEquals(expectedProduct.name(), getText(inventoryItemNameLabel));
        assertEquals(expectedProduct.price(), getText(inventoryItemPriceLabel));
    }

    public void assertPriceSummary(PriceSummary priceSummary) {
        assertEquals(priceSummary.subtotal(), getText(priceSubtotalLabel));
        assertEquals(priceSummary.tax(), getText(priceTaxLabel));
        assertEquals(priceSummary.total(), getText(priceTotalLabel));
    }

    public CheckoutCompletePage completeCheckout() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public void clickCancelButton() {
        click(cancelButton);
    }
}
