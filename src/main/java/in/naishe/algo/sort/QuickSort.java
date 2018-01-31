package in.naishe.algo.sort;

import in.naishe.algo.utils.Util;

/**
 * Quick sort as discussed here:
 * http://code.naishe.in/2012/10/quicksort.html
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class QuickSort {
	
	public static void main(String[] args) {
		int[] a = Util.generateIntArray(12);
		Util.print(a);
		quickSort(a, 0, a.length-1);
		Util.print(a);
	}

	private static void quickSort(int[] a, int i, int j) {
		if(i <= j){
			int pivot = partition(a, i, j);
			quickSort(a, i, pivot - 1);
			quickSort(a, pivot+1, j);
		}
	}

	static int partition(int[] a, int i, int j) {
		int pivotVal = a[j];
		int leftIndex = i-1;
		for (int k = i; k <= j; k++){
			if(a[k] < pivotVal){
				leftIndex ++;
				if(leftIndex != k) //small optimization, do not swap if it's the same element
					Util.swap(a, leftIndex, k);
			}
		}
		Util.swap(a, leftIndex+1, j);
		return leftIndex+1;
	}

	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length-1);
	}

}