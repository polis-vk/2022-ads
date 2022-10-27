package company.vk.polis.ads.workshop;

public final class MergeSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid);
        sort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static <E extends Comparable<E>> void merge(E[] array, int fromInclusive, int mid, int toExclusive) {
        // Create temporary arrays
        E[] leftArray = (E[]) new Comparable[mid - fromInclusive];
        E[] rightArray = (E[]) new Comparable[toExclusive - mid];
        System.arraycopy(array, fromInclusive, leftArray, 0, leftArray.length);
        System.arraycopy(array, mid, rightArray, 0, rightArray.length);
        // Merge algorithm
        int curLeft = 0;
        int curRight = 0;
        int curPos = fromInclusive;
        while (curLeft < leftArray.length && curRight < rightArray.length) {
            if (leftArray[curLeft].compareTo(rightArray[curRight]) < 0) {
                array[curPos++] = leftArray[curLeft++];
            } else {
                array[curPos++] = rightArray[curRight++];
            }
        }
        while (curLeft < leftArray.length) {
            array[curPos++] = leftArray[curLeft++];
        }
        while (curRight < rightArray.length) {
            array[curPos++] = rightArray[curRight++];
        }
    }
}
