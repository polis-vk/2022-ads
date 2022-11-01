package company.vk.polis.ads.workshop;

public class InsertionSort {

    public static <E extends Comparable<E>> E[] sort(E[] array){
        for (int i = 1; i < array.length ; i++) {
            int j = i - 1;
            while(j >= 0 && array[j].compareTo(array[j + 1]) > 0){
                E tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                j--;
            }
        }
        return array;
    }
}
