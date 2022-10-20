package company.vk.polis.ads.workshop.sorts;

public class DefaultInsertionSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            int j = i;
            while (j > 0 && array[j].compareTo(array[j - 1]) < 0) {
                swap(array, j - 1, j);
                j--;
            }
        }
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
