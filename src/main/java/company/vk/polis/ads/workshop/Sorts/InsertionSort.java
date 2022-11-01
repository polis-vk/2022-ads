package company.vk.polis.ads.workshop.Sorts;

public class InsertionSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            E key = array[i];
            if (key.compareTo(array[i - 1]) >= 0) {
                continue;
            }
            int insertPos = insertionPosition(array, key, fromInclusive, i);
            System.arraycopy(array, insertPos, array, insertPos + 1, i - insertPos);
            array[insertPos] = key;
        }
    }

    private static <E extends Comparable<E>> int insertionPosition(E[] arr, E key, int from, int to) {
        int i = from;
        for (; i < to; i++) {
            if (arr[i].compareTo(key) > 0) {
                break;
            }
        }
        return i;
    }
}
