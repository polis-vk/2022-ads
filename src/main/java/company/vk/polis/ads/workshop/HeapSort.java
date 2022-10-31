package company.vk.polis.ads.workshop;

public class HeapSort {
    public static Integer[] heapSort(Integer[] array) {
        int n = array.length - 1;
        makeHeap(array);
        while (n > 1) {
            swap(array, 1, n--);
            sink(array, 1, n);
        }
        return array;
    }

    private static void makeHeap(Integer[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
    }

    private static void sink(Integer[] array, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && array[j] < array[j + 1]) j++;
            if (array[k] > array[j]) break;
            swap(array, k, j);
            k = j;
        }
    }

    private static void swap(Integer[] array, int k, int j) {
        int temp = array[k];
        array[k] = array[j];
        array[j] = temp;
    }
}
