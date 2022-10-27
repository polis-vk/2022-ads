package company.vk.polis.ads.workshop;

public final class HeapSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {

        E[] tempArray = (E[]) new Comparable[array.length + 1];
        System.arraycopy(array, 0, tempArray, 1, array.length);

        int n = tempArray.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(tempArray, k, n);
        }
        while (n > 1) {
            swap(tempArray, 1, n--);
            sink(tempArray, 1, n);
        }

        System.arraycopy(tempArray, 1, array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void sink(E[] array, int k, int size) {
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

    private static <E> void swap(E[] array, int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
