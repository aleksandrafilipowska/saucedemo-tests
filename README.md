## SauceDemo Test Automation Framework

This project showcases a test automation framework created as a purpose of a recruitment task.
Test scenarios included are meant to verify a set of functionalities on a [SauceDemo website](https://www.saucedemo.com/). 

### Tools used while building this framework

- Java 17
- Maven
- Selenium WebDriver
- JUnit5
- Allure

### Framework Structure

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
