package org.example.results;

import org.testng.annotations.AfterSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import ADO.Root;
import ADO.SingleInstance;
import java.io.*;

public class TestResultHandler {
    private static final Logger logger = LoggerFactory.getLogger(TestResultHandler.class);

    @AfterSuite
    public void createResultFile() throws IOException {
        logger.info("Creating test results JSON file");
        
        Root root = new Root();
        root.suiteId = 0;
        root.testCases.addAll(SingleInstance.getSingleInstance().getTestCases());

        Gson gson = new Gson();
        String jsonTestResults = gson.toJson(root);
        logger.debug("Generated JSON: {}", jsonTestResults);

        File file = new File("target/testresults.json");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(jsonTestResults);
            logger.info("Successfully wrote test results to {}", file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to write test results", e);
            throw e;
        }
    }
}