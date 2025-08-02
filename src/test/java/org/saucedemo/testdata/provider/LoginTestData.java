package org.saucedemo.testdata.provider;

import org.saucedemo.testdata.model.UserCredentials;

public class LoginTestData {
    public static final String FAILED_LOGIN_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    public static final String LOCKED_OUT_ERROR_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";
    public static final String EMPTY_CREDENTIALS_ERROR_MESSAGE = "Epic sadface: Username is required";

    public static final UserCredentials STANDARD_USER = new UserCredentials("standard_user", "secret_sauce");
    public static final UserCredentials INVALID_PASSWORD_USER = new UserCredentials("standard_user", "secret_but_wrong");
    public static final UserCredentials INVALID_USERNAME_USER = new UserCredentials("just_wrong_user", "secret_sauce");
    public static final UserCredentials ERROR_USER = new UserCredentials("error_user", "secret_sauce");
    public static final UserCredentials LOCKED_OUT_USER = new UserCredentials("locked_out_user", "secret_sauce");
    public static final UserCredentials NONEXISTENT_USER = new UserCredentials("nonexistent_user", "secret_sauce");

    private LoginTestData() {
    }
}
