package company.vk.polis.ads.tasks;

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
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MaxHeap maxHeap = new MaxHeap((int) 1e6);
        MinHeap minHeap = new MinHeap((int) 1e6);


        int M = Integer.MIN_VALUE;
        Scanner scan = new Scanner(System.in);
        int T = 0;

        while(scan.hasNextInt()) {
            int number = scan.nextInt();
            if ((T & 1) == 0) {
                if (number <= M) {
                    maxHeap.insert(number);
                    M = maxHeap.extract();
                } else {
                    minHeap.insert(number);
                    M = minHeap.extract();
                }
            } else {
                if (number <= M) {
                    maxHeap.insert(number);
                    minHeap.insert(M);
                } else {
                    maxHeap.insert(M);
                    minHeap.insert(number);
                }

                M = (minHeap.peek() + maxHeap.peek()) >> 1;
            }
            System.out.println(M);
            T++;
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
