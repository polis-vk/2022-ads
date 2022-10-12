import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Mediana {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner sc = new Scanner(System.in);
        Heap maxHeap = new Heap(true);
        Heap minHeap = new Heap(false);
        int median = sc.nextInt();
        System.out.println(median);
        int count = 0;
        while (sc.hasNext()) {
            count++;
            int digit = sc.nextInt();
            if (count % 2 == 1) {
                if (digit <= median) {
                    maxHeap.insert(digit);
                    minHeap.insert(median);

                } else {
                    minHeap.insert(digit);
                    maxHeap.insert(median);
                }
                maxHeap.swim(maxHeap.size);
                minHeap.swim(minHeap.size);
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                if (digit <= median) {
                    maxHeap.insert(digit);
                    maxHeap.swim(maxHeap.size);
                    median = maxHeap.extract();
                } else {
                    minHeap.insert(digit);
                    minHeap.swim(minHeap.size);
                    median = minHeap.extract();
                }
            }
            System.out.println(median);
        }
    }

    private static class Heap {
        public int[] arrayHeap = new int[20];
        private final boolean isMax;
        public int size = 0;

        public Heap(boolean status) {
            this.isMax = status;
        }

        public int extract() {
            int result = arrayHeap[1];
            swap(1, size--);
            sink();
            return result;
        }

        private void swap(int left, int right) {
            int buff = arrayHeap[left];
            arrayHeap[left] = arrayHeap[right];
            arrayHeap[right] = buff;
        }

        private void sink() {
            int currentIndex = 1;
            if (isMax) {
                while (2 * currentIndex <= size) {
                    int childIndex = 2 * currentIndex;
                    if (childIndex < size && arrayHeap[childIndex] < arrayHeap[childIndex + 1]) {
                        childIndex++;
                    }
                    if (arrayHeap[currentIndex] >= arrayHeap[childIndex]) {
                        break;
                    }
                    swap(currentIndex, childIndex);
                    currentIndex = childIndex;
                }
            } else {
                while (2 * currentIndex <= size) {
                    int childIndex = 2 * currentIndex;
                    if (childIndex < size && arrayHeap[childIndex] > arrayHeap[childIndex + 1]) {
                        childIndex++;
                    }
                    if (arrayHeap[currentIndex] <= arrayHeap[childIndex]) {
                        break;
                    }
                    swap(currentIndex, childIndex);
                    currentIndex = childIndex;
                }
            }
        }

        public int peek() {
            return arrayHeap[1];
        }

        public void insert(int number) {
            if (size == arrayHeap.length - 1) {
                arrayHeap = Arrays.copyOf(arrayHeap, arrayHeap.length * 2);
            }
            arrayHeap[++size] = number;
            swim(size);
        }

        public void swim(int index) {
            int currentIndex = index;
            if (isMax) {
                while (currentIndex > 1 && arrayHeap[currentIndex] > arrayHeap[currentIndex / 2]) {
                    swap(currentIndex, currentIndex / 2);
                    currentIndex /= 2;
                }
            } else {
                while (currentIndex > 1 && arrayHeap[currentIndex] < arrayHeap[currentIndex / 2]) {
                    swap(currentIndex, currentIndex / 2);
                    currentIndex /= 2;
                }
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
}
