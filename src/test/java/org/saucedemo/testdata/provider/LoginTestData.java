package org.saucedemo.testdata.provider;

import org.junit.jupiter.params.provider.Arguments;
import org.saucedemo.testdata.model.UserCredentials;

import java.util.stream.Stream;

import static org.saucedemo.testdata.provider.URLs.BASE_URL;

public final class LoginTestData {
    public static final String FAILED_LOGIN_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    public static final String LOCKED_OUT_ERROR_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";
    public static final String EMPTY_CREDENTIALS_ERROR_MESSAGE = "Epic sadface: Username is required";

    public static final String SESSION_COOKIE_NAME = "session-username";
    public static final String SESSION_COOKIE_STANDARD_USER_VALUE = "standard_user";

    public static final UserCredentials STANDARD_USER = new UserCredentials("standard_user", "secret_sauce");
    public static final UserCredentials INVALID_PASSWORD_USER = new UserCredentials("standard_user", "secret_but_wrong");
    public static final UserCredentials INVALID_USERNAME_USER = new UserCredentials("just_wrong_user", "secret_sauce");
    public static final UserCredentials ERROR_USER = new UserCredentials("error_user", "secret_sauce");
    public static final UserCredentials LOCKED_OUT_USER = new UserCredentials("locked_out_user", "secret_sauce");
    public static final UserCredentials NONEXISTENT_USER = new UserCredentials("nonexistent_user", "secret_sauce");

    public static String getNoUnauthenticatedAccessErrorMessage(String URL) {
        return "Epic sadface: You can only access '" + URL.replace(BASE_URL, "/") + "' when you " +
                "are logged in.";
    }

    public static Stream<Arguments> invalidCredentials() {
        return Stream.of(Arguments.of(INVALID_USERNAME_USER, FAILED_LOGIN_ERROR_MESSAGE),
                Arguments.of(INVALID_PASSWORD_USER, FAILED_LOGIN_ERROR_MESSAGE),
                Arguments.of(LOCKED_OUT_USER, LOCKED_OUT_ERROR_MESSAGE));
    }

    private LoginTestData() {
    }
}
