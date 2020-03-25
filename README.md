![Selenium tests](https://github.com/vladan-vachalec/protonmail-task/workflows/Selenium%20tests/badge.svg?branch=master)
# protonmail-task

### Description
This project implements selenium tests for https://beta.protonmail.com/settings/labels. The scope is to cover "create/edit/delete" a label

- Tech stack used: 
  - Code: Java, Gradle, Cucumber, Selenium
  - SCM and CI: Git, Github, Github Actions
  - Page Object pattern is used to describe different website screens
- CI pipeline is managed by Github Actions on PUSH or MERGE PR into master
- Test results are stored under Actions tab -> "Selenium tests" workflow -> Artifacts
- ProtonMail user credentials are fetched either from secrets.properties file or passed via command line. For build purposes via Github Actions, credentials are stored in repository Secrets and are passed to gradle during build
- Screenshots are being recorded in case of a test failure and attached to the final test report
- Web system notifications are being handled via Webdriver options

### Requirements
1. Java 8
2. Gradle 5
3. if not run on Mac, set ENV variable CHROMEWEBDRIVER. The variable should contain a directory, eg. /usr/me/projects/drivers where your "chromedriver" binary is stored

### Running tests locally using Gradle
**gradle clean cucumber** \[-Dbrowser={firefox/chrome} -DprotonUsername=={your_username} -DprotonPassword=={your_password}] (default browser is chrome)

**Example**

```sh
gradle cucumber
gradle clean cucumber -Dbrowser=firefox -DprotonUsername==myuser -DprotonPassword==mypass
```

### Running and debugging tests in IntelliJ
1. Go to: Run -> Edit Configuration -> Add Cucumber configuration
2. Set arguments as on the picture below and click on Apply
![Alt text](readme/cucumber-runner.png?raw=true "Title")
3. Run the test by selecting the configuration

### Reporting

- Test reports are stored in: target/cucumber-reports
- Example of a report with passed and failed test (failing test containing a screenshot)
![Alt text](readme/test-report-failed.png?raw=true "Title")
Label with name 'ProtonMailRulesChangedFailedTest' wasn't found
![Alt text](readme/test-failed-screenshot.png?raw=true "Title")

### How tests work
When the tests are being executed with the commands mentioned above, Cucumber main class searches all feature files in src/test/resources/*.feature. This feature file contains particular steps, which are implemented in StepsDefinition class. In StepsDefinition class, every single Page Object (LoginScreen, LabelsScreen etc.), which is needed for the test is initialised and all user like interactions are implemented  in the particular Page Object class 

### Additional test cases and improvements
- Implement selenium grid in the framework to distribute tests on multiple nodes
- Clean labels before each test

- Test cases: 
    - Add multiple labels and delete them
    - Edit a label to the same name and color as an existing one
    - Add label which already exists
    - Drag & Drop labels
    - Check if a label is present in the left menu
    - Assign labels to emails
    etc.