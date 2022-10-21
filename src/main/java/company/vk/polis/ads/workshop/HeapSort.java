package company.vk.polis.ads.workshop;

public class HeapSort {

    public static < E extends Comparable< E > > E[] heapSort(E [] arr) {
        Heap heap = new Heap(arr);
        heap.buildMaxHeapify();
        for (int i = arr.length - 1; i > 0; --i) {
            heap.swap(0, i);
            --heap.heapSize;
            heap.maxHeapify(0);
        }
        return arr;
    }

    private static class Heap  < E extends Comparable< E > >{
        E [] storage;
        int heapSize;

        public Heap(E [] storage) {
            this.storage = storage;
            heapSize = storage.length;
        }

        private void maxHeapify(int k) {
            int left = getLeft(k);
            int right = getRight(k);
            int largest = k;

            if (right < heapSize && storage[right].compareTo(storage[k]) > 0) {
                largest = right;
            }

            if (left < heapSize && storage[left].compareTo(storage[largest]) > 0) {
                largest = left;
            }

            if (largest != k) {
                swap(k, largest);
                maxHeapify(largest);
            }
        }

        public void buildMaxHeapify() {
            for (int i = storage.length / 2; i >= 0; --i) {
                maxHeapify(i);
            }
        }

        private int getLeft(int k) {
            return 2 * k + 1;
        }

        private int getRight(int k) {
            return 2 * k + 2;
        }

        private void swap(int i, int j) {
            E tmp = storage[i];
            storage[i] = storage[j];
            storage[j] = tmp;
        }
    }



}
