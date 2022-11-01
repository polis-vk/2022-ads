package company.vk.polis.ads.workshop;

public class ImprovedInsertionSort {
    public static Integer[] improvedInsertionSort(Integer[] array) {
        sort(array, array.length);
        return array;
    }

    private static void sort(Integer[] array, int toExclusive) {
        for (int i = 1; i < toExclusive; i++) {
            Integer key = array[i];
            if (key >= array[i - 1]) {
                continue;
            }
            int insertionPosition = insertPosition(array, key, i);
            System.arraycopy(array, insertionPosition, array, insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = key;
        }
    }

    private static int insertPosition(Integer[] array, Integer key, int toExclusive) {
        int l = 0;
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