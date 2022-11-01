package company.vk.polis.ads.workshop;

public final class ImprovedInsertionSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length);
    }

    public static <E extends Comparable<E>> E[] sortReturn(E[] array) {
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
        int l = from;
        int r = to;
        while (l < r) {
            int mid = l + (r - l >> 1);
            var el = arr[mid];
            var cmp = el.compareTo(key);
            if (cmp >= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
