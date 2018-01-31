package in.naishe.algo.sort;

import java.io.PrintStream;
import java.util.Arrays;

import in.naishe.algo.utils.Util;

/**
 * This is slightly modified version of what discussed here:
 * http://code.naishe.in/2012/10/heap-and-heap-sort.html
 * 
 * 1. I am not returning null from parent, left, or right methods
 *    instead I am checking validity in the calling method.
 * 2. Since it's 0 based index, I am not leaving zeroth index empty.
 *    This had to be fixed by changing left to 2*i+1 and right
 *    to 2*i+2. 
 * @author Nishant Neeraj (http://naishe.in)
 *
 */
public class Heap {
	private int MAX_HEAP_SIZE = 64;
	private int[] heap = new int[MAX_HEAP_SIZE];
	private int heapSize = -1;
	
	public int parent(int i){
		return (i-1)/2;
	}
	
	public int left(int i){
		return 2*i+1;
	}
	
	public int right(int i){
		return 2*i+2;
	}
	
	public void insert(int key){
		if(heapSize >= MAX_HEAP_SIZE-1)
			throw new IllegalStateException("capacity full!");
		
		heap[++heapSize] = key;
		bubbleUp(heapSize);
	}

	private void bubbleUp(int i) {
		while(i > 0 && heap[parent(i)] < heap[i])
			swap(parent(i), i);
	}

	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public void heapify(int[] array){
		PrintStream out = System.out;
		this.MAX_HEAP_SIZE = array.length;
		this.heap = array;
		this.heapSize = array.length-1;
		for(int i = parent(array.length-1); i >= 0; i--)
			bubbleDown(i);
	}
	
	private void bubbleDown(int i){
		int maxIndex = i;
		int left = left(i);
		int right = right(i);
		
		if(left <= heapSize && heap[left] > heap[maxIndex])
			maxIndex = left;
		
		if(right <= heapSize && heap[right] > heap[maxIndex])
			maxIndex = right;
		
		if(maxIndex != i){
			swap(i, maxIndex);
			bubbleDown(maxIndex);
		}
	}
	
	public int[] heapSort(int[] a){
		heapify(a);
		for(int i=a.length-1; i>=1; i--){
			swap(0, heapSize);
			heapSize--;
			bubbleDown(0);
		}
		return heap;
	}
	
	
	// -- utility methods --
	
	public void print(){
		Util.print(Arrays.copyOfRange(heap, 0, heapSize+1));
	}

	public static void main(String[] args) {
		Heap h1 = new Heap();
		h1.insert(20);
		h1.insert(19);
		h1.insert(25);
		h1.insert(3);
		h1.insert(12);
		h1.insert(4);
		h1.insert(0);
		h1.insert(16);
		h1.print();
		
		int[] a = Util.generateIntArray(10);
		Util.print(a);
		Heap h = new Heap();
		h.heapSort(a);
		Util.print(a);
	}

}
