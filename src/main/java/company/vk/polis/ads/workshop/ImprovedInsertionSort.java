package company.vk.polis.ads.workshop;

public final class ImprovedInsertionSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length);
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            E key = array[i];
            if (key.compareTo(array[i - 1]) >= 0) {
                continue;
            }
            int insertPos = insertionPosition(array, array[i], fromInclusive, i);
            System.arraycopy(array, insertPos, array, insertPos + 1, i - insertPos);
            array[insertPos] = key;
        }
    }

    private static <E extends Comparable<E>> int insertionPosition(E[] array, E key, int fromInclusive, int toExclusive) {
        int left = fromInclusive;
        int right = toExclusive;
        while (left < right) {
            int mid = (right + left) >> 1;
            E elem = array[mid];
            int cmp = elem.compareTo(key);
            if (cmp > 0) {
                right = mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
