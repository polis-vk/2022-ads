package company.vk.polis.ads.workshop.sorts;

@SuppressWarnings("unchecked")
public class MergeSort {

    public static <E extends Comparable<E>> E[] mergeSort(E[] array) {
        E[] tempArray = (E[]) new Comparable[array.length];
        mergeSort((E[]) array, tempArray, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void mergeSort(E[] array, int fromInclusive, int toExclusive) {
        E[] tempArray = (E[]) new Comparable[array.length];
        mergeSort((E[]) array, tempArray, fromInclusive, toExclusive);
    }

    public static <E extends Comparable<E>> void mergeSort(E[] array, E[] tempArray, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >>> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, tempArray, fromInclusive, mid, toExclusive);
    }

    private static <E extends Comparable<E>> void merge(E[] array, E[] tempArray, int fromInclusive, int mid, int toExclusive) {
        int currentIndex = fromInclusive;

        int leftIndex = fromInclusive;

        int rightIndex = mid;

        while (leftIndex < mid && rightIndex < toExclusive) {
            if (array[leftIndex].compareTo(array[rightIndex]) <= 0) {
                tempArray[currentIndex++] = array[leftIndex++];
            } else {
                tempArray[currentIndex++] = array[rightIndex++];
            }
        }

        while (leftIndex < mid) {
            tempArray[currentIndex++] = array[leftIndex++];
        }

        while (rightIndex < toExclusive) {
            tempArray[currentIndex++] = array[rightIndex++];
        }

        System.arraycopy(tempArray, fromInclusive, array, fromInclusive, currentIndex - fromInclusive);
    }

}
