package sorts;

public class MergeSort {
    public static Integer[] sort(Integer[] array) {
        Integer[] buffer = new Integer[array.length];
        sort(array, 0, array.length, buffer);
        return array;
    }

    private static void sort(Integer[] array, int fromInclusive, int toExclusive, Integer[] buffer) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid, buffer);
        sort(array, mid, toExclusive, buffer);
        merge(array, fromInclusive, mid, toExclusive, buffer);
    }

    private static void merge(Integer[] array, int fromInclusive, int mid, int toExclusive, Integer[] buffer) {
        int pointer1 = fromInclusive;
        int pointer2 = mid;

        if (toExclusive - fromInclusive >= 0) {
            System.arraycopy(array, fromInclusive, buffer, fromInclusive, toExclusive - fromInclusive);
        }

        for (int i = fromInclusive; i < toExclusive; i++) {
            if (pointer1 == mid) {
                array[i] = buffer[pointer2++];
            } else if (pointer2 == toExclusive) {
                array[i] = buffer[pointer1++];
            } else if (buffer[pointer1].compareTo(buffer[pointer2]) < 0) {
                array[i] = buffer[pointer2++];
            } else {
                array[i] = buffer[pointer1++];
            }
        }
    }

}
