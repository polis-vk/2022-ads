package company.vk.polis.ads;

import company.vk.polis.ads.workshop.ImprovedInsertionSort;
import company.vk.polis.ads.workshop.Sorts.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SortsTest {
    private final List<Integer> array = IntStream.generate(() -> new Random().nextInt())
            .limit(100_100).boxed().toList();
    private final Integer[] expected = array.stream().sorted().toArray(Integer[]::new);

    @Test
    void testImprovedInsertionSort() {
        assertArrayEquals(expected, ImprovedInsertionSort.sortReturn(array.toArray(Integer[]::new)));
    }

    @Test
    void testInsertionSort() {
        assertArrayEquals(expected, InsertionSort.sort(array.toArray(Integer[]::new)));
    }

    @Test
    void testMergeSort() {
        assertArrayEquals(expected, MergeSort.sort(array.toArray(Integer[]::new)));
    }

    @Test
    void testQuickSort() {
        assertArrayEquals(expected, QuickSort.sort(array.toArray(Integer[]::new)));
    }

    @Test
    void testHeapSort() {
        assertArrayEquals(expected, HeapSort.sort(array.toArray(Integer[]::new)));
    }
}
