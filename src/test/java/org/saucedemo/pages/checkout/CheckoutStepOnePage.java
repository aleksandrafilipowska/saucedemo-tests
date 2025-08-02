package org.saucedemo.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucedemo.base.BasePage;

public class CheckoutStepOnePage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void fillOutCheckoutForm(String firstName, String lastName, String postalCode) {
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(postalCodeInput, postalCode);
    }

    public CheckoutStepTwoPage clickContinueButton() {
        click(continueButton);
        return new CheckoutStepTwoPage(driver);
    }

}
