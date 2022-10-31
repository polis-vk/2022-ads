package company.vk.polis.ads.workshop;

public class QuickSort {

    public static Integer[] quickSort(Integer[] array) {
        quickSort(array, 0, array.length);
        return array;
    }

    public static void quickSort(Integer[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int i = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }

    public static int partition(Integer[] array, int l, int r) {
        int pivotal = array[l];
        int i = l;

        for(int j = l + 1; j < r; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, l);

        return i;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
