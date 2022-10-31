package company.vk.polis.ads.workshop;

public class InsertionSort {

    public static <E extends Comparable<E>> E[] insertionSort(E[] array) {
        insertionSort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void insertionSort(E[] array, int fromInclusive, int toExclusive) {
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            E key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
