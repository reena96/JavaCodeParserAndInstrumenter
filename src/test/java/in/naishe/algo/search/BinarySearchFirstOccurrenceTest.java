package in.naishe.algo.search;

import in.naishe.algo.sort.QuickSort;
import in.naishe.algo.utils.Util;
import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class BinarySearchFirstOccurrenceTest {
    int[] a = {9, 3, 2, 6, 5, 6, 8, 7, 1, 12, 4};
    int keyVal, actualVal, expectedVal;
    @Test
    public void main() throws Exception {

    }

    @Test
    public void search() throws Exception {
        //out.println(QuickSort.partition(a,0,11));
        QuickSort.quickSort(a);
        System.out.println("Array Sorted: ");
        //1,2,3,4, 5, 6, 6,7,8,9,12
        Util.print(a);
        Util.drawHr();
        //System.out.println(BinarySearchFirstOccurrence.search(a, 6));
        keyVal = 6;
        actualVal = BinarySearchFirstOccurrence.search(a, keyVal);
        assertEquals(5, actualVal);
        System.out.println("Expected Value of index of "+keyVal+" : 5");
        System.out.println("Actual Value of index of "+keyVal+ " : " + actualVal);
        keyVal = 12;
        actualVal = BinarySearchFirstOccurrence.search(a, keyVal);
        assertEquals(10, actualVal);
        System.out.println("Expected Value of index of "+keyVal+" : 10");
        System.out.println("Actual Value of index of "+keyVal+ " : " + actualVal);
    }




}