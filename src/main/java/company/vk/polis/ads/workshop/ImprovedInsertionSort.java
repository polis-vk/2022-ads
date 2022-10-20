package company.vk.polis.ads.workshop;

public final class ImprovedInsertionSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            E element = array[i];
            int positionToInsert = positionToInsert(array, element, fromInclusive, i);
            System.arraycopy(array, positionToInsert, array, positionToInsert + 1, i - positionToInsert);
            array[positionToInsert] = element;
        }
    }

    public static <E extends Comparable<E>> int positionToInsert(E[] array, E key, int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l < r) {
            int mid = (l + r) >>> 1;
            int compare = array[mid].compareTo(key);
            if (compare > 0) {
                r = mid;
            } else if (compare < 0) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
