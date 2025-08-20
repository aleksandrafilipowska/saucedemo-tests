package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.testdata.model.PriceSummary;
import org.saucedemo.testdata.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.id;
import static org.saucedemo.utils.SelectorUtils.getElementByDataTest;

public class CheckoutStepTwoPage extends BasePage {
    private final By cancelButton = id("cancel");
    private final By finishButton = id("finish");

    private final By inventoryItemNameLabel = getElementByDataTest("inventory-item-name");
    private final By inventoryItemDescLabel = getElementByDataTest("inventory-details-desc");
    private final By inventoryItemPriceLabel = getElementByDataTest("inventory-item-price");
    private final By priceSubtotalLabel = getElementByDataTest("subtotal-label");
    private final By priceTaxLabel = getElementByDataTest("tax-label");
    private final By priceTotalLabel = getElementByDataTest("total-label");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        waits.visible(inventoryItemNameLabel);
    }

    public void assertProductDetails(Product expectedProduct) {
        assertEquals(expectedProduct.name(), acts.getText(inventoryItemNameLabel));
        assertEquals(expectedProduct.price(), acts.getText(inventoryItemPriceLabel));
    }

    public void assertPriceSummary(PriceSummary priceSummary) {
        assertEquals(priceSummary.subtotal(), acts.getText(priceSubtotalLabel));
        assertEquals(priceSummary.tax(), acts.getText(priceTaxLabel));
        assertEquals(priceSummary.total(), acts.getText(priceTotalLabel));
    }

    public CheckoutCompletePage completeCheckout() {
        acts.click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public void clickCancelButton() {
        acts.click(cancelButton);
    }
}
