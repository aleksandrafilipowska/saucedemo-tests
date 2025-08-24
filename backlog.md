## Backlog

### Now (to ship in the next few sessions)

- **Screenshots on failure**  
  Add a JUnit 5 `TestWatcher` that captures screenshots on test failure; attach to Allure (or save to
  `target/screenshots`).

- **Allure polish**  
  Add `@Step` selectively (Login flow + critical checkout actions) and wire screenshot attachments in the
  watcher.
- **Expand test Coverage**

### Next

- **Parallel test execution**  
  Enable Surefire parallel methods/classes.
- **More parameterized coverage**  
  Expand `@MethodSource` to other negative cases (e.g., checkout form validation set).
- **Cross-browser Docker (Selenoid/Grid)**  
  Compose file + job to run against Selenoid.

### Later (experiments & demos)

- **GitLab CI**
- **Selenide rewrite (separate repo)**  
  Mirror a few flows to showcase two styles and advantages.

### Done (recent highlights)

- Introduced `WaitActions`: centralized waits; removed implicit waits.
- Added instant probes to `ElementActions` (`isPresent`, `isDisplayedNow`).
- Cleaned `pom.xml` scopes/properties.
- Added parameterized invalid-login test via `@MethodSource`.
- Added CI: GitHub Actions workflow.
- Added Allure report publishing to gh-pages.
- Cleaned up `README.md` and `backlog.md`.
- Replaced `driver.findElement(...).isEnabled()` and similar with `acts`/`waits`.
- Decoupled page assertions in`CheckoutCompletePage.assertCheckoutCompleteMessages()`, moved to getters on
  the page and assert in tests. 
