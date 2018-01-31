package in.naishe.algo.sort;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void quickSort() throws Exception {
        out.println("Quicksort function Test");
        int[] a = {9, 3, 2, 6, 8, 7, 1, 12, 4, 11, 10, 5};
        //out.println(QuickSort.partition(a,0,11));

        try
        {
            QuickSort.quickSort(a); //Can be used as even though this notation has lesser arguments it will call quickSort(a, 0, a.length-1);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
            out.println(e);
        }

    }

    @Test
    public void partition() throws Exception {
        out.println("Partition function Test");
        int[] a = {9, 3, 2, 6, 8, 7, 1, 12, 4, 11, 10, 5};
        //out.println(QuickSort.partition(a,0,11));
        assertEquals(4, QuickSort.partition(a, 0, 11));
    }
}