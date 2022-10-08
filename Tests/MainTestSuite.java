package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Main JUnit test runner
 */
@RunWith(Suite.class)
@SuiteClasses({LinkedListTests.class, ArrayListTests.class, HashTableTests.class, StackTests.class})
public class MainTestSuite {

}
