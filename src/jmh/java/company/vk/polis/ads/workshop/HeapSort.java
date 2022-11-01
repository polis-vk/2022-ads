package company.vk.polis.ads.workshop;

public class HeapSort {

    public static void sort(Integer[] array) {
        heapSort(array);
    }

    private static void heapSort(Integer[] array) {
        int size = array.length;
        int i = (size - 1) / 2;
        while (i >= 0) {
            heapify(array, i--, size);
        }

        while (size > 0) {
            array[size - 1] = extract(array, size);
            size--;
        }

    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static int rightChild(int i) {
        return 2 * i + 2;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void heapify(Integer[] array, int i, int size) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;
        if (left < size && array[left] > array[i]) {
            largest = left;
        }
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(array, i, largest);
            heapify(array, largest, size);
        }
    }

    private static int extract(Integer[] array, int size) {
        Integer firstElement = array[0];
        array[0] = array[size - 1];
        heapify(array, 0, size - 1);
        return firstElement;
    }
}
