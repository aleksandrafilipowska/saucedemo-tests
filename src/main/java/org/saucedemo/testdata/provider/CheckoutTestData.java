package org.saucedemo.testdata.provider;

import org.saucedemo.testdata.model.CheckoutFormData;

public class CheckoutTestData {
    public static final String CHECKOUT_FORM_MISSING_FIRST_NAME_ERROR_MESSAGE = "Error: First " +
            "Name is required";
    public static final String COMPLETE_HEADER_MESSAGE = "Thank you for your order!";
    public static final String COMPLETE_TEXT_MESSAGE = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    public static final CheckoutFormData CHECKOUT_FORM_DATA_01 =
            new CheckoutFormData("Ted", "Cruz", "45-789Z");
    public static final CheckoutFormData CHECKOUT_FORM_DATA_EMPTY =
            new CheckoutFormData("", "", "");

    private CheckoutTestData() {
    }
}
