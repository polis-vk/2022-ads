package company.vk.polis.ads.workshop.sorting;

public class MergeSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        mergeSort(array, 0, array.length);
        return array;
    }

    private static <E extends Comparable<E>> void mergeSort(E[] mas, int fromIndex, int toIndex) {
        if (fromIndex == toIndex - 1) {
            return;
        }

        int mid = toIndex - (toIndex - fromIndex) / 2;
        mergeSort(mas, fromIndex, mid);
        mergeSort(mas, mid, toIndex);
        merge(mas, fromIndex, mid, toIndex);
    }

    private static <E extends Comparable<E>> void merge(E[] mas, int fromIndex, int mid, int toIndex) {
        int sizeLeftMas = mid - fromIndex;
        int sizeRightMas = toIndex - mid;
        E[] masLeft = (E[]) new Comparable[sizeLeftMas];
        E[] masRight = (E[]) new Comparable[sizeRightMas];

        System.arraycopy(mas, fromIndex, masLeft, 0, sizeLeftMas);
        System.arraycopy(mas, mid, masRight, 0, sizeRightMas);

        int i = 0;
        int j = 0;
        int index = fromIndex;

        while (i < sizeLeftMas && j < sizeRightMas) {
            if (masLeft[i].compareTo(masRight[j]) > 0) {
                mas[index++] = masLeft[i++];
            } else {
                mas[index++] = masRight[j++];
            }
        }

        while (i < sizeLeftMas) {
            mas[index++] = masLeft[i++];
        }

        while (j < sizeRightMas) {
            mas[index++] = masRight[j++];
        }
    }

}