package ADO;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SingleInstance {

    private static List<TestCase> listOfTests;
    private static SingleInstance singleInstance = null;

    private SingleInstance()
    {
        listOfTests = new ArrayList<TestCase>();
    }
    public static SingleInstance getSingleInstance()
    {
        if (singleInstance == null)
            singleInstance  = new SingleInstance();
        return singleInstance;
    }

    public void addTestCase(TestCase testCase)
    {
        listOfTests.add(testCase);
    }
    public List<TestCase> getTestCases()
    {
        return listOfTests;
    }
}
