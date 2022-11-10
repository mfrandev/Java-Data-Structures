package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Main JUnit test runner
 */
@RunWith(Suite.class)
@SuiteClasses({LinkedListTests.class, ArrayListTests.class, HashTableTests.class, StackTests.class,
QueueTests.class, MinHeapTests.class, MaxHeapTests.class})
public class MainTestSuite {

}
