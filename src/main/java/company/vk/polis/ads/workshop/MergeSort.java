package company.vk.polis.ads.workshop;

public class MergeSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        mergeSort(array, 0, array.length);
        return array;
    }

    private static int getMidIndex(int startIndex, int endIndex){
        return startIndex + ((endIndex - startIndex) >> 1);
    }

    private static <E extends Comparable<E>> void mergeSort(E[] data, int startIndex, int endIndex){
        if (startIndex >= endIndex - 1){
            return;
        }
        int mid = getMidIndex(startIndex, endIndex);
        mergeSort(data, startIndex, mid);
        mergeSort(data, mid, endIndex);
        merge(data, startIndex, mid, endIndex);
    }

    private static <E extends Comparable<E>> void merge(E[] data, int startIndex, int midIndex ,int endIndex){
        int sizeLeft = midIndex - startIndex;
        int sizeRight = endIndex - midIndex;
        E[] leftData = (E[]) new Comparable[sizeLeft];
        E[] rightData = (E[]) new Comparable[sizeRight];
        System.arraycopy(data, startIndex, leftData, 0, sizeLeft);
        System.arraycopy(data, midIndex, rightData, 0, sizeRight);

        int i = 0;
        int j = 0;
        int k = startIndex;
        while (i < sizeLeft && j < sizeRight) {
            if (leftData[i].compareTo(rightData[j]) > 0) {
                data[k] = leftData[i];
                i++;
            }
            else {
                data[k] = rightData[j];
                j++;
            }
            k++;
        }
        while (i < sizeLeft) {
            data[k] = leftData[i];
            i++;
            k++;
        }
        while (j < sizeRight) {
            data[k] = rightData[j];
            j++;
            k++;
        }
    }
}
