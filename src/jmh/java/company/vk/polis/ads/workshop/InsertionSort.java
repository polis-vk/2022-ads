package company.vk.polis.ads.workshop;

public class InsertionSort {

    public static void sort(Integer[] array) {
        insertionSort(array);
    }

    private static void insertionSort(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (value < array[j]) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = value;
        }
    }
}
