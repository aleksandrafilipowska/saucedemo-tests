# SauceDemo Java | Selenium | JUnit5 Test Automation Framework

This repository showcases a custom test automation framework, originally developed under time
constraints
for a recruitment task. The initial version focused on quickly delivering core functionality,
and it is now undergoing structured expansion to add advanced features, refine architecture, and
broaden test coverage.

Current test scenarios target a [SauceDemo website](https://www.saucedemo.com/), verifying key
e-commerce flows to demonstrate maintainable automation practices.

## Tools used while building this framework

- Java 17
- Maven
- Selenium WebDriver
- JUnit5
- Allure

## Planned Enhancements

- Automated test coverage expansion across core and edge-case workflows to validate broader
  functionality sets.
- Iterative refactoring to improve modularity and reduce code duplications.
- Cross-browser support with environment-specific configurations.
- CI/CD integration via GitHub Actions for automated builds and test runs.
- Expanded Allure reporting for clearer test visualization.
- Documentation updates and project wiki setup.

*Detailed roadmap is available in [backlog.md](./backlog.md).*

## Framework Structure

The framework follows a modular structure under src/test/java/org/saucedemo. Components include:

- `base` Core building blocks shared across the framework:

    - `BasePage` : Abstract class for all page objects. Holds shared WebDriver and WebDriverWait instances,
      and composes an `ElementActions` and `WaitActions` for interaction logic and explicit waits.

    - `BaseTest` : Abstract test base class handling WebDriver setup/teardown and basic config. Now uses only
      explicit waits (implicit wait removed) for more deterministic timing.

    - `WaitActions` : Dedicated helper for explicit waits (`visible`, `gone`, etc.), used for navigation
      guards and page-load anchors.

    - `ElementActions` : Encapsulates reusable, driver-bound element interaction methods (`click`, `sendKeys`,
      `getText`, etc.) with built-in waits. Also includes timeout-returning boolean explicit waits variants,
      replacing duplicated logic in individual page objects (`isPresent`, `isDisplayedNow`, etc.) for
      non-waiting state checks.

    - `SelectorUtils` - Static helper methods for building concise selectors without requiring driver or wait
      context.


- `pages` Page Object Model implementation. Each page/component encapsulates interactions relevant to that
  view.
  Divided into sub-packages for logical grouping:

    - `login` : Login flow (e.g., LoginPage)

    - `products` : Products-related views (ProductsPage, ProductDetailsPage)

    - `checkout` : Checkout workflow (CartPage, CheckoutStepOnePage, CheckoutStepTwoPage,
      CheckoutCompletePage)

    - `components` : UI components reused across pages (e.g., SidebarComponent)


- `testdata` Structured test data organization:

    - `model` : Java Records representing test data structures (e.g., Product, UserCredentials,
      PriceSummary, CheckoutFormData)

    - `provider` : Static utility classes providing reusable test data for each module (e.g.,
      LoginTestData, ProductTestData)

- `tests` Test classes grouped by functionality:

    - `authentication` : Login-related tests (AuthenticationTest, LoginPageUITest, SessionTest)

    - `products` : Product and product details page tests

    - `checkout` : All checkout flow tests

    - `sidebar` : Tests related to sidebar actions and state

This structure ensures context separation, reusability, and reduction in amount of issues related to
introducing changes.

## Test Architecture

The test architecture follows layered and data-driven approach with a few key principles:

#### Layered Design

- `BaseTest` handles WebDriver config (browser options), and setUp/tearDown.
- **Page Objects** encapsulate UI behavior. Assertions and interactions are abstracted away from
  test logic.
- **Test Classes** focus only on scenario-level steps and act as readable specification.

#### Data-Driven Tests

Test data is defined outside the test classes for clarity and reusability:

- Test input values and expected results are defined using `Record` classes (`Product`,
  `UserCredentials`, etc.).
- Centralized providers (e.g., `ProductTestData`) deliver predefined test entities into test
  classes.

This enables fast reuse of data across different test cases.

#### Assertions Strategy

- Assertions are embedded directly in test methods where contextual validation is needed (e.g.,
  after form submission).
- Critical path validations are inline to ensure early failure visibility.
- State checks now use non-waiting probes from `ElementActions` to avoid unnecessary timeouts, while
  navigation and load assertions use `WaitActions` for predictable timing.

#### Reporting & Visibility

- **Allure** reporting is integrated for readable test reports.
- Tests run in headless mode by default (Chrome), as defined in `BaseTest`. This can be modified by
  removing the `--headless` option in ChromeOptions.

## Design Patterns Used

The framework is built using the Page Object Model (POM).

Each page or component has its own class.
All methods that concern DOM interactions (clicking buttons, typing text, checking visibility) are
handled inside these classes.
Test classes do not, and shouldn't be responsible for that.

This solution keeps test code clean and easy to read, also making it easier to update selectors if
the UI changes due to app development.

## Running Tests

Tests use **JUnit 5** and run in **Chrome browser** by default.

To run all tests in headless mode:

```bash
mvn clean test -Dheadless=true
```

To generate and view Allure report:

```bash
mvn allure:report
mvn allure:serve
```


