package company.vk.polis.ads.part3.YuklyaevskiyIgnat.task2;





import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Scanner;


/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11739097
 * @author Dmitry Schitinin
 */
public final class Main {

    private Main() {
        // Should not be instantiated
    }
    private static class MyHeap {
        private int[] values;
        public int size;
        boolean maxOrMin;
        MyHeap(int maxSize, boolean maxOrMin) {
            this.values = new int[maxSize + 1];
            this.size = 0;
            this.maxOrMin = maxOrMin;
        }
        void swim(int k) {
            while(k > 1 && (maxOrMin ? values[k] > values[k/2] : values[k] < values[k/2])) {
                swap(values, k , k/2);
                k = k / 2;
            }
        }
        void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if(j < size && (maxOrMin ? values[j] < values[j+1] : values[j] > values[j+1])) j++;
                if(maxOrMin ? values[k] >= values[j] : values[k] <= values[j]) {
                    break;
                }
                swap(values, k, j);
                k = j;
            }
        }
        void insert(int v) {
            values[++size] = v;
            swim(size);
        }
        int extract() {
            int max = values[1];
            swap(values, 1, size--);
            sink(1);
            return max;
        }

        void swap(int[] array, int son, int father) {
            int temp = array[son];
            array[son] = array[father];
            array[father] = temp;
        }
        int front() {
            return values[1];
        }

    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        MyHeap maxHeap = new MyHeap(500001, true);
        MyHeap minHeap = new MyHeap(500001, false);
        int value, median, newMaxValue, newMinValue;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            value = scanner.nextInt();
            if (minHeap.size == maxHeap.size) {
                maxHeap.insert(value);
                newMaxValue = maxHeap.extract();
                minHeap.insert(newMaxValue);
            } else {
                minHeap.insert(value);
                newMinValue = minHeap.extract();
                maxHeap.insert(newMinValue);
            }
            if (minHeap.size > maxHeap.size) {
                median = minHeap.front();
                out.println(median);

            } else {
                median = (minHeap.front() + maxHeap.front()) / 2;
                out.println(median);
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
