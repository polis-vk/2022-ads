package company.vk.polis.ads.workshop.hometask;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;


import company.vk.polis.ads.workshop.hometask.sorts.ImprovedInsertionSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortsTest {

    @Test
    void insertionSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(100_000).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        // TODO: InsertionSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void improvedInsertionSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(100_000).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        ImprovedInsertionSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void mergeSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(100_000).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        // TODO: MergeSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void quickSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(100_000).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        // TODO: QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void heapSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(100_000).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        // TODO: HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }
}
