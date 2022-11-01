package company.vk.polis.ads.workshop;

public class MergeSort {
    static Object[] arrayCopy;

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        arrayCopy = new Object[array.length];
        sort(array, 0, array.length - 1);
        return array;
    }

    private static <E extends Comparable<E>> void sort(E[] a, int i, int j) {
        if (i >= j) {
            return;
        }

        int mid = i + (j - i) / 2;

        sort(a, i, mid);
        sort(a, mid + 1, j);
        merge(a, i, j, mid);
    }

    public static <E extends Comparable<E>> void merge(E[] a, int begin, int end, int mid) {
        int i = begin;
        int j = mid + 1;
        int k = begin;

        while (i <= mid && j <= end) {
            if (a[i].compareTo(a[j]) <= 0) {
                arrayCopy[k++] = a[i++];
            } else {
                arrayCopy[k++] = a[j++];
            }
        }
        while (i <= mid) {
            arrayCopy[k++] = a[i++];
        }
        while (j <= end) {
            arrayCopy[k++] = a[j++];
        }
        System.arraycopy(arrayCopy, begin, a, begin, end - begin + 1);
    }
}
