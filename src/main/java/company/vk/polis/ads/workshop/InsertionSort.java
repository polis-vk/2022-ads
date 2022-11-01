package company.vk.polis.ads.workshop;

public class InsertionSort {

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, 0, array.length);
    }

    private static <T extends Comparable<T>> T[] sort(T[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= fromInclusive && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j--];
            }
            array[j + 1] = key;
        }
        return array;
    }
}
