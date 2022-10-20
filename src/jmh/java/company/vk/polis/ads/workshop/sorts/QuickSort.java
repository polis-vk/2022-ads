package company.vk.polis.ads.workshop.sorts;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("unchecked")
public class QuickSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        if (toExclusive - fromInclusive <= 1) {
            return;
        }

        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(fromInclusive, toExclusive);
        E pivot = array[i];
        swap(array, i, fromInclusive);
        i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (pivot.compareTo(array[j]) >= 0) {
                swap(array, j, ++i);
            }
        }
        swap(array, fromInclusive, i);
        return i;
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
