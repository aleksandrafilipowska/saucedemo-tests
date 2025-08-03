package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.pages.products.ProductsPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CheckoutCompletePage extends BasePage {

    private final By backToProductsButton = By.id("back-to-products");
    private final By completeHeader = getElementByDataTest("complete-header");
    private final By completeText = getElementByDataTest("complete-text");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(backToProductsButton));
    }

    public ProductsPage goBackToProductsPage() {
        click(backToProductsButton);
        return new ProductsPage(driver);
    }

    public String getCompleteHeaderText() {
        return getText(completeHeader);
    }

    public String getCompleteTextContent() {
        return getText(completeText);
    }
}
