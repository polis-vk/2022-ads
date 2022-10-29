package company.vk.polis.ads.workshop;

public final class ImprovedInsertionSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length);
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            E currElem = array[i];
            if (currElem.compareTo(array[i - 1]) >= 0) {
                continue;
            }
            int insertionPosition = insertionPosition(array, currElem, fromInclusive, i);
            System.arraycopy(array, insertionPosition, array, insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = currElem;
        }
    }

    private static <E extends Comparable<E>> int insertionPosition(E[] array, E key, int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid].compareTo(key) >= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

