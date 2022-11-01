package company.vk.polis.ads.workshop;

import java.util.Arrays;

public final class HeapSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        E[] temp = Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, 0, temp, 1, array.length);
        heapSort(temp);
        System.arraycopy(temp, 1, array, 0, array.length);
        return array;
    }

    static <E extends Comparable<E>> void heapSort(E[] array) {
        int size = array.length - 1;
        for (int k = size / 2; k >= 1; k--) {
            sink(array, k, size);
        }
        while (size > 1) {
            swap(array, 1, size--);
            sink(array, 1, size);
        }
    }

    static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static <E extends Comparable<E>> void sink(E[] array, int k, int size) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && array[j].compareTo(array[j + 1]) < 0) {
                j++;
            }
            if (array[k].compareTo(array[j]) >= 0) {
                break;
            }
            swap(array, k, j);
            k = j;
        }
    }
}
