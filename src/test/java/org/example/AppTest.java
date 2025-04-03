package org.example;


import ADO.Root;
import ADO.SingleInstance;
import ADO.TestCase;
import com.google.gson.Gson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
import org.example.base.TestBase; // Adjust the package name as needed

public class AppTest extends TestBase
{
    /**
     * Rigorous Test :-)
     */
    public static Root root ;
    @Test (groups = "TESTCASE_22")
    public void OpenInChrome()
    {
        try {
            System.out.println("Starting Chrome browser...");
            initializeDriver(new ChromeDriver());
            driver.get("https://udzial.com");
        } finally {
            tearDown();
            System.out.println("Chrome browser closed...");
        }
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

// Compare two lists and print the differences
public void printListDifferences(List<String> list1, List<String> list2)
{
    List<String> list1Copy = new ArrayList<>(list1);
    List<String> list2Copy = new ArrayList<>(list2);
    list1Copy.removeAll(list2);
    list2Copy.removeAll(list1);
    System.out.println("The difference between the two lists is: ");
    System.out.println("List1 - List2: " + list1Copy);
    System.out.println("List2 - List1: " + list2Copy);
}

public void functiontodetectevenoddnumber(int number)
{
    evenoddnumber(number);
    if(number % 2 == 0)
    {
        System.out.println("The number is even.");
    }
    else
    {
        System.out.println("The number is odd.");
    }
}

public void evenoddnumber(int number)
{
    if(number % 2 == 0)
    {
        System.out.println("The number is even.");
    }
    else
    {
        System.out.println("The number is odd.");
    }
}

public void identifyarmstrongnumer (int number)
{
    int c=0,a,temp;  
    int n=number;//It is the number to check armstrong  
    temp=n;  
    while(n>0)  
    {  
    a=n%10;  
    n=n/10;  
    c=c+(a*a*a);  
    }  
    if(temp==c)  
    System.out.println(temp+" is armstrong number");   
    else  
        System.out.println(temp+" is Not armstrong number");   
   }  

   public void functiontodetectpalindromenumber(int number)
   {
    int r,sum=0,temp;    
    int n=number;//It is the number variable to be checked for palindrome  
    
    temp=n;    
    while(n>0)    
    {    
     r=n%10;  //getting remainder  
     sum=(sum*10)+r;    
     n=n/10;    
    }    
    if(temp==sum)    
     System.out.println(temp+" is a palindrome number ");    
    else    
     System.out.println(temp+" is not a palindrome");   
   }  

   public void functiontodetectpalindromestring(String str)
   {
    String reverse = ""; // Objects of String class  
    int length = str.length();   
    for ( int i = length - 1; i >= 0; i-- )  
       reverse = reverse + str.charAt(i);  
    if (str.equals(reverse))  
       System.out.println(str+" is a palindrome.");  
    else  
       System.out.println(str+" is not a palindrome.");   
   }  

   public void functiontodetectfibonacciseries(int n)
   {
    int n1=0,n2=1,n3,i,count=n;    
    System.out.print(n1+" "+n2);//printing 0 and 1    
    
    for(i=2;i<count;++i)//loop starts from 2 because 0 and 1 are already printed    
    {    
     n3=n1+n2;    
     System.out.print(" "+n3);    
     n1=n2;    
     n2=n3;    
    }    
   }  

   public void functiontodetectfactorial(int n)
   {
    int i,fact=1;  
    int number=n;//It is the number to calculate factorial    
    for(i=1;i<=number;i++){    
        fact=fact*i;
    }
}
    

 public void compare2strings(String str1, String str2)
 {
    System.out.println("AppTest.compare2strings()");
    if(str1.equals(str2))
    {
        System.out.println("The two strings are equal");
    }
    else
    {
        System.out.println("The two strings are not equal");
    }
}


  public void functiontodetectprimenumber(int number)
  {
      int flag = 0;
      for(int i = 2; i <= number/2; ++i)
      {
          // condition for nonprime number
          if(number % i == 0)
          {
              flag = 1;
              break;
          }
      }

      if (flag == 0)
          System.out.println(number + " is a prime number.");
      else
          System.out.println(number + " is not a prime number.");
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
