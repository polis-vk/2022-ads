package company.vk.polis.ads.sortings;

public class HeapSort {

    public static Integer[] getSorted(Integer[] array) {
        sort(array);
        return array;
    }

    private static void sort(Integer[] array) {
        int n = array.length;
        Integer[] arrayForSort = new Integer[array.length + 1];
        System.arraycopy(array, 0, arrayForSort, 1, array.length);
        for (int k = n / 2; k >= 1; k--) {
            sink(arrayForSort, k, n);
        }
        while (n > 1) {
            swap(arrayForSort, 1, n--);
            sink(arrayForSort, 1, n);
        }
        System.arraycopy(arrayForSort, 1, array, 0, array.length);
    }

    private static void swap(Integer[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static void sink(Integer[] array, int k, int size) {
        int index = k;
        while (2 * index <= size) {
            int j = 2 * index;
            if (j < size && array[j] < array[j + 1]) j++;
            if (array[index] >= array[j]) break;
            swap(array, index, j);
            index = j;
        }
    }
}