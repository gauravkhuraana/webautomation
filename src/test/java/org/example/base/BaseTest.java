package org.example.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ADO.Root;
import ADO.SingleInstance;
import ADO.TestCase;
import com.google.gson.Gson;
import java.io.*;

public class BaseTest extends TestBase {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static Root root;

    @AfterMethod
    public void createResultsJson(ITestResult result) {
        String[] groups = result.getMethod().getGroups();
        int testId = 0;
        String testOutcome;

        logger.info("Processing results for test method: {}", result.getMethod().getMethodName());

        for (String group : groups) {
            if (group.startsWith("TESTCASE_")) {
                testId = Integer.parseInt(group.replace("TESTCASE_", ""));
            }
        }

        testOutcome = (result.getStatus() == ITestResult.SUCCESS) ? "Passed" : "Failed";
        logger.info("Test {} - Outcome: {}", testId, testOutcome);

        TestCase testCase = new TestCase();
        testCase.outcome = testOutcome;
        testCase.testCaseId = testId;
        SingleInstance.getSingleInstance().addTestCase(testCase);
    }
}