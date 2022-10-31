package company.vk.polis.ads.workshop;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Sorts {

    public static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static Integer[] mergeSort(Integer[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return array;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
        return array;
    }

    public static void merge(Integer[] array, int fromInclusive, int mid, int toExclusive) {
        int indexLeft = fromInclusive;
        int indexRight = mid;

        int[] merged = new int[toExclusive - fromInclusive];
        int indexMerged = 0;
        while (indexLeft < mid && indexRight < toExclusive) {
            if (array[indexLeft] < array[indexRight]) {
                merged[indexMerged] = array[indexLeft];
                indexLeft++;
            } else {
                merged[indexMerged] = array[indexRight];
                indexRight++;
            }
            indexMerged++;
        }

        //сливаем результаты оставшиеся (если вдруг левый массив смерджился, а правый нет, например)
        while (indexLeft < mid) {
            merged[indexMerged] = array[indexLeft];
            indexLeft++;
            indexMerged++;
        }
        while (indexRight < toExclusive) {
            merged[indexMerged] = array[indexRight];
            indexMerged++;
            indexRight++;
        }

        for (int i = fromInclusive; i < toExclusive; i++) {
            array[i] = merged[i - fromInclusive];
        }
    }

    public static Integer[] insertionSort(Integer[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= fromInclusive && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public static Integer[] qSort(Integer[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return array;
        }

        int pivotIndex = randomPartition(array, fromInclusive, toExclusive);
        qSort(array, fromInclusive, pivotIndex);
        qSort(array, pivotIndex + 1, toExclusive);
        return array;
    }

    public static int partition(Integer[] array, int fromInclusive, int toExclusive) { // опорный элемент в начале массива
        int pivot = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (array[j] <= pivot) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    public static int randomPartition(Integer[] array, int fromInclusive, int toExclusive) {
        int rand = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, rand, fromInclusive);
        return partition(array, fromInclusive, toExclusive);
    }
}
