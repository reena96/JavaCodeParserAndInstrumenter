package in.naishe.algo.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountingSortTest {
    int[] a=  {96, 74, 92, 30, 41, 17, 23, 58, 6, 6, 95, 27};
    //int[] b= {66, 27, 56, 79, 55, 97, 88, 43, 90, 89, 65, 98};
    int[] c= {45, 99, 32, 39, 72, 41, 14, 13, 57, 1, 57, 76};
    //int[] d= {459, 64, 24, 80, 47, 53, 86, 54, 95, 66, 98, 65};
    @Test
    public void main() throws Exception {
        assertArrayEquals(new int[]{6, 6, 17, 23, 27, 30, 41, 58, 74, 92, 95, 96},CountingSort.countingSort(a));
        //assertArrayEquals(new int[]{66, 27, 56, 79, 55, 97, 88, 43, 90, 89, 65, 98},CountingSort.countingSort(b));
        assertArrayEquals(new int[]{1, 13, 14, 32, 39, 41, 45, 57, 57, 72, 76, 99},CountingSort.countingSort(c));
        //assertArrayEquals(new int[]{24, 47, 53, 54, 59, 64, 65, 66, 80, 86, 95, 98},CountingSort.countingSort(d));
    }


}