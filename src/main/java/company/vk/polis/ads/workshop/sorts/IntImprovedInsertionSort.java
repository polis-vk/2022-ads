package company.vk.polis.ads.workshop.sorts;

public class IntImprovedInsertionSort {
    public static Object sort(int[] array) {
        sort(array, 0, array.length);
        return null;
    }

    public static void sort(int[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            int  key = array[i];
            if (key >= array[i - 1]) {
                continue;
            }
            int insertPos = insertionPosition(array, array[i], fromInclusive, i);
            System.arraycopy(array, insertPos, array, insertPos + 1, i - insertPos);
            array[insertPos] = key;
        }
    }

    private static <E extends Comparable<E>> int insertionPosition(int[] array, int key, int fromInclusive, int toExclusive) {
        int left = fromInclusive;
        int right = toExclusive;
        while (left < right) {
            int mid = (right + left) >> 1;
            int elem = array[mid];
            if (elem > key) {
                right = mid;
            } else if (elem < key) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

}
