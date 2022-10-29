package company.vk.polis.ads.workshop.sorts;

public final class InsertionSort {

    public static Integer[] sort(Integer[] array) {
        insertionSort(array);
        return array;
    }

    public static void insertionSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }
}
