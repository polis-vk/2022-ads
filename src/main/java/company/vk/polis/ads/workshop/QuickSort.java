package company.vk.polis.ads.workshop;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static <E extends Comparable<E>> E[] sort(E[] array){
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int begin, int end){
        if(begin + 1 >= end){
            return;
        }
        int partitionIndex = partition(array, begin, end);
        sort(array, begin, partitionIndex);
        sort(array, partitionIndex + 1, end);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end){
        int i = ThreadLocalRandom.current().nextInt(end - begin) + begin;
        swap(array, begin, i);
        var pivot = array[begin];
        i = begin;
        for (int j = begin + 1; j < end; j++) {
            if(array[j].compareTo(pivot) <= 0){
                swap(array, ++i, j);
            }
        }
        swap(array, i, begin);
        return i;
    }

    private static <E extends Comparable<E>> void swap(E[] array, int x, int y){
        var tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
}
