package company.vk.polis.ads.workshop;

public class MergeSort {

    public static <E extends Comparable<E>> E[] mergeSortForBench(E[] arr) {
        mergeSort(arr, 0, arr.length);
        return arr;
    }

    private static <E extends Comparable<E>> void mergeSort(E[] arr, int l, int r) {
        if (l + 1 == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid, r);
        merge(arr, l, mid, r);
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r) {
        int inx = 0;
        int it1 = l;
        int it2 = mid;
        E[] storage = (E[])new Comparable[r - l];
        while (it1 != mid && it2 != r) {
            if (arr[it1].compareTo(arr[it2]) < 0) {
                storage[inx++] = arr[it1++];
            } else {
                storage[inx++] = arr[it2++];
            }
        }
        while (it2 != r) {
            storage[inx++] = arr[it2++];
        }
        while (it1 != mid) {
            storage[inx++] = arr[it1++];
        }
        System.arraycopy(storage, 0, arr, l, storage.length);
    }
}
