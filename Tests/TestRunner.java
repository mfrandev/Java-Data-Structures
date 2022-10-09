package Tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
 
/**
 * Main class to run the JUnit test suite from the command line
 */
public class TestRunner {
 
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MainTestSuite.class);
 
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
            failure.getException().printStackTrace();
        }
 
         
        System.out.println(result.getRunCount() - result.getFailureCount() + "/" + result.getRunCount() + " tests successful\nExecution completed in " + result.getRunTime() + "ms");
    }
}