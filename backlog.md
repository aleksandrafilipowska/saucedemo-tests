# Project Backlog & Roadmap

This document outlines planned and in-progress enhancements for the **SauceDemo Java | Selenium |
JUnit5 Test Automation Framework**.
Items are prioritized for both immediate impact and long-term maintainability.

## 1. Test Coverage Expansion

- Add scenarios for all core e-commerce flows, including:
    - Full checkout with multiple products
    - Cart persistence across sessions
    - Checkout flow behaviour for different users
    - Products Page behaviour cases with high priority
- Cover non-critical but realistic workflows, including:
    - Sorting products by name and price
    - Adding/removing items from the cart in various sequences for different users
- Include API layer validation (if available for SauceDemo - this will probably have to be
  covered in Cypress project)
- Parameterized tests for multiple user accounts and browsers

## 2. Iterative Refactoring & Modularity Improvements

- Extract reusable helper classes for:
    - Repeated UI interactions (dropdowns, waits, etc.)
    - Common test data builders
    - Repeated assertion methods
- Split large PageObjects into logical components (LoadableComponents pattern where appropriate)
- Reduce duplicated locators and methods across page classes
- Review Test Data Management with possible improvements
- Review package structure for scalability

## 3. Cross-Browser Support & Environment Configurations

- Create base test classes for Chrome, Firefox, and optionally Edge
- Implement dynamic browser selection via Maven profiles
- Environment-specific configuration files (e.g., staging, prod-like)
- Validate compatibility for headless execution
- **Dockerized test execution** for isolated, reproducible runs across browsers and environments

## 4. CI/CD Integration

- Set up GitHub Actions workflow:
    - Triggered on push/PR to master
    - Parallel jobs for multiple browsers
    - Generate and upload Allure reports as build artifacts
- Exploration of additional useful tools that GitHub Actions could offer to enhance this and
  further projects

## 5. Allure Reporting Enhancements

- Add `@Step` annotations to key PageObject methods
- Configure attachments for screenshots on failure
- Improve test suite hierarchy and grouping in reports

## 6. Documentation & Knowledge Base

- Keep `README.md` updated as architecture evolves
- Create `Wiki` pages for:
    - Framework architecture overview
    - Adding new tests
    - Configuring environments
- Maintain this backlog to reflect ongoing priorities

## 7. Future Experiments & Technology Demos

These are not part of the core roadmap but will be explored to broaden skills and demonstrate
flexibility:

- **Selenide rewrite** of the current framework to highlight architectural differences and reduced
  boilerplate (set up as a separate project)
- **Cypress implementation** of core test cases to cover scenarios that Selenium cannot handle
  efficiently
- Exploration of additional reporting or analytics tools beyond Allure


