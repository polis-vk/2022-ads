package company.vk.polis.ads.part3.GenryEden;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// submission url: https://www.eolymp.com/ru/submissions/11715816
public final class EOlymp4074 {
    private EOlymp4074() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        // Write me
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();
        int median = in.nextInt();
        out.println(median);
        maxHeap.add(median);
        while (in.reader.ready()) {
            int newVal = 0;
            try {
                newVal = Integer.parseInt(in.reader.readLine());
            }
            catch (NumberFormatException e) {
                break;
            } catch (IOException e) {
                break;
            }
            if (newVal >= median) {
                minHeap.add(newVal);
            } else {
                maxHeap.add(newVal);
            }

            if (minHeap.length() - maxHeap.length() > 1) {
                maxHeap.add(minHeap.pop());
            } else if (maxHeap.length() - minHeap.length() > 1) {
                minHeap.add(maxHeap.pop());
            }

            if (minHeap.length() > maxHeap.length()) {
                median = minHeap.peek();
            } else if (maxHeap.length() > minHeap.length()) {
                median = maxHeap.peek();
            } else {
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            }
            out.println(median);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class MaxHeap {
        public static final int DEFAULT_SIZE = 64;
        private int[] arr;
        private int lastPointer;


        public MaxHeap() {
            this(DEFAULT_SIZE);
        }


        public MaxHeap(int size) {
            this.arr = new int[size];
            this.lastPointer = -1;
        }

        public void add(int value) {
            if (lastPointer >= arr.length - 1) {
                this.resize();
            }

            arr[++lastPointer] = value;

            swim(lastPointer);
        }

        public int peek() {
            return arr[0];
        }

        public int pop() {
            if (lastPointer == -1) {
                throw new NoSuchElementException();
            }

            int ans = arr[0];

            arr[0] = arr[lastPointer--];

            sink(0);

            return ans;
        }

        public int length() {
            return lastPointer + 1;
        }

        public boolean isEmpty() {
            return lastPointer == -1;
        }

        private void sink(int index) {
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = index * 2 + 2;
            if (leftChildIndex <= lastPointer && arr[leftChildIndex] > arr[index] && arr[leftChildIndex] > arr[rightChildIndex]) {
                swap(leftChildIndex, index);
                sink(leftChildIndex);
            } else if (rightChildIndex <= lastPointer && arr[rightChildIndex] > arr[index]) {
                swap(rightChildIndex, index);
                sink(rightChildIndex);
            }
        }

        private void swim(int index) {
            int parentIndex = (index - 1) / 2;

            if (arr[index] > arr[parentIndex]) {
                swap(index, parentIndex);
                swim(parentIndex);
            }


        }

        private void swap(int index1, int index2) {
            int tmp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = tmp;
        }

        private void resize() {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    static class MinHeap {
        public static final int DEFAULT_SIZE = 64;
        private int[] arr;
        private int lastPointer;


        public MinHeap() {
            this(DEFAULT_SIZE);
        }


        public MinHeap(int size) {
            this.arr = new int[size];
            this.lastPointer = -1;
        }

        public void add(int value) {
            if (lastPointer >= arr.length - 1) {
                this.resize();
            }

            arr[++lastPointer] = value;

            swim(lastPointer);
        }

        public int peek() {
            return arr[0];
        }

        public int pop() {
            if (lastPointer == -1) {
                throw new NoSuchElementException();
            }

            int ans = arr[0];

            arr[0] = arr[lastPointer--];

            sink(0);

            return ans;
        }

        public int length() {
            return lastPointer + 1;
        }

        public boolean isEmpty() {
            return lastPointer == -1;
        }

        private void sink(int index) {
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = index * 2 + 2;

            if (leftChildIndex <= lastPointer && arr[leftChildIndex] < arr[index] && arr[leftChildIndex] < arr[rightChildIndex]) {
                swap(leftChildIndex, index);
                sink(leftChildIndex);
            } else if (rightChildIndex <= lastPointer && arr[rightChildIndex] < arr[index]) {
                swap(rightChildIndex, index);
                sink(rightChildIndex);
            }
        }

        private void swim(int index) {
            int parentIndex = (index - 1) / 2;

            if (arr[index] < arr[parentIndex]) {
                swap(index, parentIndex);
                swim(parentIndex);
            }


        }

        private void swap(int index1, int index2) {
            int tmp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = tmp;
        }

        private void resize() {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }


}

