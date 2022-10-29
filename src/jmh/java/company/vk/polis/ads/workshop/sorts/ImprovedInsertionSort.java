package company.vk.polis.ads.workshop.sorts;

public final class ImprovedInsertionSort {

    public static Integer[] sort(Integer[] array) {
        improvedInsertionSort(array, 0, array.length);
        return array;
    }

    public static void improvedInsertionSort(Integer[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; ++i) {
            Integer key = array[i];
            if (key >= array[i - 1]) {
                continue;
            }
            int insertionPosition = insertionPosition(array, key, fromInclusive, i);
            System.arraycopy(array, insertionPosition, array, insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = key;
        }
    }

    private static int insertionPosition(Integer[] array, int key, int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l < r) {
            int mid = (l + r) >>> 1;
            var el = array[mid];
            final var cmp = el.compareTo(key);
            if (cmp > 0) {
                r = mid;
            } else if (cmp < 0) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
