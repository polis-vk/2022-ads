package company.vk.polis.ads.workshop;

public class InsertionSort {
    public static Integer[] insertionSort(Integer[] array) {
        sort(array, array.length);
        return array;
    }

    private static void sort(Integer[] array, int toExclusive) {
        for (int i = 1; i < toExclusive; ++i) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
