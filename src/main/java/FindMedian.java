import java.util.Scanner;

public class FindMedian {
    MinHeap minHeap;
    MaxHeap maxHeap;

    private void add(int num){
        if (minHeap.getSize() > 0 && num < minHeap.peek()) {
            maxHeap.insert(num);
            if (maxHeap.getSize() > minHeap.getSize() + 1) {
                minHeap.insert(maxHeap.delMax());
            }
        } else {
            minHeap.insert(num);
            if (minHeap.getSize() > maxHeap.getSize() + 1) {
                maxHeap.insert(minHeap.delMin());
            }
        }
    }

    private int getMedian(){
        int median;
        if (minHeap.getSize() < maxHeap.getSize()) {
            median = maxHeap.peek();
        } else if (minHeap.getSize() > maxHeap.getSize()) {
            median = minHeap.peek();
        } else {
            median = (minHeap.peek() + maxHeap.peek()) / 2;
        }
        return median;
    }

    private void solve() {
        minHeap = new MinHeap(10);
        maxHeap = new MaxHeap(10);
        Scanner scanner = new Scanner(System.in);
        int cur;
        while (scanner.hasNextInt()) {
            cur = scanner.nextInt();
            add(cur);
            System.out.println(getMedian());
        }
    }

    private static class MaxHeap {
        private int[] array;
        private int size = 0;

        private static final double INCREASE_COEFFICIENT = 1.5;

        MaxHeap(int size) {
            array = new int[size + 1];
        }

        public int getSize() {
            return size;
        }

        private void swap(int[] array, int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        public void swim(int k) {
            int index = k;
            while (index > 1 && array[index] > array[index / 2]) {
                swap(array, index, index / 2);
                index /= 2;
            }
        }

        public void sink(int k) {
            int index = k;
            while (2 * index <= size) {
                int j = 2 * index;
                if (j < size && array[j] < array[j + 1]) j++;
                if (array[index] >= array[j]) break;
                swap(array, index, j);
                index = j;
            }
        }

        public void insert(int element) {
            if (size + 1 >= array.length) {
                int[] newArray = new int[(int) (array.length * INCREASE_COEFFICIENT)];
                System.arraycopy(array, 0, newArray, 0, size + 1);
                array = newArray;
            }
            array[++size] = element;
            swim(size);
        }

        public int delMax() {
            int max = array[1];
            swap(array, 1, size--);
            sink(1);
            return max;
        }

        public int peek() {
            return array[1];
        }
    }

    public static class MinHeap {
        private int[] array;
        private int size = 0;

        private static final double INCREASE_COEFFICIENT = 1.5;

        MinHeap(int size) {
            array = new int[size + 1];
        }

        public int getSize() {
            return size;
        }

        private void swap(int[] array, int first, int second) {
            int temp = array[first];
            array[first] = array[second];
            array[second] = temp;
        }

        public void swim(int k) {
            int index = k;
            while (index > 1 && array[index] < array[index / 2]) {
                swap(array, index, index / 2);
                index /= 2;
            }
        }

        public void sink(int k) {
            int index = k;
            while (2 * index <= size) {
                int j = 2 * index;
                if (j < size && array[j] > array[j + 1]) j++;
                if (array[index] < array[j]) break;
                swap(array, index, j);
                index = j;
            }
        }

        public void insert(int element) {
            if (size + 1 >= array.length) {
                int[] newArray = new int[(int) (array.length * INCREASE_COEFFICIENT)];
                System.arraycopy(array, 0, newArray, 0, size + 1);
                array = newArray;
            }
            array[++size] = element;
            swim(size);
        }

        public int delMin() {
            int min = array[1];
            swap(array, 1, size--);
            sink(1);
            return min;
        }

        public int peek() {
            return array[1];
        }
    }
}
