[![CI](https://github.com/aleksandrafilipowska/saucedemo-tests/actions/workflows/ci.yml/badge.svg)](https://github.com/aleksandrafilipowska/saucedemo-tests/actions/workflows/ci.yml)

#### [Allure Tests Report](https://aleksandrafilipowska.github.io/saucedemo-tests/)

## SauceDemo - Java · Selenium · JUnit 5

A UI test automation framework for https://www.saucedemo.com built for portfolio clarity and maintainability.

### Stack

Java 17 · Maven · Selenium WebDriver · JUnit 5 · Allure

### TL;DR - Run it

```bash
mvn clean test
```

### Generate & view Allure Report:

```bash

mvn allure:report
mvn allure:serve

```

### Config

`-Dheadless=true|false` (default set in `pom.xml`)

`-DbaseURL=https://www.saucedemo.com/` (default set in `pom.xml`)

##### Example:

```bash
mvn -Dheadless=false -DbaseURL=https://www.saucedemo.com/ test
```

### Architecture in short

- `BasePage` composes:

    - `ElementActions`: interactions + instant probes (isPresent, isDisplayedNow).

    - `WaitActions`: explicit waits (visible, clickable, gone) used for navigation/page-load guards.

- Pages: login, products, checkout, components (Sidebar). Each constructor asserts a page anchor through a
  wait action.

- Test data:

    - `testdata.model`: records used by pages and tests.

    - `testdata.provider`: constants/factories.

- Tests live in src/test and use parameterized cases where it adds value (e.g., invalid login via
  `@MethodSource`).

### CI (GitHub Actions)

- Java 17
- Maven cache
- Chrome on runner
- Artifacts: Surefire + Allure results
- Publish Allure Report to Pages 


