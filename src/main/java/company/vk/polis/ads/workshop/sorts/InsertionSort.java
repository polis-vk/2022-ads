package company.vk.polis.ads.workshop.sorts;

public class InsertionSort {
    public static Object sort(int[] array) {
        insertionSort(array, 0, array.length);
        return null;
    }

    private static void insertionSort(int[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            int j = i - 1;
            while (j >= 0 && array[i] < array[j]) {
                j--;
            }
            j++;
            int value = array[i];
            System.arraycopy(array, j, array, j + 1, i - j);
            array[j] = value;
        }
    }

}
