package sorts;

public class InsertionSort {
    public static Integer[] sort(Integer[] array) {
        sort(array, 0, array.length);
        return array;
    }

    private static void sort(Integer[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; ++i) {
            Integer key = array[i];
            int j = i - 1;
            while (j >= fromInclusive && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

}
