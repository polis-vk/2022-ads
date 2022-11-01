package company.vk.polis.ads.workshop;

public class MergeSort {

    public static void sort(Integer[] array) {
        mergeSort(array, array.length);
    }

    private static void mergeSort(Integer[] array, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        Integer[] leftArray = new Integer[mid];
        Integer[] rightArray = new Integer[size - mid];
        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[i];
        }
        for (int i = mid; i < size; i++) {
            rightArray[i - mid] = array[i];
        }
        mergeSort(leftArray, mid);
        mergeSort(rightArray, size - mid);
        merge(array, leftArray, rightArray, mid, size - mid);
    }

    private static void merge(Integer[] array, Integer[] leftArray, Integer[] rightArray, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (leftArray[i].compareTo(rightArray[j]) > 0) {
                array[k] = leftArray[i];
                k++;
                i++;
            } else {
                array[k] = rightArray[j];
                k++;
                j++;
            }
        }
        while (i < left) {
            array[k] = leftArray[i];
            k++;
            i++;
        }
        while (j < right) {
            array[k] = rightArray[j];
            k++;
            j++;
        }
    }
}
