import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW3Task2 {
    private HW3Task2() {
        // Should not be instantiated
    }

    //Найти медиану 2: https://www.eolymp.com/ru/submissions/11748552
    private static void solve(final Scanner in, final PrintWriter out) {
        MinHeap minHeap = new MinHeap();
        Heap maxHeap = new Heap();
        int median = in.nextInt();
        int count = 1;
        out.println(median);
        while (in.hasNext()) {
            int value = in.nextInt();
            count++;
            if (count % 2 == 0) {
                if (value < median) {
                    maxHeap.insert(value);
                    minHeap.insert(median);
                } else {
                    maxHeap.insert(median);
                    minHeap.insert(value);
                }
                median = (minHeap.peek() + maxHeap.peek()) / 2;
            } else {
                if (value < median) {
                    if (value > maxHeap.peek()) {
                        median = value;
                    } else {
                        median = maxHeap.extract();
                        maxHeap.insert(value);
                    }
                } else {
                    if (value < minHeap.peek()) {
                        median = value;
                    } else {
                        median = minHeap.extract();
                        minHeap.insert(value);
                    }
                }
            }
            out.println(median);
        }
    }

    private static class Heap {

        int[] array = new int[10];
        int size;

        void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        void swim(int k) {
            while (k > 1 && array[k] > array[k / 2]) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && array[j] < array[j + 1]) {
                    j++;
                }
                if (array[k] >= array[j]) {
                    break;
                }
                swap(array, k, j);
                k = j;
            }
        }

        void insert(int v) {
            if (size + 1 >= array.length) {
                increaseArraySize();
            }
            array[++size] = v;
            swim(size);
        }

        int extract() {
            int max = array[1];
            swap(array, 1, size--);
            sink(1);
            return max;
        }

        int peek() {
            return array[1];
        }

        void increaseArraySize() {
            array = Arrays.copyOf(array, array.length * 3 / 2 + 1);
        }
    }

    private static class MinHeap extends Heap {

        @Override
        void swim(int k) {
            while (k > 1 && array[k] < array[k / 2]) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        @Override
        void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && array[j] > array[j + 1]) {
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

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
