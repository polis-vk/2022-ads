package company.vk.polis.ads.workshop.sortings;

public final class HeapSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        heapSort(array);
        return array;
    }

    private static <E extends Comparable<E>> void heapSort(E[] array) {
        buildHeap(array);
        int heapSize = array.length;
        while (heapSize > 0) {
            swap(array, 0, --heapSize);
            siftDown(array, 0, heapSize);
        }
    }

    private static <E extends Comparable<E>> void buildHeap(E[] array) {
        int length = array.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            siftDown(array, i, length);
        }
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <E extends Comparable<E>> void siftDown(E[] array, int k, int size) {
        while (2 * k + 1 < size) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int j = left;
            if (right < size && array[j].compareTo(array[right]) < 0) {
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
