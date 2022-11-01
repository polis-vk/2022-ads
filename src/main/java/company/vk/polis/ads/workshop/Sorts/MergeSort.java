package company.vk.polis.ads.workshop.Sorts;

public class MergeSort {
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

    @SuppressWarnings("uncheked")
    private static <E extends Comparable<E>> void merge(E[] array, int fromInclusive, int mid, int toExclusive) {
        E[] arr1 = (E[]) new Comparable[mid - fromInclusive];
        E[] arr2 = (E[]) new Comparable[toExclusive - mid];

        System.arraycopy(array, fromInclusive, arr1, 0, mid - fromInclusive);
        System.arraycopy(array, mid, arr2, 0, toExclusive - mid);

        int k = fromInclusive;
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].compareTo(arr2[j]) < 1) {
                array[k] = arr1[i++];
            } else {
                array[k] = arr2[j++];
            }
            k++;
        }

        while (i < arr1.length) {
            array[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            array[k++] = arr2[j++];
        }
    }
}
