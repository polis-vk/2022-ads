package company.vk.polis.ads.workshop;

public final class QuickSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int fromInclusive, int toExclusive) {
        E pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j].compareTo(pivotal) <= 0) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
