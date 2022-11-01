package company.vk.polis.ads.workshop.sorting;

public class InsertionSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        insertionSort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void insertionSort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            int j = i - 1;
            while (j >= 0 && array[i].compareTo(array[j]) < 0) {
                j--;
            }
            j++;

            var value = array[i];
            System.arraycopy(array, j, array, j + 1, i - j);
            array[j] = value;
        }
    }
}


