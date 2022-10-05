package Tests;

import HashTable.HashTable;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class HashTableTests {

    HashTable<Integer, Integer> myTable;

    /**
     * Setup a hash table with 7 elements (1 before load factor increase)
     */
    @Before
    public void setupHashTable() {

        // Initialize a new hash table
        myTable = new HashTable<>();

        // Will represent values 7 - 1 
        int decrement = 7;

        // Associate values 7 - 1 with keys 1 - 7
        for(int i = 1; i < 8; i++) {
            myTable.add(i, decrement--);
        }

    }

    /**
     * After the test, set the hash table to null
     */
    @After
    public void takedownHashTable() {
        myTable.clear();
    }

    /**
     * Verify there are 7 elements in the table as initialized
     * Verify the keys 1 - 7 are associated with values 7 - 1 as initialized
     */
    @Test
    public void setupHashTableSuccessful() {

        // Confirm initialization was successful
        assertNotNull(myTable);

        // Check that isEmpty returns false with multiple elements in the table
        assertFalse(myTable.isEmpty());

        // Make sure there are only 7 elements in the table
        assertEquals(myTable.getSize(), 7);

         // Will represent values 7 - 1 
         int decrement = 7;

         // Check values 1 - 7 are associated with keys 7 - 1
         for(int i = 1; i < 8; i++) {
            assertEquals(decrement--, (int)myTable.get(i));
         }

         // Verify there are only 10 buckets in the table, i.e., load factor threshold not met
         assertEquals(myTable.getNumberOfBuckets(), 10);

    }

    /**
     * Test that a HashTable load factor of 0.75 or greater causes the number of buckets to double
     * Test that elements are correctly reorganized and accessible after re-bucketing
     * Also tests isEmpty
     */
    @Test
    public void testAddLoadFactorResizeWithStringObjects() {

        // Array of strings 15 elements long
        // Last element has 2 question marks so it is different than element index 6
        // i.e., creates a new key and does not replace value for old key
        String[] strings = {"Hello", "world", "how", "are", "you", "doing", "?", "The", "weather", "today", "is", "lovely", "isn't", "it", "??"};

        // Make a new table of strings so the hashing is more unpredictable
        HashTable<String, String> newTable = new HashTable<>();

        // Verify isEmpty returns true when there are no elements in the table
        assertTrue(newTable.isEmpty());

        // Add the first string to the table
        newTable.add(strings[0], strings[0]);

        // Verify isEmpty returns false once there is a single element in the table
        assertFalse(newTable.isEmpty());

        // Add the first 7 strings to the table
        for(int i = 1; i < 7; i++) {
            newTable.add(strings[i], strings[i]);
        }

        // Make sure there are only 7 elements in the table
        assertEquals(myTable.getSize(), 7);

        // Verify there are only 10 buckets in the table, i.e., load factor threshold not met
        assertEquals(myTable.getNumberOfBuckets(), 10);

        newTable.add(strings[7], strings[7]);

        // Validate there are 8 items in the table
        assertEquals(newTable.getSize(), 8);
        
        // Validate there are 20 buckets in the table
        assertEquals(newTable.getNumberOfBuckets(), 20);

        // Add 6 more elements so there are 14 in the table
        for(int i = 8; i < 14; i++) {
            newTable.add(strings[i], strings[i]);
        }

        // Validate there are 20 buckets in the table
        assertEquals(newTable.getNumberOfBuckets(), 20);

        // Validate there are 14 items in the table
        assertEquals(newTable.getSize(), 14);

        // Add a 15th element (15/20)
        newTable.add(strings[14], strings[14]);

        // Validate there are 15 items in the table
        assertEquals(newTable.getSize(), 15);

        // Validate there are 40 buckets in the table
        assertEquals(newTable.getNumberOfBuckets(), 40);

        // =================== Test that fetching values after re-bucketing works ===================
        for(int i = 0; i < strings.length; i++) {
            assertEquals(strings[i], newTable.get(strings[i]));
        }

    }

    /**
     * Test that the add function replaces the value for a key/value pair if the provided key already exists
     */
    @Test
    public void testAddDuplicateKeyReplacesValue() {

        //setupHashTableSuccessful verifies initial state

        // Replace the value for every key in the table
        for(int i = 1; i < 8; i++) {
            myTable.add(i, i);
        }

        // Verify the size of the table has not changed
        assertEquals(myTable.getSize(), 7);

        // Verify the values for every key have actually changed
        for(int i = 1; i < 8; i++) {
            assertEquals((int)myTable.get(i), i);
        }

    }

    /**
     * Test that the remove function actually deletes the node with the specified key from the table
     */
    @Test
    public void testRemove() {

        // Validate there are 7 nodes in the table
        assertEquals(myTable.getSize(), 7);

        // Validate the remove method returns the value of the node with the entered key
        assertEquals((int)myTable.remove(3), 5);

        // Validate the size of the table has decreased by 1
        assertEquals(myTable.getSize(), 6);

    }

    /**
     * Test that the remove function returns null when trying to delete a non-existant node
     */
    @Test 
    public void testRemoveOnNonExistantKey() {
        assertEquals(myTable.getSize(), 7);
        assertNull(myTable.remove(8));
        assertEquals(myTable.getSize(), 7);
    }

    /**
     * Test the case where deleting a node not at the start or end of a bucket deletes that and only that node
     */
    @Test
    public void testRemoveDoesNotDeleteOtherNodesByAccident() {

        // Create a new hash table with diabled bucket creation via load factor
        HashTable<String, String> newTable = new HashTable<>(true);

        // Array of 35 to give good probability of verifying that deleting an element preserves all unspecified elements in the table
        String[] strings = {"Hello", "world", "how", "are", "you", 
                            "doing", "?", "The", "weather", "today", 
                            "is", "lovely", "isn't", "it", "??", 
                            "I", "am", "also", "having", "quite", 
                            "a", "good", "time", "myself", "," 
                            ,"thanks", "filler1", "filler2", "filler3", "filler4"
                        };

        // Add all of the strings to the table
        for(int i = 0; i < strings.length; i++) {
            newTable.add(strings[i], strings[i]);
        }

        // Verify that the number of buckets did not grow like it would in normal conditions
        assertEquals(newTable.getNumberOfBuckets(), 10);

        // Verify the number of nodes in the table is equal to the number of strings in the list
        assertEquals(newTable.getSize(), strings.length);

        // Split the list above into 3 different lists:
        // Lists before the section to remove from the table
        String[] wordsBefore = {"Hello", "world", "how", "are", "you", "doing", "?"};

        // The section to remove from the table
        String[] wordsLikelyInMiddle = {"The", "weather", "today", 
                                        "is", "lovely", "isn't", "it", "??", 
                                        "I", "am", "also", "having", "quite", 
                                        "a", "good", "time", "myself", "," 
                                        ,"thanks", "filler1"
                                    };

        // The section to remove from the table
        String[] wordsAfter = {"filler2", "filler3", "filler4"};

        // Remove the words likely in the middle of a bucket from the table
        for(int i = 0; i < wordsLikelyInMiddle.length; i++) {

            // Assert equals shows no removal attempt had provided a non-existant key
            assertEquals(newTable.remove(wordsLikelyInMiddle[i]), wordsLikelyInMiddle[i]);
        }

        // Validate that the size of the table now equals the length of the original list of words minus the number of words removed
        assertEquals(newTable.getSize(), (long)strings.length - wordsLikelyInMiddle.length);

        // Validate that all of the expected words in the table are present
        for(String word: wordsBefore) {
            assertNotNull(newTable.get(word));
        }

        // Validate that all of the expected words in the table are present
        for(String word: wordsAfter) {
            assertNotNull(newTable.get(word));
        }

    }

    /**
     * Tests whether the HashTable get method returns null if the key provided is not in the table
     * NOTE: No test for get success case becaus all of the previous tests rely on this case
     */
    @Test
    public void testGetReturnsNullForNonExistantKey() {

        // 8 is not a valid key in the table, so should return null
        assertNull(myTable.get(8));

    }
    
}
