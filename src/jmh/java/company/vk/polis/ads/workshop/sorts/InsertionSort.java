package company.vk.polis.ads.workshop.sorts;

public final class InsertionSort {

    public static <E extends Comparable<E>> E[] insertionSort(E[] array) {
        insertionSort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void insertionSort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            E key = array[i];
            int j = i - 1;
            while (j >= fromInclusive && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
