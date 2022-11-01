package company.vk.polis.ads.workshop.Sorts;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int mid = partitionArray(array, fromInclusive, toExclusive);
        sort(array, mid + 1, toExclusive);
        sort(array, fromInclusive, mid);
    }

    private static <E extends Comparable<E>> int partitionArray(E[] array, int fromInclusive, int toExclusive) {
        int pivotIndex = toExclusive - 1;
        swap(array,
                ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive,
                pivotIndex);

        var pivot = array[pivotIndex];
        int j = fromInclusive - 1;
        for (int i = fromInclusive; i < pivotIndex; i++) {
            if (array[i].compareTo(pivot) < 0) {
                swap(array, i, ++j);
            }
        }
        swap(array, ++j, pivotIndex);
        return j;
    }

    private static <E> void swap(E[] array, int a, int b) {
        var temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
