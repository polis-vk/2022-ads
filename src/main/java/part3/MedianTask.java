package part3;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

class MedianTask {
    public static class Heap {

        private final static int CAPACITY = 1000000;
        private int[] heapArray;
        private int currentSize;

        private final String type;

        public Heap(String type) {
            this.currentSize = 0;
            this.heapArray = new int[CAPACITY + 1];
            this.type = type;
        }

        public void insert(int value) {
            if (currentSize == heapArray.length) {
                heapArray = Arrays.copyOf(heapArray, heapArray.length * 2);
            }
            heapArray[++currentSize] = value;
            swim(currentSize);
        }

        public int exctract() {
            if (currentSize == 0) {
                throw new RuntimeException();
            }
            if (currentSize == 1) {
                currentSize--;
                return heapArray[1];
            }
            if (currentSize >= heapArray.length - 1) {
                heapArray = Arrays.copyOf(heapArray, heapArray.length * 2);
            }
            int root = heapArray[1];
            swap(heapArray, 1, currentSize--);
            sink(1);
            return root;
        }

        private void swim(int k) {
            if (Objects.equals(type, "max")) {
                while (k > 1 && heapArray[k] > heapArray[k / 2]) {
                    swap(heapArray, k, k / 2);
                    k = k / 2;
                }
            } else {
                while (k > 1 && heapArray[k] < heapArray[k / 2]) {
                    swap(heapArray, k, k / 2);
                    k /= 2;
                }
            }
        }

        private void sink(int k) {
            if (Objects.equals(type, "max")) {
                while (2 * k <= currentSize) {
                    int j = 2 * k;
                    if (j < currentSize && heapArray[j] < heapArray[j + 1]) {
                        j++;
                    }
                    if (heapArray[k] >= heapArray[j]) {
                        break;
                    }
                    swap(heapArray, k, j);
                    k = j;
                }
            } else {
                while (2 * k <= currentSize) {
                    int j = 2 * k;
                    if (j < currentSize && heapArray[j + 1] < heapArray[j]) {
                        j++;
                    }
                    if (heapArray[k] <= heapArray[j]) {
                        break;
                    }
                    swap(heapArray, k, j);
                    k = j;
                }
            }
        }

        public int lastElement() {
            int root = heapArray[1];
            swap(heapArray, 1, currentSize--);
            sink(1);
            return root;
        }

        private void swap(int[] heapArray, int index, int parentIndex) {
            int temp = heapArray[index];
            heapArray[index] = heapArray[parentIndex];
            heapArray[parentIndex] = temp;
        }
    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);
        Heap maxHeap = new Heap("max");
        Heap minHeap = new Heap("min");
        int median = 0;
        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number <= median) {
                maxHeap.insert(number);
                median = maxHeap.lastElement();
            } else {
                minHeap.insert(number);
                median = minHeap.lastElement();
            }
            if (number >= median) {
                minHeap.insert(number);
                maxHeap.insert(median);
            } else {
                minHeap.insert(median);
                maxHeap.insert(number);
            }
            median = (maxHeap.heapArray[1] + minHeap.heapArray[1]) / 2;
            System.out.println(median);
        }
    }
}
