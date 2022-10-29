package company.vk.polis.ads.workshop.sorts;

public class QuickSort {
    public static Object sort(int[] array) {
        quickSort(array, 0, array.length);
        return null;
    }

    private static void quickSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int pos = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, pos);
        quickSort(array, pos + 1, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int pos = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++pos, j);
            }
        }
        swap(array, pos, fromInclusive);
        return pos;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
