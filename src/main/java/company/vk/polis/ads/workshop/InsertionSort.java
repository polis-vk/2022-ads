package company.vk.polis.ads.workshop;

public class InsertionSort {

    public static <E extends Comparable<E>> E[] sortForBench(E[] array) {
        for (int i = 1; i < array.length; ++i) {
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(array[j + 1]) > 0) {
                swap(array, j, j + 1);
                j--;
            }
        }
        return array;
    }

    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
