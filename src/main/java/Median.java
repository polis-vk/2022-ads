import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Median {

    public static class MaxHeap {
        private final ArrayList<Integer> heap;

        public MaxHeap() {
            heap = new ArrayList<>();
            heap.add(null);
        }

        public void insert(int value) {
            heap.add(value);
            swim(getSize());
        }

        public int maxDel() {
            int maxValue = heap.get(1);
            swap(1, getSize());
            heap.remove(getSize());
            sink(1);
            return maxValue;
        }

        public int peek() {
            return heap.get(1);
        }

        int getSize() {
            return heap.size() - 1;
        }

        private void swim(int position) {
            while (position > 1 && heap.get(position) > heap.get(position / 2)) {
                swap(position, position / 2);
                position = position / 2;
            }
        }

        private void sink(int position) {
            while (position * 2 <= getSize()) {
                int j = 2 * position;
                if (j < getSize() && heap.get(j) < heap.get(j + 1)) {
                    j++;
                }
                if (heap.get(position) >= heap.get(j)) {
                    break;
                }
                swap(position, j);
                position = j;
            }
        }

        private void swap(int positionOne, int positionTwo) {
            int temp = heap.get(positionOne);
            heap.set(positionOne, heap.get(positionTwo));
            heap.set(positionTwo, temp);
        }

    }

    public static class MinHeap {
        private final ArrayList<Integer> heap;

        public MinHeap() {
            heap = new ArrayList<>();
            heap.add(null);
        }

        public void insert(int value) {
            heap.add(value);
            swim(getSize());
        }

        public int minDel() {
            int minValue = heap.get(1);
            swap(1, getSize());
            heap.remove(getSize());
            sink(1);
            return minValue;
        }

        public int peek() {
            return heap.get(1);
        }

        int getSize() {
            return heap.size() - 1;
        }

        private void swim(int position) {
            while (position > 1 && heap.get(position) < heap.get(position / 2)) {
                swap(position, position / 2);
                position = position / 2;
            }
        }

        private void sink(int position) {
            while (position * 2 <= getSize()) {
                int j = 2 * position;
                if (j < getSize() && heap.get(j) > heap.get(j + 1)) {
                    j++;
                }
                if (heap.get(position) <= heap.get(j)) {
                    break;
                }
                swap(position, j);
                position = j;
            }
        }

        private void swap(int positionOne, int positionTwo) {
            int temp = heap.get(positionOne);
            heap.set(positionOne, heap.get(positionTwo));
            heap.set(positionTwo, temp);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();

        boolean flag = true;
        int median = in.nextInt();
        System.out.println(median);
        while (in.reader.ready()) {
            int digit = in.nextInt();
            if (flag) {
                if (digit > median) {
                    minHeap.insert(digit);
                    maxHeap.insert(median);
                } else {
                    maxHeap.insert(digit);
                    minHeap.insert(median);
                }
                median = (maxHeap.peek() + minHeap.peek()) / 2;
                flag = false;
            } else {
                if (digit > median) {
                    minHeap.insert(digit);
                    median = minHeap.minDel();
                } else {
                    maxHeap.insert(digit);
                    median = maxHeap.maxDel();
                }
                flag = true;
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}


