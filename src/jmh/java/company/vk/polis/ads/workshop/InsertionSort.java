package company.vk.polis.ads.workshop;

public class InsertionSort {
    public static <E extends Comparable> void sort(E[] array){
        for (int i = 1; i < array.length; i++){
            for (int j = i; j > 0; j--) {
                if (array[j].compareTo(array[j-1]) > 0) {
                    break;
                }
                E tmp = array[j];
                array[j] = array[j-1];
                array[j-1] = tmp;
            }
        }
    }

}
