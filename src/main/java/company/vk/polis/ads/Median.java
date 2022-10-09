package company.vk.polis.ads;

import java.util.Arrays;
import java.util.Scanner;

//https://www.eolymp.com/ru/submissions/11720053
public class Median {
    private static final int INIT_CAPACITY = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Heap maxHeap = new Heap(INIT_CAPACITY);
        Heap minHeap = new minHeap(INIT_CAPACITY);
        int count = 0;
        int median = sc.nextInt();
        System.out.println(median);
        while (sc.hasNext()) {
            count++;
            int number = sc.nextInt();
            if (count % 2 == 1) {
                if (number <= median) {
                    maxHeap.insert(number);
                    minHeap.insert(median);

                } else {
                    minHeap.insert(number);
                    maxHeap.insert(median);
                }
                maxHeap.swim(maxHeap.heapSize);
                minHeap.swim(minHeap.heapSize);
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                if (number <= median) {
                    maxHeap.insert(number);
                    maxHeap.swim(maxHeap.heapSize);
                    median = maxHeap.extract();
                } else {
                    minHeap.insert(number);
                    minHeap.swim(minHeap.heapSize);
                    median = minHeap.extract();
                }
            }
            System.out.println(median);
        }
    }


    private static class minHeap extends Heap {

        public minHeap(int arrCapacity) {
            super(arrCapacity);
        }

        @Override
        void swim(int k) {
            while (k > 1 && array[k] < array[k / 2]) {
                swap(array, k, k / 2);
                k /= 2;
            }
        }

        @Override
        void sink(int k) {
            while (2 * k <= heapSize) {
                int j = 2 * k;
                if (j < heapSize && array[j] > array[j + 1]) {
                    j++;
                }
                if (array[k] <= array[j]) {
                    break;
                }
                swap(array, k, j);
                k = j;
            }
        }
    }

    private static class Heap {
        int[] array;
        int heapSize;
        private int arrCapacity;

        public Heap(int arrCapacity) {
            this.arrCapacity = arrCapacity;
            array = new int[arrCapacity];
            heapSize = 0;
        }

        public int peek() {
            return array[1];
        }

        public int extract() {
            int max = array[1];
            swap(array, 1, heapSize--);
            sink(1);
            return max;
        }

        public void insert(int x) {
            if (heapSize + 1 >= arrCapacity) {
                increaseCapacity();
            }
            array[++heapSize] = x;
            swim(heapSize);
        }

        void swim(int k) {
            while (k > 1 && array[k] > array[k / 2]) {
                swap(array, k, k / 2);
                k /= 2;
            }
        }

        void sink(int k) {
            while (2 * k <= heapSize) {
                int j = 2 * k;
                if (j < heapSize && array[j] < array[j + 1]) {
                    j++;
                }
                if (array[k] >= array[j]) {
                    break;
                }
                swap(array, k, j);
                k = j;
            }
        }

        private void increaseCapacity() {
            arrCapacity = (arrCapacity * 3 / 2) + 1;
            array = Arrays.copyOf(array, arrCapacity);
        }

        void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}
