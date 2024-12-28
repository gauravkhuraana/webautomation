package org.example;

import ADO.Root;
import ADO.SingleInstance;
import ADOTestResultsUpdate.Results;
import ADOTestResultsUpdate.TestResultPayload;
import Utilities.ConfigLoader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class RestAPIForADOUpdate {

    public static Root root;
    public static String testplanid;
    public static String testsuiteid;
    public static String pattoken ;
    public String testpointid;
    public String testCaseID;

    ConfigLoader configLoader;

    @BeforeSuite
    public void beforeSuite()
    {
        configLoader = new ConfigLoader();
        testplanid = configLoader.getProperty("testplanid");
        testsuiteid = configLoader.getProperty("testsuiteid");
        pattoken = configLoader.getProperty("pattoken");
    }

    @Test(groups = "TESTCASE_22")
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("gaurav khurana")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    @AllureId("Authentication")
    public void OpenInChrome() {
        Allure.suite("Gaurav");
        Allure.epic("AuthenticationInsideEpic");

        WebDriver driver;
        System.out.println("starting chrome browser...");
        driver = new ChromeDriver();
        driver.get("https://udzial.com");
        driver.quit();
        System.out.println("chrome browser closed ...");

        Allure.attachment("data.txt", "This is the file content.");
        try (InputStream is = Files.newInputStream(Paths.get("failed.png"))) {
            Allure.attachment("image.png", is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(groups = "TESTCASE_26")
    public void OpenInEdge() {
//        WebDriver driver;
//        driver = new EdgeDriver();
//        System.out.println("starting edge browser...");
//        driver.get("https://udzial.com");
//        driver.quit();

    }

    @Test(groups = "TESTCASE_27")
    public void OpenInFirefox() {
        WebDriver driver;
        driver = new FirefoxDriver();
        System.out.println("starting firefox browser...");
        driver.get("https://udzial.com");
        driver.quit();

    }

    public int getTestCaseId(String[] groups) {

        int testid = 0;

        for (String group : groups) {
            if (group.contains("TESTCASE")) {
                testid = Integer.parseInt(group.replace("TESTCASE_", ""));
                break;
            }
        }
        return testid;
    }

//    //@AfterTest
//    public void CreateResultsJson(ITestResult result)
//    {
//        String[] groups = result.getMethod().getGroups();
//        int testid  = 0;
//        String Pass_Fail="" ;
//        System.out.println("Groups for test method " + result.getMethod().getMethodName() + ":");
//
//        for (String group : groups) {
//            if(group.contains("TESTCASE")) {
//              testid = Integer.parseInt(group.replace("TESTCASE_",""));
//            }
//        }
//        int status = result.getStatus();
//
//        System.out.println("Status of test method " + result.getMethod().getMethodName() + ":");
//        switch (status) {
//            case ITestResult.SUCCESS:
//                Pass_Fail = "Passed";
//                break;
//            case ITestResult.FAILURE:
//                Pass_Fail = "Failed";
//                break;
//        }
//
//
//        TestCase testCase = new TestCase();
//        testCase.outcome = Pass_Fail;
//        testCase.testCaseId = testid;
//        SingleInstance.getSingleInstance().addTestCase(testCase);
//    }

    @AfterMethod
    public void updateTestResults(ITestResult result) {

        String[] groups = result.getMethod().getGroups();
        System.out.println("Groups for test method " + result.getMethod().getMethodName() + ":");

        int testCaseID = getTestCaseId(groups);


        RestAssured.baseURI = "https://dev.azure.com";
        String path = "/khuraanagaurav/Automation/_apis/testplan/Plans/" + testplanid + "/Suites/" + testsuiteid + "/TestPoint?testCaseId=" + testCaseID + "&api-version=7.1-preview.2";

        Response response = RestAssured
                .given()
                .header("Authorization", "Basic "+pattoken)
                .header("Content-Type", "application/json")
                .when()
                .get(path);



        String responseBody = response.getBody().asString();
        System.out.println("The response is " + responseBody);

        JsonPath jsonPath = response.jsonPath();
        int testpointid = jsonPath.getInt("value[0].id");

        System.out.println("The testpoint id is " + testpointid);


        int status = result.getStatus();

        System.out.println("Status of test method " + result.getMethod().getMethodName() + ":");
        switch (status) {
            case ITestResult.SUCCESS:
                status = 2;
                break;
            case ITestResult.FAILURE:
                status = 3;
                break;
        }
        Results results = new Results();
        results.outcome = status;

        TestResultPayload testResultPayload = new TestResultPayload();
        testResultPayload.results = results;
        testResultPayload.id = testpointid;

        List<TestResultPayload> testResultPayloadList = new ArrayList<>();
        testResultPayloadList.add(testResultPayload);

        path = "/khuraanagaurav/Automation/_apis/testplan/Plans/"+ testplanid +"/Suites/"+ testsuiteid + "/TestPoint?includePointDetails=true&returnIdentityRef=true&api-version=7.1-preview.2";
        // Make the API request
        response = RestAssured
                .given()
                .header("Authorization", "Basic "+pattoken)
                .header("Content-Type", "application/json")
                .body(testResultPayloadList)
                .when()
                .patch(path);

        // Print Response
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());




        //     SingleInstance.getSingleInstance().addTestResultPayload(testResultPayload);
//        List<TestResultPayload> testResultPayloadsArray = new ArrayList<>();
//        testResultPayloadsArray.add(testResultPayload);

        //    System.out.println("The response is " + responseBody);
    }

    //@AfterSuite
    public void UpdateResult() throws IOException {
        Response response = RestAssured.given()
                .header("Authorization", "Basic " + pattoken)
                //.body(SingleInstance.getSingleInstance().getTestResultPayloadList())
                .contentType("application/json")
                .patch("/Automation/_apis/testplan/Plans/" + testplanid + "/Suites/" + testsuiteid + "/TestPoint?includePointDetails=true&returnIdentityRef=true&api-version=7.1-preview.2");
        //System.out.println("The request body is " + SingleInstance.getSingleInstance().getTestResultPayloadList().toArray());

        String responseBody = response.getBody().asString();

        System.out.println("The response body is " + responseBody);

        //@AfterSuite
//    public void CreateFile() throws IOException {
//
//        Root root = new Root();
//        root.suiteId = 0;
//        root.testCases.addAll(SingleInstance.getSingleInstance().getTestCases());
//
//        Gson gson = new Gson();
//        String jsonTestResults  = gson.toJson(root);
//        System.out.println(root.suiteId + " printing it ");
//        System.out.println(root.testCases + " printing it ");
//        System.out.println(jsonTestResults);
//
//
//        File file = new File("target/testresults.json");
//
//        // Create a BufferedWriter to write to the file
//        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//
//        // Write the content to the file
//        writer.write(jsonTestResults);
//
//        // Close the writer to release resources
//        writer.close();
//    }
    }
    }
