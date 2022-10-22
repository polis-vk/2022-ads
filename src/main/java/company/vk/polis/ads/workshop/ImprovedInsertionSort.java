package company.vk.polis.ads.workshop;

public final class ImprovedInsertionSort {
    public static <E extends Comparable<E>> Object sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++){
            final var key = array[i];
            if (array[i].compareTo(array[i - 1]) >= 0) {
                continue;
            }
            int currentIndex = insertionPosition(array, key, fromInclusive, i);
            System.arraycopy(array, currentIndex, array, currentIndex + 1, i - currentIndex);
            array[currentIndex] = key;
        }
    }

    public static <E extends Comparable<E>> int insertionPosition(E[] array, E key, int fromInclusive, int toExclusive) {
        int left = fromInclusive;
        int right = toExclusive;
        while (left < right) {
            int mid = (left + right) / 2;
            int cmp = array[mid].compareTo(key);
            if (cmp > 0) {
                right = mid;
            }
            else if (cmp < 0) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }
        return left;
    }
}
