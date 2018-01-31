package in.naishe.algo.sort;

import in.naishe.algo.utils.Util;

/**
 * Three way partitioning as discussed here: 
 * http://code.naishe.in/2012/10/dutch-national-flag-problem.html
 * refer: http://en.wikipedia.org/wiki/Dutch_national_flag_problem
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class ThreeWayPartition {

	public static void main(String[] args) {
		int[] A = {0, 5, -1, -5, 0, 1, 2, 0, -7, 5};
		partition(A, 0, 0);
		Util.print(A);
		//prints: [-7, -1, -5, 0, 0, 0, 2, 1, 5, 5]
	}

	private static void partition(int[] a, int low, int high) {
		int left = -1;
		int right = a.length;
		int i = 0;
		
		while(i < right){
			if(a[i] < low)
				Util.swap(a, i, ++left);
			else if(a[i] > high)
				Util.swap(a, i, --right);
			else
				i++;
		}
	}

}
