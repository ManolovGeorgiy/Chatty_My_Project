## Selenium java framework

### Installation

- install [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- install [Gradle 8.5](https://gradle.org/releases/)
- install Allure:

* `brew install allure` for [Mac](https://brew.sh/)
    * Make sure Homebrew is installed.
    * In a `terminal`, run this command:
  > `brew install allure`

  > `/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"`


* `scoop install allure` for [Windows](https://scoop.sh/)

    - Make sure Scoop is installed.
      Make sure Java version 8 or above installed, and its directory is specified in the JAVA_HOME environment variable.
    - Open a PowerShell terminal (version 5.1 or later) and run:
    - In a `terminal`, run this command:

  > `scoop install allure`

  > `Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser`

  > `Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression`


* install dependencies:
* `./gradlew build`

### Run tests

### IMPORTAN ACTIONS

-for run test using testng need to do actions:

1. Open Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
2. Change Build and run using to `IntelliJ IDEA`
3. Change Run tests using to `IntelliJ IDEA`
4. Apply and Ok


- Run all tests `./gradlew clean test`

- Run tests using task `./gradlew clean nameOfTask` (task name can be found in `gradlew build` file)

### Config file

- Config file is located in `src/main/resources/config.properties`
- Need to add project url
- selenoid.state - false = `local run`, `true = remote run`
- selenoid.url - `url to selenoid`


### Allure reports

- After running tests, you can find reports in `build/reports/allure-report` folder

- for `Allurereport` in the terminal enter the command :

> `allure serve build/allure-results`

