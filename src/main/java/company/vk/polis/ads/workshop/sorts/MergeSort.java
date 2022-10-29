package company.vk.polis.ads.workshop.sorts;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static Object sort(int[] array) {
        mergeSort(array, 0, array.length);
        return null;
    }

    private static void mergeSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static void merge(int[] array, int fromInclusive, int mid, int toExclusive) {
        int halfIndex1 = fromInclusive;
        int halfIndex2 = mid;
        List<Integer> tempList = new ArrayList<>();
        while (halfIndex1 < mid || halfIndex2 < toExclusive) {
            if (halfIndex1 < mid &&
                    (halfIndex2 >= toExclusive || array[halfIndex1] < array[halfIndex2])) {
                tempList.add(array[halfIndex1]);
                halfIndex1++;
            } else {
                tempList.add(array[halfIndex2]);
                halfIndex2++;
            }
        }
        for (int i = 0; i < (toExclusive - fromInclusive); i++) {
            int currIndex = fromInclusive + i;
            array[currIndex] = tempList.get(i);
        }
    }

}
