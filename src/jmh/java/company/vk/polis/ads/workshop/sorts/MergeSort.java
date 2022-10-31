package company.vk.polis.ads.workshop.sorts;

import java.util.Arrays;

public class MergeSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        mergeSort(array,0,array.length);
        return array;
    }

    private static <E extends Comparable<E>> void mergeSort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >>> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array,fromInclusive,mid,toExclusive);
    }

    private static <E extends Comparable<E>> void merge(E[] array, int fromInclusive, int mid, int toExclusive) {
        E[] unsortedArray = Arrays.copyOfRange(array, fromInclusive, toExclusive);
        int midForArray = mid - fromInclusive;
        int i = 0;
        int j = midForArray;
        int k = fromInclusive;

        for (; i < midForArray && j < unsortedArray.length; k++) {
            if (unsortedArray[i].compareTo(unsortedArray[j]) < 0) {
                array[k] = unsortedArray[i++];
            } else {
                array[k] = unsortedArray[j++];
            }
        }
        while (i < midForArray) {
            array[k++] = unsortedArray[i++];
        }
        while (j < toExclusive - fromInclusive) {
            array[k++] = unsortedArray[j++];
        }
    }
}
