package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This class provides implementations of common sorting algorithms like merge
 * sort and quick sort for ArrayList objects containing elements comparable to
 * each other.
 * 
 * @author Khang Hoang Nguyen & Phuc Bao Do
 * @version Feb 22, 2024
 */
public class ArrayListSorter {
	private static int INSERTION_SORT_THRESHOLD;

	/**
	 * Sorts the given ArrayList using the merge sort algorithm.
	 *
	 * @param arr - the ArrayList to be sorted
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		INSERTION_SORT_THRESHOLD = 1;
		ArrayList<T> temp = new ArrayList<>(arr);// I modified here
		for (int i = 0; i < arr.size(); i++) {
			temp.add(null);
		}
		mergesort(arr, temp, 0, arr.size() - 1);
	}

	/**
	 * 
	 * Recursive method to sort the given ArrayList of integers using the merge sort
	 * algorithm.
	 * 
	 * @param arr   - the ArrayList of integers to be sorted
	 * @param temp  - a temporary ArrayList of integers used for merging
	 * @param left  - the left index of the subarray to be sorted
	 * @param right - the right index of the subarray to be sorted
	 */
	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int left,
			int right) {
		if (left >= right)
			return;
		if (right - left <= INSERTION_SORT_THRESHOLD) {
			insertionSort(arr, left, right);
		}
		int mid = left + (right - left) / 2;
		mergesort(arr, temp, left, mid);
		mergesort(arr, temp, mid + 1, right);
		merge(arr, temp, left, mid, right);

	}

	/**
	 * 
	 * Merges two sorted subarrays of the given ArrayList of integers.
	 * 
	 * @param arr   - the ArrayList of integers to be merged
	 * @param temp  - a temporary ArrayList of integers used for merging
	 * @param left  - the left index of the subarray to be merged
	 * @param mid   - the middle index of the subarray to be merged
	 * @param right - the right index of the subarray to be merged
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int left, int mid,
			int right) {
		for (int i = left; i <= right; i++) {
			temp.set(i, arr.get(i));
		}

		int i = left;
		int j = mid + 1;
		int k = left;

		while (i <= mid && j <= right) {
			if (temp.get(i).compareTo(temp.get(j)) <= 0) {
				arr.set(k, temp.get(i));
				i++;
			} else {
				arr.set(k, temp.get(j));
				j++;
			}
			k++;
		}

		while (i <= mid) {
			arr.set(k, temp.get(i));
			i++;
			k++;
		}

		while (j <= right) {
			arr.set(k, temp.get(j));
			j++;
			k++;
		}
	}

	/**
	 * Sorts the given ArrayList using the quick sort algorithm.
	 *
	 * @param arr the ArrayList to be sorted
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		quicksort(arr, 0, arr.size() - 1);
	}

	/**
	 * Helper method used by the quick sort algorithm.
	 * 
	 * 
	 * @param arr  - The ArrayList to be sorted.
	 * @param low  - The starting index of the subarray to sort.
	 * @param high - The ending index of the subarray to sort.
	 */
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int low, int high) {
		if (low < high) {

			int pivotIndex = getPivotMedianOfThree(low, high, arr);

			int partitionIndex = partition(arr, low, high, pivotIndex);
			quicksort(arr, low, partitionIndex - 1);
			quicksort(arr, partitionIndex + 1, high);

		}
	}

	/**
	 * Partitions an ArrayList around a chosen pivot element.
	 * 
	 * @param arr        - The ArrayList to be sorted.
	 * @param low        - The starting index of the subarray to partition.
	 * @param high       - The ending index of the subarray to partition.
	 * @param pivotIndex - The index of the pivot element.
	 * @return - The index where the pivot element is finally placed after
	 *         partitioning.
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int low, int high,
			int pivotIndex) {
		T pivotValue = arr.get(pivotIndex);
		Collections.swap(arr, pivotIndex, high);
		int i = low;
		for (int j = low; j < high; j++) {
			if (arr.get(j).compareTo(pivotValue) <= 0) {
				Collections.swap(arr, i, j);
				i++;
			}
		}
		Collections.swap(arr, i, high);
		return i;
	}

	/**
	 * Explain that this method generates a random pivot index within a specified
	 * range.
	 * 
	 * @param low  - The lower bounds of the range.
	 * @param high - The upper bounds of the range.
	 * @return - The randomly chosen pivot index.
	 */
	private static int getPivotRandom(int low, int high) {
		Random rnd = new Random();
		return rnd.nextInt(low, high);

	}

	/**
	 * Explain that this method chooses the middle element as the pivot.
	 * 
	 * @param low  - The lower bounds of the range.
	 * @param high - The upper bounds of the range.
	 * @return - The index of the middle element.
	 */
	private static int getPivot(int low, int high) {
		int pivotIndex = low + (high - low) / 2;
		return pivotIndex;
	}

	/**
	 * Explain that this method chooses the median of the first, middle, and last
	 * elements as the pivot.
	 * 
	 * @param low  - The lower bounds of the range.
	 * @param high - The upper bounds of the range.
	 * @param arr  - The ArrayList to be sorted.
	 * @return - The index of the median element.
	 */
	private static <T extends Comparable<? super T>> int getPivotMedianOfThree(int low, int high, ArrayList<T> arr) {
		int mid = low + (high - low) / 2;
		T lowValue = arr.get(low);
		T midValue = arr.get(mid);
		T highValue = arr.get(high);

		if (lowValue.compareTo(midValue) > 0) {
			if (midValue.compareTo(highValue) > 0) {
				return mid;
			} else if (lowValue.compareTo(highValue) > 0) {
				return high;
			} else {
				return low;
			}
		} else {
			if (lowValue.compareTo(highValue) > 0) {
				return low;
			} else if (midValue.compareTo(highValue) > 0) {
				return high;
			} else {
				return mid;
			}
		}
	}

	/**
	 * Explain that this method sorts a subarray using the insertion sort algorithm.
	 * 
	 * @param arr   - The ArrayList containing the subarray.
	 * @param left  - The lower bounds of the range.
	 * @param right - The upper bounds of the range.
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			T key = arr.get(i);
			int j = i - 1;
			while (j >= left && arr.get(j).compareTo(key) > 0) {
				arr.set(j + 1, arr.get(j));
				j--;
			}
			arr.set(j + 1, key);
		}
	}

	/**
	 * Explain that this method creates an ArrayList filled with integers in
	 * ascending order.
	 * 
	 * @param size - The desired size of the ArrayList.
	 * @return - The generated ArrayList containing ascending integers.
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> ascendingList = new ArrayList<>(size);
		for (int i = 1; i <= size; i++) {
			ascendingList.add(i);
		}
		return ascendingList;
	}

	/**
	 * Explain that this method creates an ArrayList filled with random permutations
	 * of integers from 1 to size.
	 * 
	 * @param size - The desired size of the ArrayList.
	 * @return - The generated ArrayList with permuted integers.
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> permutedList = generateAscending(size);
		Collections.shuffle(permutedList, new Random());
		return permutedList;
	}

	/**
	 * Explain that this method creates an ArrayList filled with integers in
	 * descending order.
	 * 
	 * @param size - The desired size of the ArrayList.
	 * @return - The generated ArrayList containing descending integers.
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> descendingList = new ArrayList<>(size);
		for (int i = size; i > 0; i--) { // I modified here,
			descendingList.add(i);
		}
		return descendingList;
	}
}
