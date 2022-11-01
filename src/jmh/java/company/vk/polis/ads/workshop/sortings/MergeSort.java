package company.vk.polis.ads.workshop.sortings;

public final class MergeSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        mergeSort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void mergeSort(E[] array,
                                                           int fromInclusive,
                                                           int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Comparable<E>> void merge(E[] array,
                                                        int left,
                                                        int mid,
                                                        int right) {
        int i = 0;
        int j = 0;
        E[] result = (E[]) new Comparable[right - left];

        while (left + i < mid && mid + j < right) {
            if (array[left + i].compareTo(array[mid + j]) < 0) {
                result[i + j] = array[left + i];
                i++;
            } else {
                result[i + j] = array[mid + j];
                j++;
            }
        }

        while (left + i < mid) {
            result[i + j] = array[left + i];
            i++;
        }

        while (right + j < right) {
            result[i + j] = array[mid + j];
            j++;
        }

        System.arraycopy(result, 0, array, left, i + j);
    }
}
