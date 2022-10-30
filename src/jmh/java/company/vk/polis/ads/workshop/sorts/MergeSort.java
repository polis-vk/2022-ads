package company.vk.polis.ads.workshop.sorts;

import java.util.Arrays;

public class MergeSort {
    public static <T extends Comparable<T>> T[] sort(T[] array) {
        T[] tempElements = Arrays.copyOf(array, array.length);
        ;
        return sort(array, 0, array.length, tempElements);
    }

    private static <T extends Comparable<T>> T[] sort(T[] array, int fromInclusive, int toExclusive, T[] tempElements) {
        if (toExclusive - fromInclusive <= 1) {
            return array;
        }
        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;
        sort(array, fromInclusive, mid, tempElements);
        sort(array, mid, toExclusive, tempElements);
        merge(array, fromInclusive, mid, toExclusive, tempElements);
        return array;
    }

    private static <T extends Comparable<T>> void merge(T[] array, int fromInclusive, int mid, int toExclusive, T[] tempElements) {
        int i = fromInclusive;
        int j = mid;
        int index = fromInclusive;
        while (i < mid && j < toExclusive) {
            if (array[i].compareTo(array[j]) <= 0) {
                tempElements[index++] = array[i++];
            } else {
                tempElements[index++] = array[j++];
            }
        }
        while (i < mid) {
            tempElements[index++] = array[i++];
        }
        while (j < toExclusive) {
            tempElements[index++] = array[j++];
        }

        if (index >= fromInclusive)
            System.arraycopy(tempElements, fromInclusive, array, fromInclusive, index - fromInclusive);
    }
}

