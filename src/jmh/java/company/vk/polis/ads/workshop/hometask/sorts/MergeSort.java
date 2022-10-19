package company.vk.polis.ads.workshop.hometask.sorts;

public class MergeSort {

    public static Integer[] sort(Integer[] array) {
        Integer[] bufferedArray = new Integer[array.length];
        sort(array, 0, array.length, bufferedArray);
        return array;
    }

    public static void sort(Integer[] array, int fromInclusive, int toExclusive, Integer[] bufferedArray) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid, bufferedArray);
        sort(array, mid, toExclusive, bufferedArray);
        merge(array, fromInclusive, mid, toExclusive, bufferedArray);
    }

    private static void merge(Integer[] array, int from, int mid, int to, Integer[] bufferedArray) {
        int i = from;
        int j = mid;

        while (i < mid && j < to) {
            if (array[i].compareTo(array[j]) < 0) {
                bufferedArray[i + j - from - mid] = array[i];
                i++;
            } else {
                bufferedArray[i + j - from - mid] = array[j];
                j++;
            }
        }
        while (i < mid) {
            bufferedArray[i + j - from - mid] = array[i];
            i++;
        }
        while (j < to) {
            bufferedArray[i + j - from - mid] = array[j];
            j++;
        }
        for (i = from; i < to; i++) {
            array[i] = bufferedArray[i - from];
        }
    }
}
