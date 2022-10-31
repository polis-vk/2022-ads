package company.vk.polis.ads.workshop;

public class MergeSort {

    public static Integer[] mergeSort(Integer[] array) {
        mergeSort(array, 0, array.length);
        return array;
    }

    public static void mergeSort(Integer[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) / 2);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static void merge(Integer[] array, int fromInclusive, int mid, int toExclusive) {
        int lengthOfTempArray = toExclusive - fromInclusive;
        Integer[] tempArray = new Integer[lengthOfTempArray];

        int leftIndex = fromInclusive;
        int rightIndex = mid;
        int pointerInTempArray = 0;

        while (leftIndex < mid && rightIndex < toExclusive) {
            if (array[leftIndex].compareTo(array[rightIndex]) < 0) {
                tempArray[pointerInTempArray] = array[leftIndex];
                leftIndex++;
            } else {
                tempArray[pointerInTempArray] = array[rightIndex];
                rightIndex++;
            }
            pointerInTempArray++;
        }

        while (leftIndex < mid) {
            tempArray[pointerInTempArray] = array[leftIndex];
            pointerInTempArray++;
            leftIndex++;
        }

        while (rightIndex < toExclusive) {
            tempArray[pointerInTempArray] = array[rightIndex];
            pointerInTempArray++;
            rightIndex++;
        }


        for (int i = 0; i < tempArray.length; fromInclusive++) {
            array[fromInclusive] = tempArray[i++];
        }
    }
}
