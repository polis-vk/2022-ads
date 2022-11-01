package company.vk.polis.ads.workshop.Sorts;

public class HeapSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        Heap.heapSort(array);
        return array;
    }

    private static class Heap<E extends Comparable<E>> {
        private static final int DEFAULT_CAPACITY = 10;
        private E[] array;
        private int size;

        public Heap() {
            this.clear();
        }

        public int size() {
            return size;
        }

        @SuppressWarnings("unchecked")
        public void clear() {
            size = 0;
            array = (E[]) new Comparable[DEFAULT_CAPACITY];
        }

        public E delMax() {
            if (size() == 0) {
                return null;
            }

            var max = array[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        public void insert(E num) {
            if (size + 1 == array.length) {
                resize();
            }

            array[++size] = num;
            swim(size);
        }

        public static <E extends Comparable<E>> void heapSort(E[] arr) {
            Heap<E> heap = new Heap<>();
            heap.array = arr;
            heap.size = arr.length - 1;

            int n = arr.length;
            for (int k = n / 2; k >= 1; k--) {
                heap.sink(k - 1);
            }

            while (heap.size > 0) {
                heap.swap(0, heap.size--);
                heap.sink(0);
            }
        }

        @SuppressWarnings("unchecked")
        private void resize() {
            E[] oldArray = array;
            array = (E[]) new Comparable[(int) Math.ceil((double) array.length * 1.5)];
            System.arraycopy(oldArray, 0, array, 0, size() + 1);
        }

        private void swim(int k) {
            while (k > 1 && array[k].compareTo(array[k / 2]) > 0) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && array[j].compareTo(array[j + 1]) < 0) {
                    j++;
                }

                if (array[k].compareTo(array[j]) >= 0) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            var temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
