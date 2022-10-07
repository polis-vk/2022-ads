package company.vk.polis.ads.part3.kirill06344;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
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

    private static class Ascend implements Comparator<Integer> {

        @Override
        public int compare(Integer t1, Integer t2) {
            return t1 - t2;
        }
    }

    private static class Descend implements Comparator<Integer> {
        @Override
        public int compare(Integer t1, Integer t2) {
            return t2 - t1;
        }
    }


    private static class SequenceMedian {
        private Heap minHeap;
        private Heap maxHeap;
        private int currentMedian;
        private int size;

        public SequenceMedian() {
            minHeap = new Heap(new Descend());
            maxHeap = new Heap(new Ascend());
            currentMedian = 0;
            size = 0;
        }

        public void add(int elem) {
            ++size;
            if (size % 2 == 0) {
                if (elem <= currentMedian) {
                    maxHeap.insert(elem);
                    minHeap.insert(currentMedian);
                } else {
                    minHeap.insert(elem);
                    maxHeap.insert(currentMedian);
                }
                currentMedian = (maxHeap.getTop() + minHeap.getTop()) / 2;
            } else {
                if (elem <= currentMedian) {
                    maxHeap.insert(elem);
                    currentMedian = maxHeap.extract();
                } else {
                    minHeap.insert(elem);
                    currentMedian = minHeap.extract();
                }
            }
        }

        public int getCurrentMedian() {
            return currentMedian;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        SequenceMedian sequenceMedian = new SequenceMedian();
        while (scanner.hasNextInt()) {
            int el = scanner.nextInt();
            sequenceMedian.add(el);
            out.println(sequenceMedian.getCurrentMedian());
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

