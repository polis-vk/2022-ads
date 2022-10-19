package company.vk.polis.ads.workshop.hometask;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;


import company.vk.polis.ads.workshop.hometask.sorts.HeapSort;
import company.vk.polis.ads.workshop.hometask.sorts.ImprovedInsertionSort;
import company.vk.polis.ads.workshop.hometask.sorts.InsertionSort;
import company.vk.polis.ads.workshop.hometask.sorts.MergeSort;
import company.vk.polis.ads.workshop.hometask.sorts.QuickSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortsTest {

    private static final int ELEMENTS_COUNT = 20_000;

    @Test
    void insertionSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(ELEMENTS_COUNT).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        InsertionSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void improvedInsertionSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(ELEMENTS_COUNT).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        ImprovedInsertionSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void mergeSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(ELEMENTS_COUNT).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        MergeSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void quickSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(ELEMENTS_COUNT).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        QuickSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void heapSortTest() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(ELEMENTS_COUNT).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        HeapSort.sort(actual);
        assertArrayEquals(expected, actual);
    }
}
