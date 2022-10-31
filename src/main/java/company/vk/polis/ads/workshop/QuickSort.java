package company.vk.polis.ads.workshop;

import java.util.concurrent.ThreadLocalRandom;

public final class QuickSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        if (toExclusive <= fromInclusive)
            return;
        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int fromInclusive, int toExclusive) {
        int index = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        E tmp = array[fromInclusive];
        array[fromInclusive] = array[index];
        array[index] = tmp;

        E pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; j++) {
            if (array[j].compareTo(pivotal) <= 0) {
                tmp = array[++i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        tmp = array[i];
        array[i] = array[fromInclusive];
        array[fromInclusive] = tmp;
        return i;
    }
}
