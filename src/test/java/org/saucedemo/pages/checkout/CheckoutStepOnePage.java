package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.testdata.model.CheckoutFormData;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CheckoutStepOnePage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        wait.until(visibilityOfElementLocated(firstNameInput));
    }

    public void fillOutCheckoutForm(CheckoutFormData checkoutFormData) {
        sendKeys(firstNameInput, checkoutFormData.firstName());
        sendKeys(lastNameInput, checkoutFormData.lastName());
        sendKeys(postalCodeInput, checkoutFormData.postalCode());
    }

    public CheckoutStepTwoPage clickContinueButton() {
        click(continueButton);
        return new CheckoutStepTwoPage(driver);
    }

    public void clickContinueButtonExpectFail() {
        click(continueButton);
    }

    public void clickCancelButton() {
        click(cancelButton);
    }

}
