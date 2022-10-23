package company.vk.polis.ads.workshop.sorts;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static <E extends Comparable<E>> E[] quickSort(E[] array) {
        quickSort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int partition = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, partition);
        quickSort(array, partition + 1, toExclusive);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int fromInclusive, int toExclusive) {
        int randomInt = ThreadLocalRandom.current().nextInt(toExclusive-fromInclusive) + fromInclusive;
        swap(array, fromInclusive, randomInt);
        E pivotal = array[fromInclusive];
        int i = fromInclusive;

        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (array[j].compareTo(pivotal) <= 0) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static <E extends Comparable<E>> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
