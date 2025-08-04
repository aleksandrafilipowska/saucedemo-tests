## SauceDemo Test Automation Framework

This project showcases a test automation framework created as a purpose of a recruitment task.
Test scenarios included are meant to verify a set of functionalities on a [SauceDemo website](https://www.saucedemo.com/). 

### Tools used while building this framework

- Java 17
- Maven
- Selenium WebDriver
- JUnit5
- Allure

## Framework Structure

The framework follows a modular structure under src/test/java/org/saucedemo. Components include:

- `base` Contains common logic shared across tests:

  - `BasePage` : Shared WebDriver and explicit wait logic for all page objects.

  - `BaseTest` : Abstract test class handling WebDriver setup/teardown and basic config.

- `pages` Page Object Model implementation. Each page/component encapsulates interactions and assertions relevant to that view.
          Divided into sub-packages for logical grouping:

  - `login` : Login flow (e.g., LoginPage)

  - `products` : Products-related views (ProductsPage, ProductDetailsPage)

  - `checkout` : Checkout workflow (CartPage, CheckoutStepOnePage, CheckoutStepTwoPage, CheckoutCompletePage)

  - `components` : UI components reused across pages (e.g., SidebarComponent)



- `testdata` Structured test data organization:

  - `model` : Java Records representing test data structures (e.g., Product, UserCredentials, PriceSummary, CheckoutFormData)

  - `provider` : Static utility classes providing reusable test data for each module (e.g., LoginTestData, ProductTestData)

- `tests` Test classes grouped by functionality:

  - `authentication` : Login-related tests (AuthenticationTest, LoginPageUITest, SessionTest)

  - `products` : Product and product details page tests

  - `checkout` : All checkout flow tests

  - `sidebar` : Tests related to sidebar actions and state

This structure ensures context separation, reusability, and reduction in amount of issues related to introducing changes.


## Test Architecture

The test architecture follows layered and data-driven approach with a few key principles:

#### Layered Design

- `BaseTest` handles WebDriver config (browser options), and setUp/tearDown.
- **Page Objects** encapsulate UI behavior. Assertions and interactions are abstracted away from test logic.
- **Test Classes** focus only on scenario-level steps and act as readable specification.

#### Data-Driven Tests

Test data is defined outside the test classes for clarity and reusability:

- Test input values and expected results are defined using `Record` classes (`Product`, `UserCredentials`, etc.).
- Centralized providers (e.g., `ProductTestData`) deliver predefined test entities into test classes.

This enables fast reuse of data across different test cases.

#### Assertions Strategy

- Assertions are embedded directly in test methods where contextual validation is needed (e.g., after form submission).
- Critical path validations are inline to ensure early failure visibility.

#### Reporting & Visibility

- **Allure** reporting is integrated for readable test reports.
- Tests run in headless mode by default (Chrome), as defined in `BaseTest`. This can be modified by removing the `--headless` option in ChromeOptions.


## Design Patterns Used

The framework is built using the Page Object Model (POM).

Each page or component has its own class.
All methods that concern DOM interactions (clicking buttons, typing text, checking visibility) are handled inside these classes.
Test classes do not, and shouldn't be responsible for that.

This solution keeps test code clean and easy to read, also making it easier to update selectors if the UI changes due to app development.


## Running Tests

Tests use **JUnit 5** and run in **headless mode** in **Chrome browser** by default.

To run all tests:

```bash
mvn clean test
```

To generate and view Allure report:

```bash
mvn allure:report
mvn allure:serve
```

## Notes

- Headless mode is set in `BaseTest` via ChromeOptions.
  To run tests without it, just remove the `options.addArguments("--headless");` line in `protected ChromeDriver startChromeDriver()` method located in `BaseTest.java`.

- Test results (logs + screenshots on failure) are stored in `target/allure-results/`
- All necessary `maven` configuration is already set up in `pom.xml` - you don't need to configure anything else, unless you really want to introduce your own changes.


