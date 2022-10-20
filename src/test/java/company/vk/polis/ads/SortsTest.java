package company.vk.polis.ads;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortsTest {

    private static final int DEFAULT_ARRAY_SIZE = 100_000;

    @Test
    public void insertionSortTest() {
        Integer[] arr = generateRandomIntegerArray(DEFAULT_ARRAY_SIZE);

        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);

        Sorts.insertionSort(arr);
        Arrays.sort(arrCopy);

        assertArraysEqual(arr, arrCopy);
    }

    @Test
    public void improvedInsertionSort() {
        Integer[] arr = generateRandomIntegerArray(DEFAULT_ARRAY_SIZE);
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);

        Sorts.improvedInsertionSort(arr);
        Arrays.sort(arrCopy);

        assertArraysEqual(arr, arrCopy);
    }

    @Test
    public void heapSort() {
        Integer[] arr = generateRandomIntegerArray(DEFAULT_ARRAY_SIZE);
        arr[0] = Integer.MIN_VALUE;
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);

        Sorts.heapSort(arr);
        Arrays.sort(arrCopy);

        assertArraysEqual(arr, arrCopy);
    }

    @Test
    public void mergeSort() {
        Integer[] arr = generateRandomIntegerArray(DEFAULT_ARRAY_SIZE);
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);

        Sorts.mergeSort(arr);
        Arrays.sort(arrCopy);

        assertArraysEqual(arr, arrCopy);
    }

    @Test
    public void quickSort() {
        Integer[] arr = generateRandomIntegerArray(DEFAULT_ARRAY_SIZE);
        Integer[] arrCopy = Arrays.copyOf(arr, arr.length);

        Sorts.quickSort(arr);
        Arrays.sort(arrCopy);

        assertArraysEqual(arr, arrCopy);
    }

    private static Integer[] generateRandomIntegerArray(int size) {
        Integer[] array = new Integer[size];
        final Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static <T> void assertArraysEqual(T[] array1, T[] array2) {
        assertEquals(array1.length, array2.length);
        for (int i = 0; i < array1.length; i++) {
            assertEquals(array1[i], array2[i]);
        }
    }
}
