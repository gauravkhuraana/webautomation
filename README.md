# Copilot InstructionsPipeline
ults in ADO using Syncer utility
## Project Overview
This is a Java-based web automation testing framework (not C# as initially stated) that uses:
- Selenium WebDriver for browser automation
- TestNG for test execution and reporting- Maven for project management- Allure for test reporting- REST API integration for ADO updates

## Project Structure
```
webautomation/
├── src/
│   ├── test/
│   │   └── java/
│   │       ├── org.example/
│   │       │   ├── AppTest.java
│   │       │   └── RestAPIForADOUpdate.java
│   │       └── ADO/
│   │           ├── Root.java
│   │           ├── TestCase.java
│   │           └── SingleInstance.java
└── pom.xml
```

## Key Components
1. Browser Tests
   - Chrome, Edge, and Firefox test implementations
   - Browser initialization and cleanup

2. Test Utilities
   - Mathematical functions (prime, palindrome, armstrong numbers)
   - String comparison utilities
   - List comparison methods

3. Test Results
   - JSON result generation
   - ADO integration for test result updates
   - Allure reporting integration

## Coding Conventions
1. Test Methods
   - Use TestNG annotations (@Test, @AfterMethod, etc.)
   - Group tests using meaningful names (e.g., "TESTCASE_22")
   - Include proper cleanup in test methods

2. Naming Conventions
   - Use camelCase for method names
   - Use PascalCase for class names
   - Use clear, descriptive names for tests

3. Error Handling
   - Implement try-finally blocks for WebDriver cleanup
   - Add proper exception handling
   - Include meaningful error messages

## Common Patterns
```java
@Test(groups = "TESTCASE_XX")
public void testMethod() {
    WebDriver driver = null;
    try {
        driver = new ChromeDriver();
        // Test implementation
    } finally {
        if (driver != null) {
            driver.quit();
        }
    }
}
```

## Tips for Copilot
- Suggest explicit waits over implicit waits
- Include WebDriver cleanup in finally blocks
- Generate proper test documentation
- Follow page object model pattern
- Implement proper exception handling