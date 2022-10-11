package company.vk.polis.ads.paikeee.part3;

import java.util.Scanner;



public final class HeapSort {

    private HeapSort() {

    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Heap myHeap = new Heap(n);
        for (int i = 0; i < n; i++) {
            myHeap.insert(scanner.nextInt());
        }
        int[] array = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            array[i] = myHeap.extract();
        }
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static class Heap {

        int[] heap;
        int size;

        public Heap(int n) {
            heap = new int[n + 1];
            size = 0;
        }

        void insert(int elem) {
            heap[++size] = elem;
            swim(size);
        }

        int extract() {
            int max = heap[1];
            swap(1, size--);
            sink();
            return max;
        }

        private void sink() {
            int parent = 1;
            while (size >= parent * 2) {
                int child = parent * 2;
                if (child < size && heap[child] < heap[child + 1]) {
                    child++;
                }
                if (heap[parent] >= heap[child]) {
                    break;
                }
                swap(parent, child);
                parent = child;
            }
        }

        private void swim(int child) {
            while (child > 1 && heap[child] > heap[child / 2]) {
                swap(child, child / 2);
                child /= 2;
            }
        }

        private void swap(int a, int b) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }

    }

}
