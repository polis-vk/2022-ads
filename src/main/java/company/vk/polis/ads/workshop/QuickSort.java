package company.vk.polis.ads.workshop;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static < E extends Comparable< E > > E[] quickSortForBench(E[] arr) {
        partitionSort(arr, 0, arr.length);
        return arr;
    }

    private static <E extends Comparable<E>> void partitionSort(E[] arr, int l, int r) {
        if (l + 1 >= r) {
            return;
        }
        int pivot = randomPartition(arr, l, r);
        partitionSort(arr, l, pivot);
        partitionSort(arr, pivot + 1, r);
    }

    private static <E extends Comparable<E>> int randomPartition(E[] arr, int l, int r) {
        int i = ThreadLocalRandom.current().nextInt(r - l) + l;
        swap(arr, l, i);
        return partition(arr, l, r);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        E pivot = arr[l];
        int i = l;
        for (int j = l + 1; j < r; ++j) {
            if (arr[j].compareTo(pivot) <= 0) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i, l);
        return i;
    }

    public static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
