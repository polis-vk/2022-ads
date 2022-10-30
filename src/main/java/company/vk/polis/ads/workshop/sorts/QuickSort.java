package company.vk.polis.ads.workshop.sorts;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        quickSort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void quickSort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = randomPartition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }

    private static <E extends Comparable<E>> int randomPartition(E[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        return partition(array, fromInclusive, toExclusive);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int fromInclusive, int toExclusive) {
        E pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j].compareTo(pivotal) <= 0) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static <E> void swap(E[] array, int first, int second) {
        E temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

}
