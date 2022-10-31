package company.vk.polis.ads.workshop;

public class HeapSort {

    static void makeHeap(Integer[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
    }

    static Integer[] heapSort(Integer[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        while (n > 1) {
            swap(a, 1, n--);
            sink(a, 1, n);
        }

        return a;
    }

    public static void sink(Integer[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j] < a[j + 1]) {
                j++;
            }
            if (a[k] >= a[j]) {
                break;
            }
            swap(a, k, j);
            k = j;
        }
    }

    public static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
