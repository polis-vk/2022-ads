package company.vk.polis.ads.workshop.sorts;

public class HeapSort {
    @SuppressWarnings("unchecked")
    public static Integer[] sort(Integer[] array) {
        Integer[] newArray = new Integer[array.length + 1];
        System.arraycopy(array, 0, newArray, 1, array.length);
        heapSort(newArray);
        System.arraycopy(newArray, 1, array, 0, array.length);
        return array;
    }

    private static void heapSort(Integer[] array) {
        int n = array.length - 1;
        //Строим кучу
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
        //Делаем сортировку
        while (n > 1) {
            swap(array, 1, n--);
            sink(array, 1, n);
        }
    }

    private static void sink(Integer[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k; //left child
            if (j < n && a[j].compareTo(a[j + 1]) < 0) j++; //right child
            if (a[k].compareTo(a[j]) >= 0) break; //invariant holds
            swap(a, k, j);
            k = j;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] a, int m, int n) {
        E temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }
}
