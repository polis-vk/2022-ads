package company.vk.polis.ads.workshop.sortings;

public final class InsertionSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        insertionSort(array);
        return array;
    }

    private static <E extends Comparable<E>> void insertionSort(E[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(array[j + 1]) > 0) {
                swap(array, j, j + 1);
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
