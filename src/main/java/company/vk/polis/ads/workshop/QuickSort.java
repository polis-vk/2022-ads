package company.vk.polis.ads.workshop;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static  <E extends Comparable<E>> E[] sort(E[] array) {
        quickSort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void swap(E[] a, int i, int j) {
        E tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static <E extends Comparable<E>> int randomPartition(E[] data, int startIndex, int endIndex){
        int i = ThreadLocalRandom.current().nextInt(endIndex - startIndex) + startIndex;
        swap(data, startIndex, i);
        return partition(data, startIndex, endIndex);
    }

    private static <E extends Comparable<E>> int partition(E[] data, int startIndex, int endIndex){
        E selectedItem = data[startIndex];
        int i = startIndex;
        for (int j = startIndex + 1; j < endIndex; j++){
            if (data[j].compareTo(selectedItem) <= 0){
                swap(data, ++i, j);
            }
        }
        swap(data, i, startIndex);
        return i;
    }

    private static <E extends Comparable<E>> void quickSort(E[] data, int startIndex, int endIndex){
        if (startIndex >= endIndex - 1){
            return;
        }
        int i = randomPartition(data, startIndex, endIndex);
        quickSort(data, startIndex, i);
        quickSort(data, i + 1, endIndex);
    }
}