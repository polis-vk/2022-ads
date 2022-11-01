package company.vk.polis.ads.workshop.sorting;

public class QuickSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        quickSort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void quickSort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int pos = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, pos);
        quickSort(array, pos + 1, toExclusive);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int fromInclusive, int toExclusive) {
        var pivot = array[fromInclusive];
        int i = fromInclusive;

        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j].compareTo(pivot) <= 0) {
                swap(array, ++i, j);
            }
        }

        swap(array, i, fromInclusive);
        return i;
    }

    private static <E extends Comparable<E>> void swap(E[] array, int firstIndex, int secondIndex) {
        var temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}