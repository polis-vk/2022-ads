package company.vk.polis.ads.workshop.hometask.sorts;

public class InsertionSort {

    public static Integer[] sort(Integer[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static void sort(Integer[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; ++i) {
            int key = array[i];
            int j = i - 1;
            while (j >= fromInclusive && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
