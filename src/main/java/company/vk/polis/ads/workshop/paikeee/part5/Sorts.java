package company.vk.polis.ads.workshop.paikeee.part5;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class Sorts {

    private static final Random random = new Random(Calendar.getInstance().getTimeInMillis());

    public static Object insertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int current = elements[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (elements[j] > current) {
                    elements[j + 1] = elements[j];
                }
                else {
                    break;
                }
            }
            elements[j + 1] = current;
        }
        return null;
    }

    private static void improvedInsertionSort(int[] array,  int fromInclusive, int toExclusive) {
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
    }

    private static int insertionPosition(int[] array, int key,  int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;
        while (l < r) {
            int mid = l + r >>> 1;
            int el = array[mid];
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

    public static Object improvedInsertionSort(int[] elements) {
        improvedInsertionSort(elements, 0, elements.length);
        return null;
    }

    private static void merge(int[] elements, int begin, int middle, int end) {
        int[] left = Arrays.copyOfRange(elements, begin, middle);
        int[] right = Arrays.copyOfRange(elements, middle, end);
        int li = 0, ri = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li] <= right[ri])) {
                elements[i] = left[li++];
            }
            else {
                elements[i] = right[ri++];
            }
        }
    }

    private static void mergeSort(int[] elements, int begin, int end) {
        if (end - begin <= 1) {
            return;
        }
        int middle = (begin + end) / 2;
        mergeSort(elements, begin, middle);
        mergeSort(elements, middle, end);
        merge(elements, begin, middle, end);
    }

    public static Object mergeSort(int[] elements) {
        mergeSort(elements, 0, elements.length);
        return null;
    }

    private static int partition(int[] elements, int min, int max) {
        int x = elements[min + random.nextInt(max - min + 1)];
        int left = min, right = max;
        while (left <= right) {
            while (elements[left] < x) {
                left++;
            }
            while (elements[right] > x) {
                right--;
            }
            if (left <= right) {
                int temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
                left++;
                right--;
            }
        }
        return right;
    }

    private static void quickSort(int[] elements, int min, int max) {
        if (min < max) {
            int border = partition(elements, min, max);
            quickSort(elements, min, border);
            quickSort(elements, border + 1, max);
        }
    }

    public static Object quickSort(int[] elements) {
        quickSort(elements, 0, elements.length - 1);
        return null;
    }

    private static void heapify(int[] elements, int start, int length) {
        int left = 2 * start + 1;
        int right = left + 1;
        int max = start;
        if (left < length && elements[left] > elements[max]) {
            max = left;
        }
        if (right < length && elements[right] > elements[max]) {
            max = right;
        }
        if (max != start) {
            int temp = elements[max];
            elements[max] = elements[start];
            elements[start] = temp;
            heapify(elements, max, length);
        }
    }

    private static void buildHeap(int[] elements) {
        for (int start = elements.length / 2 - 1; start >= 0; start--) {
            heapify(elements, start, elements.length);
        }
    }

    public static Object heapSort(int[] elements) {
        buildHeap(elements);
        for (int j = elements.length - 1; j >= 1; j--) {
            int temp = elements[0];
            elements[0] = elements[j];
            elements[j] = temp;
            heapify(elements, 0, j);
        }
        return null;
    }
}
