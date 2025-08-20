package org.saucedemo.base;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.saucedemo.pages.login.LoginPage;
import org.saucedemo.testdata.model.UserCredentials;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;

public abstract class BaseTest {

    protected WebDriver driver;

    protected String baseURL() {
        return getProperty("baseURL");
    }

    protected boolean headless() {
        return parseBoolean(getProperty("headless"));
    }

    protected void loginAs(UserCredentials credentials) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsValidUser(credentials);
    }

    protected ChromeDriver startChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        if (headless()) options.addArguments("--headless=new");
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        return (ChromeDriver) driver;
    }

    @BeforeEach
    public void setUp() {
        driver = startChromeDriver();
        driver.get(baseURL());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
