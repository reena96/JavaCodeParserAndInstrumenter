package in.naishe.algo.search;

import in.naishe.algo.sort.QuickSort;
import in.naishe.algo.utils.Util;

/**
 * Find the first occurrence of a given number in an sorted array.
 * as discussed here http://code.naishe.in/2012/10/binary-search-first-occurrence-of.html
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class BinarySearchFirstOccurrence {

	public static void main(String[] args) {
		int[] a = Util.generateIntArrayWithDupes(17);
		QuickSort.quickSort(a);
		System.out.println("Array Sorted: ");
		Util.print(a);
		Util.drawHr();
		
		/*search random number until you find one*/
		boolean found = false;
		while(!found){
			int v;
			v= Util.rand.nextInt(Util.MAX_LIMIT);
			int at = search(a, v);
			if( at >= 0){
				System.out.println("Found '"+v+"' at index " + at);
				found = true;
			}else{
				System.out.println("Not Found: " + v);
			}
		}
	}

	public static int search(int[] a, int key) {
		return searchBin(a, key, 0, a.length-1);
	}

	private static int searchBin(int[] a, int key, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = low + (high-low)/2;
		if(key == a[mid]){
			if(mid > 0 && a[mid-1] == key)
				return searchBin(a, key, low, mid-1);
			else
				return mid;
		}else if(key < a[mid]){
			return searchBin(a, key, low, mid-1);
		}else{
			return searchBin(a, key, mid+1, high);
		}
	}

}
