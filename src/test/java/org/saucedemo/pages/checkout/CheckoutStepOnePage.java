package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;
import org.saucedemo.testdata.model.CheckoutFormData;

import static org.openqa.selenium.By.id;

public class CheckoutStepOnePage extends BasePage {
    private final By firstNameInput = id("first-name");
    private final By lastNameInput = id("last-name");
    private final By postalCodeInput = id("postal-code");
    private final By continueButton = id("continue");
    private final By cancelButton = id("cancel");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        waits.clickable(firstNameInput);
    }

    public void fillOutCheckoutForm(CheckoutFormData checkoutFormData) {
        acts.sendKeys(firstNameInput, checkoutFormData.firstName());
        acts.sendKeys(lastNameInput, checkoutFormData.lastName());
        acts.sendKeys(postalCodeInput, checkoutFormData.postalCode());
    }

    public CheckoutStepTwoPage clickContinueButton() {
        acts.click(continueButton);
        return new CheckoutStepTwoPage(driver);
    }

    public void clickContinueButtonExpectFail() {
        acts.click(continueButton);
    }

    public void clickCancelButton() {
        acts.click(cancelButton);
    }

}
