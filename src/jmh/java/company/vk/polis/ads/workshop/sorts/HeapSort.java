package company.vk.polis.ads.workshop.sorts;

import java.util.Arrays;

public class HeapSort {
    public static <T extends Comparable<T>> T[] sort(T[] array) {
        int n = array.length;
        T[] tempElements = Arrays.copyOf(array, n + 1);
        System.arraycopy(array, 0, tempElements, 1, n);

        for (int i = n / 2; i >= 1; i--) {
            sink(tempElements, i, n);
        }
        while (n > 1) {
            swap(tempElements, 1, n--);
            sink(tempElements, 1, n);
        }

        System.arraycopy(tempElements, 1, array, 0, array.length);
        return array;
    }

    private static <T extends Comparable<T>> void sink(T[] array, int index, int size) {
        int currIndex = index;
        int childIndex;
        while (currIndex * 2 <= size) {
            childIndex = currIndex * 2;
            if (childIndex < size && array[childIndex].compareTo(array[childIndex + 1]) < 0) {
                childIndex++;
            }
            if (array[currIndex].compareTo(array[childIndex]) >= 0) {
                break;
            }
            swap(array, currIndex, childIndex);
            currIndex = childIndex;
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
