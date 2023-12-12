package org.example;


import ADO.Root;
import ADO.SingleInstance;
import ADO.TestCase;
import com.google.gson.Gson;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    public static Root root ;
    @Test (groups = "TESTCASE_22")
    public void OpenInChrome()
    {
        WebDriver driver;
        System.out.println("starting chrome browser...");
        driver = new ChromeDriver();
        driver.get("https://udzial.com");
        driver.quit();
        System.out.println("chrome browser closed ...");

    }
    @Test (groups = "TESTCASE_26")
    public void OpenInEdge()
    {
        WebDriver driver;
        driver = new EdgeDriver();
        System.out.println("starting edge browser...");
        driver.get("https://udzial.com");
        driver.quit();

    }

    @Test (groups = "TESTCASE_27")
    public void OpenInFirefox()
    {
        WebDriver driver;
        driver = new FirefoxDriver();
        System.out.println("starting firefox browser...");
        driver.get("https://udzial.com");
        driver.quit();

    }

    @AfterMethod
    public void CreateResultsJson(ITestResult result)
    {
        String[] groups = result.getMethod().getGroups();
        int testid  = 0;
        String Pass_Fail="" ;
        System.out.println("Groups for test method " + result.getMethod().getMethodName() + ":");

        for (String group : groups) {
            if(group.contains("TESTCASE")) {
              testid = Integer.parseInt(group.replace("TESTCASE_",""));
            }
        }
        int status = result.getStatus();

        System.out.println("Status of test method " + result.getMethod().getMethodName() + ":");
        switch (status) {
            case ITestResult.SUCCESS:
                Pass_Fail = "Passed";
                break;
            case ITestResult.FAILURE:
                Pass_Fail = "Failed";
                break;
        }


        TestCase testCase = new TestCase();
        testCase.outcome = Pass_Fail;
        testCase.testCaseId = testid;
        SingleInstance.getSingleInstance().addTestCase(testCase);
    }

    @AfterSuite
    public void CreateFile() throws IOException {

        Root root = new Root();
        root.suiteId = 0;
        root.testCases.addAll(SingleInstance.getSingleInstance().getTestCases());

        Gson gson = new Gson();
        String jsonTestResults  = gson.toJson(root);
        System.out.println(root.suiteId + " printing it ");
        System.out.println(root.testCases + " printing it ");
        System.out.println(jsonTestResults);


        File file = new File("target/testresults.json");

        // Create a BufferedWriter to write to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        // Write the content to the file
        writer.write(jsonTestResults);

        // Close the writer to release resources
        writer.close();
    }

}
