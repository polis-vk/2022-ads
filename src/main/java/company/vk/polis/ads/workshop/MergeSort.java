package company.vk.polis.ads.workshop;

import java.util.Arrays;

public final class MergeSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        E[] temp = Arrays.copyOf(array, array.length);
        sort(array, 0, array.length, temp);
        return array;
    }

    private static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive, E[] temp) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid, temp);
        sort(array, mid, toExclusive, temp);
        merge(array, fromInclusive, mid, toExclusive, temp);
    }

    private static <E extends Comparable<E>> void merge(E[] array, int fromInclusive, int mid, int toExclusive, E[] temp) {
        int i = fromInclusive;
        int j = mid;
        int position = fromInclusive;
        while (i < mid && j < toExclusive) {
            if (array[i].compareTo(array[j]) > 0) {
                temp[position++] = array[j++];
            } else {
                temp[position++] = array[i++];
            }
        }
        while (i < mid) {
            temp[position++] = array[i++];
        }
        while (j < toExclusive) {
            temp[position++] = array[j++];
        }
        if (position >= fromInclusive) {
            System.arraycopy(temp, fromInclusive, array, fromInclusive, position - fromInclusive);
        }
    }
}
