package in.naishe.algo.search;

import in.naishe.algo.sort.QuickSort;
import in.naishe.algo.utils.Util;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchForAiITest {
    @Test
    public void main() throws Exception {
        int[] a = {0, 1, 2, 3, 4, 23, 53, 55, 63, 90};
        QuickSort.quickSort(a);
        ArrayList<Integer> accum = new ArrayList<Integer>();
        Util.print(a);
        SearchForAiI.searchForAiI(a, accum);
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(4); expected.add(1); expected.add(0); expected.add(2); expected.add(3);
        System.out.println(expected);
        assertTrue(expected.equals(accum));
    }

    @Test
    public void searchForAiI() throws Exception {
    }

}