package company.vk.polis.ads.workshop;

import java.util.concurrent.ThreadLocalRandom;

public class Sorts {

    //////////////////////////////////////////
    // Insertion sort
    //////////////////////////////////////////
    public static int[] insertionSort(int[] array) {
        return insertionSort(array, 0, array.length);
    }

    public static int[] insertionSort(int[] array, int fromInclusive,
                                      int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                swap(array, j, j - 1);
                j--;
            }
        }
        return array;
    }

    //////////////////////////////////////////
    // Improved Insertion sort
    //////////////////////////////////////////

    public static int[] improvedInsSort(int[] array) {
        return improvedInsSort(array, 0, array.length);
    }

    public static int[] improvedInsSort(int[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; ++i) {
            int key = array[i];
            if (key >= array[i - 1]) {
                continue;
            }
            int insertionPosition = insertionPosition(array, key, fromInclusive, i);
            System.arraycopy(array, insertionPosition, array,
                    insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = key;
        }
        return array;
    }

    private static int insertionPosition(int[] array, int key, int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l < r) {
            int mid = (l + r) >>> 1;
            var el = array[mid];
            if (el > key) {
                r = mid;
            } else if (el < key) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }

    //////////////////////////////////////////
    // Merge sort
    //////////////////////////////////////////

    public static int[] mergeSort(int[] array) {
        temp = new int[array.length];
        return mergeSort(array, 0, array.length);
    }

    private static int[] mergeSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return array;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
        return array;
    }

    private static int[] temp;

    private static void merge(int[] array, int fromInclusive, int mid, int toInclusive) {

        int leftBorder = fromInclusive;
        int rightBorder = mid;

        while (leftBorder < mid && rightBorder < toInclusive) {
            if (array[leftBorder] > array[rightBorder]) {
                temp[leftBorder + rightBorder - fromInclusive - mid] = array[leftBorder];
                leftBorder++;
            } else {
                temp[leftBorder + rightBorder - fromInclusive - mid] = array[rightBorder];
                rightBorder++;
            }
        }
        while (leftBorder < mid) {
            temp[leftBorder + rightBorder - fromInclusive - mid] = array[leftBorder];
            leftBorder++;
        }
        while (rightBorder < toInclusive) {
            temp[leftBorder + rightBorder - fromInclusive - mid] = array[rightBorder];
            rightBorder++;
        }
        for (leftBorder = fromInclusive; leftBorder < toInclusive; leftBorder++) {
            array[leftBorder] = temp[leftBorder - fromInclusive];
        }
    }

    //////////////////////////////////////////
    // Quick sort
    //////////////////////////////////////////

    public static int[] quickSort(int[] array) {
        return quickSort(array, 0, array.length);
    }

    private static int[] quickSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return array;
        }
        int i = randomPartition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
        return array;
    }

    private static int randomPartition(int[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        return partition(array, fromInclusive, toExclusive);
    }

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    //////////////////////////////////////////
    // Heap sort
    //////////////////////////////////////////
    public static int[] heapSort(int[] array) {
        int n = array.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(array, k, n);
        }
        while (n > 1) {
            swap(array, 1, n--);
            sink(array, 1, n);
        }
        return array;
    }

    private static void sink(int[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k; // left child
            if (j < n && a[j] < a[j + 1]) {
                j++; //right child
            }
            if (a[k] >= a[j]) {
                break; // invariant holds
            }
            swap(a, k, j);
            k = j;
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
