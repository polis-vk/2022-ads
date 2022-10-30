package company.vk.polis.ads.workshop.sorts;

public class InsertionSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        for (int i = 1; i < array.length; ++i) {
            E key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }
}
