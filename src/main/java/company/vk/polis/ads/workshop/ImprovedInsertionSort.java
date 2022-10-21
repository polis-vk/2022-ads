package company.vk.polis.ads.workshop;

public final class ImprovedInsertionSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length);
    }

    public static < E extends Comparable<E>> E[] sortForBench(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for(int i = fromInclusive + 1; i < toExclusive; ++i) {
            E key = array[i];
            if (key.compareTo(array[i - 1]) >= 0) {
                continue;
            }
            int insertionPosition = insertionPosition(array, key, fromInclusive, i );
            System.arraycopy(array, insertionPosition, array, insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = key;
        }
    }

    private static <E extends Comparable< E > > int insertionPosition(E[] array, E key, int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l < r) {
            int mid = (l + r) >> 1;
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
