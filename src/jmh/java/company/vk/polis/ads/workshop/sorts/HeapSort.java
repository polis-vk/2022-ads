package company.vk.polis.ads.workshop.sorts;

@SuppressWarnings("unchecked")
public class HeapSort {

    public static <E extends Comparable<E>> E[] sort(E[] a) {
        E[] array = (E[]) new Comparable[a.length + 1];

        System.arraycopy(a, 0, array, 1, a.length);

        int n = array.length - 1
                ;
        for (int i = n / 2; i >= 1; i--) {
            sink(array, i, n);
        }

        while (n > 1) {
            swap(array, 1, n--);
            sink(array, 1, n);
        }

        System.arraycopy(array, 1, a, 0, a.length);

        return a;
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <E extends Comparable<E>> void sink(E[] array, int i, int n) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && array[j + 1].compareTo(array[j]) > 0) {
                j++;
            }
            if (array[i].compareTo(array[j]) > 0) {
                break;
            }
            swap(array, i, j);
            i = j;
        }
    }
}
