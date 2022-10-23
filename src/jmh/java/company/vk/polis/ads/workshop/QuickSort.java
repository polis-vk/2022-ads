package company.vk.polis.ads.workshop;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static <E extends Comparable> void sort(E[] arr) {
        quickSort(arr, 0, arr.length);
    }

    private static <E extends Comparable> void quickSort(E[] arr, int fromInc, int toExc) {
        if (toExc <= fromInc + 1) {
            return;
        }

        int mid = partition(arr, fromInc, toExc);
        quickSort(arr, fromInc, mid);
        quickSort(arr, mid + 1, toExc);
    }

    private static <E extends Comparable> int partition(E[] arr, int fromInc, int toExc) {

        int rAddress = ThreadLocalRandom.current().nextInt(fromInc, toExc);
        E tmp = arr[rAddress];
        arr[rAddress] = arr[fromInc];
        arr[fromInc] = tmp;

        E pivot = arr[fromInc];
        int ans = fromInc;

        for (int i = fromInc + 1; i < toExc; i++) {
            if (arr[i].compareTo(pivot) <= 0) {
                ans++;
                tmp = arr[i];
                arr[i] = arr[ans];
                arr[ans] = tmp;
            }
        }

        tmp = arr[fromInc];
        arr[fromInc] = arr[ans];
        arr[ans] = tmp;

        return ans;
    }

}
