package in.naishe.algo.utils;

import java.util.HashSet;
import java.util.Random;

public class Util {
	public static final Random rand = new Random();
	public static final int MAX_LIMIT = 100;
	
	public static int[] generateIntArray(int length){
		int[] a = new int[length];
		for(int i=0; i<length; i++)
			a[i] = rand.nextInt(MAX_LIMIT);
		return a;
	}
	
	public static void print(int[] a) {
		System.out.print("[");
		for(int i=0; i<a.length; i++){
			System.out.print(a[i]);
			if(i != a.length-1)
				System.out.print(", ");
		}
		System.out.println("]");
	}

	public static int[] swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return a;
	}

	public static int getMax(int[] a) {
		int max = Integer.MIN_VALUE;
		for(int i: a){
			if(i > max)
				max = i;
		}
		return max;
	}

	public static void drawHr() {
		System.out.println("--------------------------------------------------------------------------------");
	}

	public static int[] generateIntUniqueArray(int arrayLength) {
		HashSet<Integer> set = new HashSet<Integer>();
		while(set.size() < arrayLength){
			set.add(rand.nextInt(MAX_LIMIT));
		}
		int[] res = new int[arrayLength];
		int i = 0;
		for(int v: set){
			res[i++] = v;
		}
			
		return res;
	}

	public static void blankLine() {
		System.out.println("");
	}

	public static int[] generateIntArrayWithDupes(int length) {
		int[] a = new int[length];
		int i = 0;
		while (i < a.length){
			int v = rand.nextInt(MAX_LIMIT);
			int reps = rand.nextInt(a.length);
			for(int j=0; j<reps && i < a.length; j++){
				a[i++] = v;
			}
		}
		return a;
	}
}
