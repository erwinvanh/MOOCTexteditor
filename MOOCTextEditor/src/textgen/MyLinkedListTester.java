/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds)");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
        // Remove first element 
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		//remove last element
		a = list1.remove(1);
		assertEquals("Remove: check a is correct ", 42, a);
		assertEquals("Remove: check size is correct ", 1, list1.size());
		//remove element in between
		int b = longerList.get(1);
		int sizeNew = longerList.size() - 1;
		a = longerList.remove(1);
		assertEquals("Remove: check a is correct ", b, a);
		assertEquals("Remove: check size is correct ", sizeNew, longerList.size());
		// Index out of bounds test (1)
		try {
			list1.remove(-1);
			fail("Check out of bounds (1)");
		}
		catch (IndexOutOfBoundsException e) {
		}
		// Index out of bounds test (2)
		int i = list1.size() + 1;
		try {
			list1.remove(i);
			fail("Check out of bounds (2)");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		int expectedSize = list1.size() + 1;
		list1.add(99);
		assertEquals("Check new size", expectedSize, list1.size());
		assertEquals("Check value for new entry", (Integer)99, list1.get(list1.size() - 1));
		expectedSize = shortList.size() + 1;
		shortList.add("C");
		assertEquals("Check new size", expectedSize, shortList.size());
		assertEquals("Check value for new entry", "C", shortList.get(shortList.size() - 1));
  	    // Nullpointer
	    try {
	 		list1.add(null);
	 		fail("Check nullpointer");
	 	}
	 	catch (NullPointerException e) {
	 	}

	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size of shortList", 2, shortList.size());
		assertEquals("Size of LongerList", LONG_LIST_LENGTH, longerList.size());
		assertEquals("Size of EmptyList", 0, emptyList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // Succesfull add (position 0)
		int expectedSize = list1.size() + 1;
		list1.add(0,100);
		assertEquals("Check new size", expectedSize, list1.size());
		assertEquals("Check new value", (Integer)100, list1.get(0));
		assertEquals("Check shifted value (65)", (Integer)65, list1.get(1));

        // Succesfull add (position 2)
		expectedSize = list1.size() + 1;
		int shiftedValue = list1.get(2);
		list1.add(2,102);
		assertEquals("Check new size", expectedSize, list1.size());
		assertEquals("Check new value", (Integer)102, list1.get(2));
		assertEquals("Check shifted value (65)", (Integer)shiftedValue, list1.get(3));

		// Index out of bounds (1)
		try {
			list1.add(7, 100);
			fail("Check out of bounds (1)");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// Index out of bounds (2)
		try {
			list1.add(-1, 100);
			fail("Check out of bounds (2)");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// Index out of bounds (3)
	    try {
			list1.add(list1.size() + 1, 100);
			fail("Check out of bounds (3)");
		}
		catch (IndexOutOfBoundsException e) {

		}
  	    // Nullpointer
	    try {
	 		list1.add(1, null);
	 		fail("Check nullpointer");
	 	}
	 	catch (NullPointerException e) {
	 	}

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
        // Out of bounds (cornercases)
		try {
			list1.set(-1, 9);
			fail("Check out of bounds(1)");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			list1.set(list1.size(), 9);
			fail("Check out of bounds(2)");
		}
		catch (IndexOutOfBoundsException e) {
		}
		// Nullpointer
		try {
			list1.set(0, null);
			fail("Check nullpointer");
		}
		catch (NullPointerException e) {
		}
		// valid actions
		int oldValue = list1.get(1);
		int returnValue = list1.set(1, 999);
		assertEquals("Check old value against return-value", oldValue, returnValue);
		assertEquals("Check new value ", (Integer)999, list1.get(1));
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
