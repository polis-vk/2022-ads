package company.vk.polis.ads.workshop;

public class HeapSort {
    public static <E extends Comparable<E>>  E[] sort(E[] array){
        for (int i = (array.length / 2) - 1; i >= 0; i--) {
            sink(array, array.length, i);
        }
        for (int i = array.length - 1; i > 0; i--) {
            var temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            sink(array, i, 0);
        }
        return array;
    }

    public static <E extends Comparable<E>> void sink(E[] array, int n, int i){
        int large = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if(l < n && array[l].compareTo(array[large]) > 0){
            large = l;
        }
        if(r < n && array[r].compareTo(array[large]) > 0){
            large = r;
        }
        if(large != i){
            var tmp = array[i];
            array[i] = array[large];
            array[large] = tmp;
            sink(array, n, large);
        }
    }
}
