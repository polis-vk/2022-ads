package sorts;

public class ImprovedInsertionSort {
    public static Integer[] sort(Integer[] array) {
        sort(array, 0, array.length);
        return array;
    }

    private static void sort(Integer[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            Integer currElem = array[i];
            if (currElem.compareTo(array[i - 1]) >= 0) {
                continue;
            }
            int insertionPosition = insertionPosition(array, currElem, fromInclusive, i);
            System.arraycopy(array, insertionPosition, array, insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = currElem;
        }
    }

    private static int insertionPosition(Integer[] array, Integer key, int fromInclusive, int toExclusive) {
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
