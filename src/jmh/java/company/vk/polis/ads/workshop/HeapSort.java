package company.vk.polis.ads.workshop;

public class HeapSort {
    public static <E extends Comparable> void sort(E[] arr) {

        heapify(arr);
        int pointer = arr.length - 1;
        while (pointer > 0) {
            swap(arr, 0, pointer--);
            sink(arr, 0, pointer);
        }
    }

    private static <E extends Comparable> void heapify(E[] arr) {
        for (int i = arr.length; i >= 0; i--) {
            if (i * 2 + 1 < arr.length) {
                sink(arr, i, arr.length - 1);
            }
        }

    }

    private static <E extends Comparable> void sink(E[] arr, int index, int limit) {
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        if (rightChildIndex > limit) {
            if (leftChildIndex <= limit && arr[leftChildIndex].compareTo(arr[index]) > 0){
                swap(arr, leftChildIndex, index);
                sink(arr, leftChildIndex, limit);
            }
            return;
        }
        if (leftChildIndex <= limit && arr[leftChildIndex].compareTo(arr[index]) > 0 && arr[leftChildIndex].compareTo(arr[rightChildIndex]) > 0) {
            swap(arr, leftChildIndex, index);
            sink(arr, leftChildIndex, limit);
        } else if (arr[rightChildIndex].compareTo(arr[index]) > 0) {
            swap(arr, rightChildIndex, index);
            sink(arr, rightChildIndex, limit);
        }
    }

    private static <E extends Comparable> void swap(E[] arr, int index1, int index2) {
        E tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

}
