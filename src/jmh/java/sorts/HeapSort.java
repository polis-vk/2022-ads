package sorts;

public class HeapSort {

    public static Integer[] sort(Integer[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 0; --k) {
            sink(array, k, n);
        }
        while (n > 0) {
            swap(array, 0, n--);
            sink(array, 0, n);
        }
        return array;
    }

    private static void sink(Integer[] array, int k, int n) {
        while (2 * k + 1 <= n) {
            int j = 2 * k + 1;
            if (j < n && array[j].compareTo(array[j + 1]) < 0) ++j;
            if (array[k].compareTo(array[j]) >= 0) break;
            swap(array, k, j);
            k = j;
        }
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
