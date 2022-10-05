package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Main JUnit test runner
 */
@RunWith(Suite.class)
@SuiteClasses({LinkedListTests.class, ArrayListTests.class, HashTableTests.class})
public class MainTestSuite {
    
}
