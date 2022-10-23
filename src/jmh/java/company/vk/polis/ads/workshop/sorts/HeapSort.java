package company.vk.polis.ads.workshop.sorts;

@SuppressWarnings("unchecked")
public class HeapSort {

    public static <E extends Comparable<E>> E[] heapSort(E[] array) {
        heapSort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void heapSort(E[] array, int fromInclusive, int toExclusive) {
        int heapSize = toExclusive - fromInclusive;
        E[] heap = (E[]) new Comparable[heapSize + 1];
        System.arraycopy(array, fromInclusive, heap, fromInclusive + 1, heapSize);

        for (int k = heapSize / 2; k >= 1; k--) {
            sink(heap, k, heapSize);
        }

        int currentHeapSize = heapSize;
        while (currentHeapSize > 1) {
            swap(heap, 1, currentHeapSize--);
            sink(heap, 1, currentHeapSize);
        }

        System.arraycopy(heap, fromInclusive + 1, array, fromInclusive, heapSize);
    }

    public static <E extends Comparable<E>> void sink(E[] heap, int parent, int heapSize) {
        while (parent * 2 <= heapSize) {
            int child = 2 * parent;
            if (child < heapSize && heap[child].compareTo(heap[child + 1]) < 0) {
                child++;
            }

            if (heap[parent].compareTo(heap[child]) >= 0) {
                break;
            }

            swap(heap, parent, child);
            parent = child;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] array, int a, int b) {
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
