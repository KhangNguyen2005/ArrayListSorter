package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class provides tests for ArrayListSorter class
 * 
 * @author Khang Hoang Nguyen & Phuc Bao Do
 * @version Feb 22, 2024
 */
class ArrayListSorterTest {
	ArrayList<Integer> intList;
	ArrayList<String> strList;

	@BeforeEach
	void setUp() throws Exception {
		Random rnd = new Random();
		intList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			intList.add(rnd.nextInt());
		}

		strList = new ArrayList<>();
		strList.add("banana");
		strList.add("apple");
		strList.add("orange");
		strList.add("grape");
	}

	@Test
	void testMergeSortInt() {
		List<Integer> expected = new ArrayList<>(intList);
		Collections.sort(expected);

		ArrayListSorter.mergesort(intList);

		assertEquals(expected, intList);
	}

	@Test
	void testMergeSortString() {
		ArrayList<String> expected = new ArrayList<>();
		expected.add("apple");
		expected.add("banana");
		expected.add("grape");
		expected.add("orange");

		ArrayListSorter.mergesort(strList);
		assertEquals(strList, expected);

	}

	@Test
	void testQuickSortInt() {

		List<Integer> expected = new ArrayList<>(intList);
		Collections.sort(expected);

		// Sort the random list using merge sort
		ArrayListSorter.quicksort(intList);

		// Compare the sorted random list with the expected result
		assertEquals(expected, intList);
	}

	@Test
	void testQuickSortString() {
		ArrayList<String> expected = new ArrayList<>();
		expected.add("apple");
		expected.add("banana");
		expected.add("grape");
		expected.add("orange");

		ArrayListSorter.quicksort(strList);
		assertEquals(strList, expected);
	}

	@Test
	void testGenerateAscendingInt() {
		for (int size = 1; size <= 100; size++) {
			ArrayList<Integer> result = ArrayListSorter.generateAscending(size);
			assertEquals(size, result.size(), "Result size should match specified size");

			for (int i = 0; i < size; i++) {
				assertEquals(i + 1, result.get(i), "Element at index " + i + " should be " + (i + 1));
			}
		}
	}

	@Test
	void testGeneratePermutedInt() {
		ArrayList<Integer> permutedList = ArrayListSorter.generatePermuted(100);
		ArrayList<Integer> ascendingList = new ArrayList<>(permutedList);
		ArrayListSorter.quicksort(ascendingList);
		assertNotEquals(ascendingList, permutedList, "Generated list is not permuted");
	}

	@Test
	void testGenerateDescendingInt() {
		int size = 100;
		ArrayList<Integer> descendingList = ArrayListSorter.generateDescending(size);
		for (int i = 0; i < size - 1; i++) {
			assertTrue(descendingList.get(i) > descendingList.get(i + 1), "List is not in descending order");
		}
	}

	@Test
	void testMergeSortInsertionSortInt() {
		ArrayList<Integer> intList = new ArrayList<>();
		intList.add(5);
		intList.add(2);
		intList.add(7);
		intList.add(1);
		intList.add(9);
		ArrayListSorter.mergesort(intList);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		expected.add(5);
		expected.add(7);
		expected.add(9);

		assertEquals(intList, expected);

	}

	@Test
	void testQuickSortInsertionSortInt() {
		ArrayList<Integer> intList = new ArrayList<>();
		intList.add(5);
		intList.add(2);
		intList.add(7);
		intList.add(1);
		intList.add(9);
		ArrayListSorter.quicksort(intList);
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		expected.add(5);
		expected.add(7);
		expected.add(9);

		assertEquals(intList, expected);

	}

	// Edge cases (Large Amount of Elements, Null, Wrong Types, near-sorted...)
	@Test
	void testMergeSortEmptyList() {
		ArrayList<Integer> emptyList = new ArrayList<>();

		ArrayListSorter.mergesort(emptyList);

		assertTrue(emptyList.isEmpty());
	}

	@Test
	void testMergeSortNearSorted() {
		ArrayList<Integer> nearSortedList = new ArrayList<>();
		ArrayList<Integer> expected = new ArrayList<>();

		// Populate the near sorted list with almost sorted elements
		for (int i = 0; i < 100; i++) {
			nearSortedList.add(i);
			expected.add(i);
		}

		// Introduce disorder by swapping elements at indices 0 and 50
		int temp = nearSortedList.get(0);
		nearSortedList.set(0, nearSortedList.get(50));
		nearSortedList.set(50, temp);

		// Sort the near sorted list using quicksort
		ArrayListSorter.mergesort(nearSortedList);

		// Ensure that the lists are not equal as a whole
		assertEquals(expected, nearSortedList, "Lists are equal after sorting");
	}

	@Test
	void testMergeSortOneElementInList() {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> expected = new ArrayList<>();

		list.add(1);
		ArrayListSorter.mergesort(list);
		expected.add(1);
		assertEquals(list, expected);
	}

	@Test
	void testMergeSortDuplicateElement() {
		ArrayList<Integer> listWithDuplicates = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			listWithDuplicates.add(i % 10); // Add elements with duplicates
		}

		// Expected sorted list
		ArrayList<Integer> expected = new ArrayList<>(listWithDuplicates);
		Collections.sort(expected);

		// Sort the list using mergeSort
		ArrayListSorter.mergesort(listWithDuplicates);

		// Check if the list is sorted correctly
		assertEquals(expected, listWithDuplicates, "MergeSort with duplicate elements test failed");
	}

	@Test
	void testQuicksortEmptyList() {
		ArrayList<Integer> emptyList = new ArrayList<>();
		ArrayList<Integer> expected = new ArrayList<>();
		ArrayListSorter.quicksort(emptyList);
		assertEquals(expected, emptyList);
	}

	@Test
	void testQuickSortNearSorted() {
		ArrayList<Integer> nearSortedList = new ArrayList<>();
		ArrayList<Integer> expected = new ArrayList<>();

		// Populate the near sorted list with almost sorted elements
		for (int i = 0; i < 100; i++) {
			nearSortedList.add(i);
			expected.add(i);
		}

		// Introduce disorder by swapping elements at indices 0 and 50
		int temp = nearSortedList.get(0);
		nearSortedList.set(0, nearSortedList.get(50));
		nearSortedList.set(50, temp);

		// Sort the near sorted list using quicksort
		ArrayListSorter.quicksort(nearSortedList);

		assertEquals(expected, nearSortedList, "Lists are equal after sorting");
	}

	@Test
	void testQuickSortOneElementInList() {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> expected = new ArrayList<>();

		list.add(1);
		ArrayListSorter.quicksort(list);
		expected.add(1);
		assertEquals(list, expected);
	}

	@Test
	void testQuickSortDuplicateElement() {
		ArrayList<Integer> listWithDuplicates = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			listWithDuplicates.add(i % 10); // Add elements with duplicates
		}

		// Expected sorted list
		ArrayList<Integer> expected = new ArrayList<>(listWithDuplicates);
		Collections.sort(expected);

		// Sort the list using mergeSort
		ArrayListSorter.quicksort(listWithDuplicates);

		// Check if the list is sorted correctly
		assertEquals(expected, listWithDuplicates, "MergeSort with duplicate elements test failed");
	}

	@Test
	void testGenerateAscendingSizeZero() {
		ArrayList<Integer> listSize0 = ArrayListSorter.generateAscending(0);
		assertTrue(listSize0.isEmpty(), "Size 0 test failed");
	}

	@Test
	void testGenerateAscendingSizeLargeSize() {

		ArrayList<Integer> list = ArrayListSorter.generateAscending(1000);
		for (int i = 0; i < list.size(); i++) {
			assertEquals(i + 1, list.get(i).intValue(), "Large size element test failed at index " + i);
		}
	}

	@Test
	void testGeneratePermutedZero() {
		ArrayList<Integer> listSize0 = ArrayListSorter.generatePermuted(0);
		assertTrue(listSize0.isEmpty(), "Size 0 test failed");

	}

	@Test
	void testGeneratePermutedDuplicate() {
		ArrayList<Integer> listDuplicate = ArrayListSorter.generatePermuted(1000);
		HashSet<Integer> set = new HashSet<>(listDuplicate);
		assertEquals(1000, set.size(), "Duplicate element test failed");
	}

	@Test
	void testGeneratePermutedLargeSize() {

		ArrayList<Integer> listLargeSize = ArrayListSorter.generatePermuted(1000);
		assertEquals(1000, listLargeSize.size(), "Large size test failed");

	}

	@Test
	void testGenerateDescendingSizeZero() {
		ArrayList<Integer> listSize0 = ArrayListSorter.generateDescending(0);
		assertTrue(listSize0.isEmpty(), "Size 0 test failed");
	}

	@Test
	void testGenerateDescendingSizeLargeSize() {

		ArrayList<Integer> list = ArrayListSorter.generateDescending(1000);
		for (int i = list.size(); i > 0; i--) {
			assertEquals(i, list.get(list.size() - i).intValue(), "Large size element test failed at index " + i);
		}
	}

}
