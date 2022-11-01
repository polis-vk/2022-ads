package company.vk.polis.ads.workshop;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] a, int l, int r) {
        if (l >= r - 1) {
            return;
        }
        int i = randomPartition(a, l, r);
        sort(a, l, i);
        sort(a, i + 1, r);
    }

    private static <E extends Comparable<E>> int randomPartition(E[] a, int l, int r) {
        int i = ThreadLocalRandom.current().nextInt(r - l) + l;
        swap(a, l, i);
        return partition(a, l, r);
    }

    private static <E extends Comparable<E>> int partition(E[] a, int l, int r) {
        E pivotal = a[l];
        int i = l;
        for (int j = l + 1; j < r; j++) {
            if (a[j].compareTo(pivotal) <= 0) {
                swap(a, ++i, j);
            }
        }
        swap(a, i, l);
        return i;
    }

    private static <E extends Comparable<E>> void swap(E[] a, int k1, int k2) {
        E temp = a[k1];
        a[k1] = a[k2];
        a[k2] = temp;
    }
}
