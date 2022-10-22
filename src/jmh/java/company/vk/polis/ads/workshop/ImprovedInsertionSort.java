package company.vk.polis.ads.workshop;


public final class ImprovedInsertionSort {
    public static <E extends Comparable<E>> void sort(E[] array) {
        sort(array, 0, array.length);
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int sortedPart = 1; sortedPart < array.length; sortedPart++) {
            E value = array[sortedPart];
            if (value.compareTo(array[sortedPart - 1]) >= 0) {
                continue;
            }
            int index = findPos(array,value,0, sortedPart);

            System.arraycopy(array,index,array,index+1, sortedPart-index);
            array[index] = value;
        }
    }

    private static <E extends Comparable<E>> int findPos(E[] array, E val, int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l != r) {
            int m = (l + r) >>> 1;
            int comparison = array[m].compareTo(val);
            if (comparison > 0) {
                r = m;
            } else if (comparison < 0) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return l;
    }
}
