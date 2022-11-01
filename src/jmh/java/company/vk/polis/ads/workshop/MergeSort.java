package company.vk.polis.ads.workshop;

public class MergeSort {
    public static Integer[] mergeSort(Integer[] array) {
        Integer[] sortingArray = new Integer[array.length];
        sort(array, 0, array.length, sortingArray);
        return array;
    }

    private static void sort(Integer[] array, int fromInclusive, int toExclusive, Integer[] sortingArray) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid, sortingArray);
        sort(array, mid, toExclusive, sortingArray);
        merge(array, fromInclusive, mid, toExclusive, sortingArray);
    }

    private static void merge(Integer[] array, int fromInclusive, int mid, int toExclusive, Integer[] bufferedArray) {
        int leftCursor = fromInclusive;
        int rightCursor = mid;
        while (leftCursor < mid && rightCursor < toExclusive) {
            if (array[leftCursor].compareTo(array[rightCursor]) < 0) {
                bufferedArray[leftCursor + rightCursor - fromInclusive - mid] = array[leftCursor];
                leftCursor++;
            } else {
                bufferedArray[leftCursor + rightCursor - fromInclusive - mid] = array[rightCursor];
                rightCursor++;
            }
        }
        while (leftCursor < mid) {
            bufferedArray[leftCursor + rightCursor - fromInclusive - mid] = array[leftCursor];
            leftCursor++;
        }
        while (rightCursor < toExclusive) {
            bufferedArray[leftCursor + rightCursor - fromInclusive - mid] = array[rightCursor];
            rightCursor++;
        }
        leftCursor = fromInclusive;
        while (leftCursor < toExclusive) {
            array[leftCursor] = bufferedArray[leftCursor - fromInclusive];
            leftCursor++;
        }
    }
}
