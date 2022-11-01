package company.vk.polis.ads.workshop;

public class HeapSort {

    public static <E extends Comparable<E>> E[] sort(E[] array) {
        E[] tempArray = (E[]) new Comparable[array.length + 1];
        System.arraycopy(array, 0, tempArray, 1, array.length);
        Heap<E> heap = new Heap<>(tempArray, array.length);
        return (E[]) heap.heapSort();
    }

    private static final class Heap<E extends Comparable<E>> {
        private E[] array;
        private int n;

        public Heap(E[] array, int n) {
            this.array = array;
            this.n = n;
        }

        public E[] heapSort() {
            for (int k = n / 2; k >= 1; k--) {
                sink(k);
            }
            while (n > 1) {
                swap(1, n--);
                sink(1);
            }
            return array;
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && array[j].compareTo(array[j + 1]) < 0) j++;
                if (array[k].compareTo(array[j]) >= 0) break;
                swap(k, j);
                k = j;
            }
        }

        private void swap(int k1, int k2) {
            E temp = array[k1];
            array[k1] = array[k2];
            array[k2] = temp;
        }
    }
}
