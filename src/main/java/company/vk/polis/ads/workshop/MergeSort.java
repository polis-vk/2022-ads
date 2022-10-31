package company.vk.polis.ads.workshop;

public class MergeSort {
    public static <E extends Comparable<E>> E[] mergeSort(E[] array) {
        mergeSort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void mergeSort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int l = fromInclusive;
        int r = toExclusive;
        int mid = (r + l) / 2;
        mergeSort(array, l, mid);
        mergeSort(array, mid, r);

        merge(array, l, r);
    }

    private static <E extends Comparable<E>> void merge(E[] array, int fromInclusive, int toExclusive) {

    }
}
