# 🚀 OrangeHRM Automation Testing Project

A complete **Selenium + TestNG automation framework** for the OrangeHRM Open Source Demo, built using **Java**, **Page Object Model (POM)**, **data-driven testing**, **parallel execution**, and **Allure reporting**.

---

## 🛠️ Tech Stack

* ☕ Java 17
* 🧪 Selenium WebDriver 4.27.0
* 🔬 TestNG 7.10.2
* 📦 Maven
* 🏗️ Page Object Model (POM)
* 📄 JSON + Jackson for test data
* ⚡ ThreadLocal WebDriver for parallel execution
* 📝 Log4j2 for logging
* 📊 Allure Report (with Epic / Feature / Story / Step annotations)
* 🔧 IntelliJ IDEA

---

## 🎯 Automated Test Scenarios

The framework covers **11 test cases**:

1. Login with valid credentials
2. Login with invalid credentials
3. Login with empty fields
4. Search for an existing employee (PIM)
5. Search for a non-existing employee
6. Open Add Employee page
7. Add employee with missing required field
8. End-to-End: Add a new employee successfully
9. Verify Admin → Add User page
10. Verify OrangeHRM footer and branding link
11. Verify sidebar menu items
    
---

## 🏛️ Framework Structure


```
src
├── main
│   └── java
│       ├── pages
│       │   ├── LoginPage.java            → @Step-annotated login actions
│       │   ├── DashboardPage.java        → Dashboard header verification
│       │   ├── PimPage.java              → PIM menu navigation
│       │   ├── EmployeeListPage.java     → Employee search & results
│       │   ├── AddEmployeePage.java      → Add Employee form actions
│       │   ├── AdminPage.java            → Admin menu / Add User page actions
│       │   ├── FooterPage.java           → Footer & branding link
│       │   └── SidebarPage.java          → Sidebar menu verification
│       └── utilities
│           └── ConfigReader.java        → Reads config.properties
│           ├── JsonDataReader.java      → Reads raw values from testdata.json
│           └── RetryAnalyzer.java       → Retries failed tests
│
├── test
│   ├── java
├   │   │── base
│   │   │   └── BaseTest.java            → Setup/teardown, login helper
│   │   ├── tests
│   │   │   ├── LoginTest.java
│   │   │   ├── EmployeeTest.java
│   │   │   ├── AdminTest.java
│   │   │   ├── FooterTest.java
│   │   │   └── SidebarTest.java
│   │   ├── Listeners/
│   │   │      ├── Execution.java           → IExecutionListener: logs suite start/finish
│   │   │      └── RetryListener.java       → Auto-attaches RetryAnalyzer to all tests   
│   │   └── utilities
│   │       └── TestDataProvider.java     → TestNG @DataProvider methods
│   │
│   └── resources
│       ├── config.properties            → base.url / browser / explicit.wait
│       ├── testdata.json                → test data (data-driven)
│       └── log4j2.xml                   → logging configuration
│
├── testng.xml                        → Parallel execution config
├── pom.xml                            → Maven dependencies
 
---

## ✨ Key Framework Features

### 🏗️ Page Object Model
All page locators and UI actions are separated from test classes to keep the framework clean, reusable, and maintainable.

### 🔄 Data-Driven Testing
Login and employee data are stored externally in `testdata.json` and supplied to TestNG tests through a custom TestDataProvider (using Jackson via JsonDataReader).

### ⚡ Parallel Execution
Tests run in parallel using:
```xml
[//]: # (parallel="tests" thread-count="3")
```
A `ThreadLocal<WebDriver>` ensures every parallel thread gets its own independent browser instance.

### ⏱️ Explicit Waits
The framework uses Selenium `WebDriverWait` and `ExpectedConditions` instead of `Thread.sleep()`.

### 🔁 Retry Mechanism
Failed tests are automatically retried up to **2 times** before being marked as failed, reducing false failures from flaky runs.

### 📝 Logging
Log4j2 logs each test step (INFO) and failure (ERROR) to `logs/automation.log`.

### 📊 Allure Reporting
Allure is integrated with TestNG to provide detailed execution reports and test steps.
Every test class is organized with:

@Epic("OrangeHRM") — top-level grouping
@Feature("Login" / "Admin" / "PIM" / "UI") — module grouping
@Story("TC# - ...") — individual test case grouping
@Description("...") — human-readable test summary
@Step("...") on every Page Object action — detailed step-by-step breakdown in the report

---
## ▶️ How to Run

### Prerequisites
- Java JDK 17+
- Maven
- Google Chrome

### Run all tests
```bash
mvn clean test
```

### Generate the Allure report
```bash
allure generate allure-results --clean -o allure-report
```

### Open the report
```bash
allure open allure-report
```

## 📁 Git & Reporting Notes
- The generated `allure-report` folder is committed to the repository for viewing the static report.
- The temporary `allure-results` folder is excluded from Git via `.gitignore`.

---

## 🌐 Application Under Test
**OrangeHRM Open Source Demo**
https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

---

## 👨‍💻 Author
**Mustafa Othman**
