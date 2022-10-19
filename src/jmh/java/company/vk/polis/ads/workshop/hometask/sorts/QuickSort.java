package company.vk.polis.ads.workshop.hometask.sorts;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static Integer[] sort(Integer[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static void sort(Integer[] array, int from, int to) {
        if (from + 1 >= to) {
            return;
        }

        int i = partition(array, from, to);
        sort(array, from, i);
        sort(array, i + 1, to);
    }

    private static int partition(Integer[] array, int from, int to) {
        int i = ThreadLocalRandom.current().nextInt(to - from) + from;
        swap(array, from, i);
        int pivotal = array[from];
        i = from;
        for (int j = from + 1; j < to; j++) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, from);
        return i;
    }

    private static void swap(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
