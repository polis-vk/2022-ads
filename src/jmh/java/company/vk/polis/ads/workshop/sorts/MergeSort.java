package company.vk.polis.ads.workshop.sorts;

@SuppressWarnings("unchecked")
public class MergeSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        E[] temp = (E[]) new Comparable[array.length];
        sort(array, temp, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void sort(E[] array, E[] temp, int fromInclusive, int toExclusive) {
        if (toExclusive - fromInclusive <= 1) {
            return;
        }
        int mid = (fromInclusive + toExclusive) >> 1;
        sort(array, temp, fromInclusive, mid);
        sort(array, temp, mid, toExclusive);
        merge(array, temp, fromInclusive, mid, toExclusive);
    }

    private static <E extends Comparable<E>> void merge(E[] array, Comparable<E>[] temp, int fromInclusive, int mid, int toExclusive) {
        int i = fromInclusive;
        int j = mid;
        int k = fromInclusive;
        while (i < mid && j < toExclusive) {
            if (array[i].compareTo(array[j]) > 0) {
                temp[k] = array[j];
                j++;
            } else {
                temp[k] = array[i];
                i++;
            }
            k++;
        }

        while (i < mid) {
            temp[k] = array[i];
            i++;
            k++;
        }

        while (j < toExclusive) {
            temp[k] = array[j];
            j++;
            k++;
        }

        for (k = fromInclusive; k < toExclusive; k++) {
            array[k] = (E) temp[k];
        }
    }
}
