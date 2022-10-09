import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class FindMedian {
    private FindMedian() {
        // Should not be instantiated
    }

    private static final int maxSizeArray = 1000000;
    private static final int two = 2;

    private static void solve(final FastScanner in, final PrintWriter out) {
        MaxHeap maxHeap = new MaxHeap(maxSizeArray);
        MinHeap minHeap = new MinHeap(maxSizeArray);
        Scanner scanner = new Scanner(System.in);
        int number;
        while (scanner.hasNextInt()) {
            number = scanner.nextInt();
            if (minHeap.getHeapSize() == maxHeap.getHeapSize()) {
                maxHeap.insert(number);
                minHeap.insert(maxHeap.extract());
            } else {
                minHeap.insert(number);
                maxHeap.insert(minHeap.extract());
            }
            if (minHeap.getHeapSize() > maxHeap.getHeapSize()) {
                out.println(minHeap.getFirstElement());

            } else {
                out.println((minHeap.getFirstElement() + maxHeap.getFirstElement()) / 2);
            }
        }
    }

    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    public static class MinHeap {
        private int[] heap;
        private int heapSize;

        public int getHeapSize() {
            return heapSize;
        }

        public int[] getHeap() {
            return heap;
        }

        public MinHeap(int size) {
            heapSize = 0;
            heap = new int[size];
        }

        private int parent(int i) {
            if (i == 0) {
                return 0;
            }
            return (i - 1) / two;
        }

        private int leftChild(int i) {
            return 2 * i + 1;
        }

        private int rightChild(int i) {
            return 2 * i + 2;
        }

        private void swap(int[] heap, int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void heapifyUp(int i) {
            if (i > 0 && heap[parent(i)] > heap[i]) {
                swap(heap, i, parent(i));
                heapifyUp(parent(i));
            }
        }

        private void heapifyDown(int i) {
            int left = leftChild(i);
            int right = rightChild(i);
            int smallest = i;
            if (left < heapSize && heap[left] < heap[i]) {
                smallest = left;
            }
            if (right < heapSize && heap[right] < heap[smallest]) {
                smallest = right;
            }
            if (smallest != i) {
                swap(heap, i, smallest);
                heapifyDown(smallest);
            }
        }

        public void insert(int element) {
            heapSize++;
            heap[heapSize - 1] = element;
            heapifyUp(heapSize - 1);
        }

        public int extract() {
            int min = heap[0];
            heap[0] = heap[heapSize - 1];
            heapSize--;
            heapifyDown(0);
            return min;
        }

        public int getFirstElement() {
            return heap[0];
        }
    }

    public static class MaxHeap {
        private int[] heap;
        private int heapSize;

        public int getHeapSize() {
            return heapSize;
        }

        public int[] getHeap() {
            return heap;
        }

        public MaxHeap(int size) {
            heapSize = 0;
            heap = new int[size];
        }

        private int parent(int i) {
            if (i == 0) {
                return 0;
            }
            return (i - 1) / two;
        }

        private int leftChild(int i) {
            return 2 * i + 1;
        }

        private int rightChild(int i) {
            return 2 * i + 2;
        }

        private void swap(int[] heap, int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void heapifyUp(int i) {
            if (i > 0 && heap[parent(i)] < heap[i]) {
                swap(heap, i, parent(i));
                heapifyUp(parent(i));
            }
        }

        private void heapifyDown(int i) {
            int left = leftChild(i);
            int right = rightChild(i);
            int largest = i;
            if (left < heapSize && heap[left] > heap[i]) {
                largest = left;
            }
            if (right < heapSize && heap[right] > heap[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(heap, i, largest);
                heapifyDown(largest);
            }
        }

        public void insert(int element) {
            heapSize++;
            heap[heapSize - 1] = element;
            heapifyUp(heapSize - 1);
            heapifyUp(heapSize - 1);
        }

        public int extract() {
            int max = heap[0];
            heap[0] = heap[heapSize - 1];
            heapSize--;
            heapifyDown(0);
            return max;
        }

        public int getFirstElement() {
            return heap[0];
        }
    }

}
