package company.vk.polis.ads.workshop.sorts;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, 0, array.length);
    }

    private static <T extends Comparable<T>> T[] sort(T[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return array;
        }
        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
        return array;
    }

    private static <T extends Comparable<T>> int partition(T[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        T pivotal = array[fromInclusive];
        i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (array[j].compareTo(pivotal) <= 0) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

