package company.vk.polis.ads.workshop;

public class QuickSort {

    public static void sort(Integer[] array) {
        quickSort(array,0, array.length - 1);
    }

    private static void quickSort(Integer[] array, int start, int end) {
        int divideIndex;
        if (start < end) {
            divideIndex = split(array, start, end);
            quickSort(array, start, divideIndex - 1);
            quickSort(array, divideIndex, end);
        }
    }

    private static int split(Integer[] array, int start, int end) {
        int leftIndex = start;
        int rightIndex = end;
        int pivot = array[start + (end - start) / 2];
        while (leftIndex <= rightIndex) {
            while (array[leftIndex] < pivot) {
                leftIndex++;
            }
            while (array[rightIndex] > pivot) {
                rightIndex--;
            }
            if (leftIndex <= rightIndex) {
                swap(array, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(Integer[] array, int leftIndex, int rightIndex) {
        int temp;
        temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }
}
