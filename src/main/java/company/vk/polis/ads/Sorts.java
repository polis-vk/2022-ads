package company.vk.polis.ads;

import java.util.Arrays;
import java.util.Random;

public class Sorts {

    private static final Random random = new Random();

    public static <T extends Comparable<T>> T[] insertionSort(T[] array) {
        return insertionSort(array, 0, array.length);
    }

    public static <T extends Comparable<T>> T[] insertionSort(T[] array, int fromInclusive,
                                                               int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            int j = i;
            while (j > 0 && array[j].compareTo(array[j - 1]) < 0) {
                swap(array, j, j - 1);
                j--;
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] improvedInsertionSort(T[] array) {
        return improvedInsertionSort(array, 0, array.length);
    }

    public static <T extends Comparable<T>> T[] improvedInsertionSort(T[] array, int fromInclusive,
                                                                       int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            T key = array[i];
            if (key.compareTo(array[i - 1]) >= 0) {
                continue;
            }
            int insertionPosition = insertionSearch(array, key, fromInclusive, i);
            System.arraycopy(array, insertionPosition, array, insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = key;
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] heapSort(T[] array) {
        int n = array.length - 1;
        for (int i = n / 2; i >= 1; i--) {
            sink(array, i, n);
        }
        for (int i = 0; i < n - 1; i++) {
            swap(array, 1, n - i);
            sink(array, 1, n - i - 1);
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] mergeSort(T[] array) {
        T[] tempArray = Arrays.copyOf(array, array.length);
        return mergeSort(array, tempArray, 0, array.length);
    }

    public static <T extends Comparable<T>> T[] mergeSort(T[] array, T[] tempArray,
                                                           int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return array;
        }

        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;

        mergeSort(array, tempArray, fromInclusive, mid);
        mergeSort(array, tempArray, mid, toExclusive);

        int i = fromInclusive;
        int j = mid;
        int t = fromInclusive;
        while (i < mid && j < toExclusive) {
            if (tempArray[i].compareTo(tempArray[j]) < 0) {
                array[t++] = tempArray[i++];
            } else {
                array[t++] = tempArray[j++];
            }
        }

        while (i < mid) {
            array[t++] = tempArray[i++];
        }

        while (j < toExclusive) {
            array[t++] = tempArray[j++];
        }

        System.arraycopy(array, 0, tempArray, 0, tempArray.length);
        return array;
    }

    public static <T extends Comparable<T>> T[] quickSort(T[] array) {
        return quickSort(array, 0, array.length);
    }

    public static <T extends Comparable<T>> T[] quickSort(T[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return array;
        }
        int partitionIndex = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, partitionIndex);
        quickSort(array, partitionIndex + 1, toExclusive);
        return array;
    }

    private static <T extends Comparable<T>> int partition(T[] array, int fromInclusive, int toExclusive) {
        swap(array, random.nextInt(fromInclusive, toExclusive), toExclusive - 1);
        int i = fromInclusive - 1;
        for (int j = fromInclusive; j < toExclusive; j++) {
            if (array[j].compareTo(array[toExclusive - 1]) <= 0) {
                swap(array, ++i, j);
            }
        }
        return i;
    }

    private static <T extends Comparable<T>> void sink(T[] array, int k, int n) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j < n && array[j].compareTo(array[j + 1]) < 0) {
                j++;
            }
            if (array[k].compareTo(array[j]) >= 0) {
                break;
            }
            swap(array, k, j);
            k = j;
        }
    }

    public static <T extends Comparable<T>> int insertionSearch(T[] array, T key,
                                                                 int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cmp = array[mid].compareTo(key);
            if (cmp > 0) {
                r = mid;
            } else if (cmp < 0) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
