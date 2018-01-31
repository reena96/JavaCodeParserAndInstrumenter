package in.naishe.algo.search;

import java.util.ArrayList;
import java.util.List;

import in.naishe.algo.sort.QuickSort;
import in.naishe.algo.utils.Util;

/**
 * a sorted array of distinct elements, A. Find the occurrences of elements where A[i] == i.
 * As discussed here http://code.naishe.in/2012/10/search-in-array-for-ai-i.html
 * 
 * NOTE: I have two implementations here,
 * 1. Gives you an idea how we find the element if any using binary seach
 * 2. Accumulates the values and then seaches left and right.
 * 
 * The main method uses the second approach.
 * 
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class SearchForAiI {

	public static void main(String[] args) {
		boolean found = false;
		while(!found){
			int[] a = Util.generateIntUniqueArray(10);
			QuickSort.quickSort(a);
			ArrayList<Integer> accum = new ArrayList<Integer>();
			searchForAiI(a, accum);
			Util.print(a);
			if(accum.size() > 0){
				found = true;
				System.out.println("Found A[i] == i, for " + accum);
			}else{
				System.out.println("NO such element where A[i] == i");
			}
		}
	}

	public static void searchForAiI(int[] a, List<Integer> accumulator) {
		searchForAiIAll(a, 0, a.length-1, accumulator);
	}

	/**
	 * Simple non accumulative, will find one where A[i] == i
	 * @param a
	 * @param low
	 * @param high
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int searchForAiI(int[] a, int low, int high) {
		if (low > high)
			return -1;
		int mid = low + (high-low)/2;
		if(a[mid] == mid)
			return mid;
		else if(mid > a[mid]) //implies all the elements in right are higher than their indexes
			return searchForAiI(a, low, mid-1);
		else //case where index less than the value, so we left must be containing values less than indexes.
			return searchForAiI(a, mid+1, high);
	}
	
	/**
	 * Stores the findings for all A[i] = i
	 * @param a
	 * @param low
	 * @param high
	 * @param accumulator 
	 * @return
	 */
	private static void searchForAiIAll(int[] a, int low, int high, List<Integer> accumulator) {
		if (low > high)
			return;
		int mid = low + (high-low)/2;
		if(a[mid] == mid){
			accumulator.add(mid);
			searchForAiIAll(a, low, mid-1, accumulator);
			searchForAiIAll(a, mid+1, high, accumulator);
		}else if(mid > a[mid]) //implies all the elements in right are higher than their indexes
			searchForAiIAll(a, low, mid-1, accumulator);
		else //case where index less than the value, so we left must be containing values less than indexes.
			searchForAiIAll(a, mid+1, high, accumulator);
	}

}