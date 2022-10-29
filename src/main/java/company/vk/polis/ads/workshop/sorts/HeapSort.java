package company.vk.polis.ads.workshop.sorts;

import java.util.ArrayList;

public class HeapSort {
    public static Object sort(int[] array) {
        heapSort(array, array.length);
        return null;
    }

    private static void heapSort(int[] array, int size) {
        Heap heap = new Heap();
        for (int elem : array) {
            heap.insert(elem);
        }
        int[] tmpArray = new int[size];
        for (int i = 0; i < size; i++) {
            tmpArray[i] = heap.extract();
        }
        System.arraycopy(tmpArray, 0, array, 0, size);
    }

    private static class Heap {
        private final ArrayList<Integer> arr = new ArrayList<>();
        private int n = 0;

        public void insert(int value) {
            while (arr.size() <= n * 2 + 1) {
                arr.add(-1);
            }
            arr.set(++n, value);
            swim(n);
        }

        public int extract() {
            int result = arr.get(1);
            int j = (arr.get(n) > arr.get(n + 1)) ? n : n + 1;
            arr.set(1, arr.get(j));
            arr.set(j, -1);
            n--;
            sink(1);
            return result;
        }

        public void swim(int k) {
            while (k > 1 && arr.get(k) < arr.get(k / 2)) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        public void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                int left = arr.get(j);
                int right = arr.get(j + 1);
                if (j < n && left > right && right >= 0) {
                    j++;
                }
                if (arr.get(k) <= arr.get(j)) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swap(int k, int j) {
            int temp = arr.get(k);
            arr.set(k, arr.get(j));
            arr.set(j, temp);
        }
    }

}
