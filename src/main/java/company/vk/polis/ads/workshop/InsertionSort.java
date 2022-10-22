package company.vk.polis.ads.workshop;

public class InsertionSort {
    private static <E extends Comparable<E>> void swap(E[] array, int index1, int index2) {
        E tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        for (int i = 1; i < array.length; i++){
            int tmp = i;
            final var currentKey = array[tmp];
            while(tmp > 0 && currentKey.compareTo(array[tmp - 1]) < 0) {
                swap(array, tmp - 1, tmp);
                tmp--;
            }
        }
        return array;
    }
}
