import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
// https://www.eolymp.com/ru/submissions/11720479
public final class Median {
    private Median() {
        // Should not be instantiated
    }

    private static final int SIZE = 10;

    private static class Heap {
        private int[] array;
        private int size;
        private int maxSize;
        private final Comparator<Integer> comparator;

        public Heap(int size, Comparator<Integer> comparator) {
            this.maxSize = size;
            this.size = 0;
            this.comparator = comparator;
            array = new int[this.maxSize + 1];
        }

        public void insert(int value) {
            if (maxSize - 1 == size) {
                this.maxSize *= 2;
                array = Arrays.copyOf(array, array.length * 2);
            }
            array[++size] = value;
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

        public int size() {
            return size;
        }

        private void swap(int[] array, int i, int j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        private void swim(int k) {
            while (k > 1 && comparator.compare(array[k], array[k / 2]) > 0) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && comparator.compare(array[j + 1], array[j]) > 0) {
                    j++;
                }
                if (comparator.compare(array[k], array[j]) >= 0) {
                    break;
                }
                swap(array, k, j);
                k = j;
            }
        }

    }

    private static void addToHeaps(Heap maxHeap, Heap minHeap, int value) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.insert(value);
            minHeap.insert(maxHeap.delMax());
        } else {
            minHeap.insert(value);
            maxHeap.insert(minHeap.delMax());
        }
    }

    private static int findMedian(Heap maxHeap, Heap minHeap) {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) / 2;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap maxHeap = new Heap(SIZE, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                }
                return 0;
            }
        });
        Heap minHeap = new Heap(SIZE, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                }
                return 0;
            }
        });
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int value = sc.nextInt();
            addToHeaps(maxHeap, minHeap, value);
            out.println(findMedian(maxHeap, minHeap));
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
}
