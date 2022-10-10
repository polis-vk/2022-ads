package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

//https://www.eolymp.com/ru/submissions/11743121
public final class Median {

    public static class MaxHeap {
        protected List<Integer> a;
        protected int n;

        public MaxHeap() {
            a = new ArrayList<>();
            a.add(-1);
            n = 0;
        }

        public void insert(int value) {
            a.add(value);
            swim(++n);
        }

        public int peek() {
            return a.get(1);
        }

        public int extract() {
            int max = a.get(1);
            swap(1, n);
            a.remove(n);
            n--;
            sink(1);
            return max;
        }

        protected void swim(int k) {
            while (k > 1 && a.get(k) > a.get(k / 2)) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        protected void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && a.get(j) < a.get(j + 1)) j++;
                if (a.get(k) >= a.get(j)) break;
                swap(k, j);
                k = j;
            }
        }

        protected void swap(int m, int n) {
            int temp = a.get(m);
            a.set(m, a.get(n));
            a.set(n, temp);
        }
    }

    public static class MinHeap extends MaxHeap {

        public MinHeap() {
            super();
        }

        @Override
        protected void swim(int k) {
            while (k > 1 && a.get(k) < a.get(k / 2)) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        @Override
        protected void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && a.get(j) > a.get(j + 1)) j++;
                if (a.get(k) <= a.get(j)) break;
                swap(k, j);
                k = j;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();
        int number;

        //Если у нас есть первое число - считываем его
        if (in.reader.ready()) {
            number = in.nextInt();
        } else {
            return;
        }

        int median = 0;
        //Если есть и второе - считываем и его, кладём 1ое и 2ое числа в наши хипы и выводим первые две медианы
        if (in.reader.ready()) {
            int nextNumber = in.nextInt();
            System.out.println(number);
            median = (nextNumber + number) >> 1;
            System.out.println(median);
            maxHeap.insert(Math.min(number, nextNumber));
            minHeap.insert(Math.max(number, nextNumber));
        } else {
            System.out.println(number);
        }

        boolean oddIteration = false;
        //Дальше идём циклом
        while (in.reader.ready()) {
            oddIteration = !oddIteration;
            number = in.nextInt();

            //Если нечётная итерация
            if (oddIteration) {
                if (number > minHeap.peek()) {
                    median = minHeap.extract();
                    minHeap.insert(number);
                } else if (number < maxHeap.peek()) {
                    median = maxHeap.extract();
                    maxHeap.insert(number);
                } else {
                    median = number;
                }
            } else {
                if (number < median) {
                    maxHeap.insert(number);
                    minHeap.insert(median);
                } else {
                    maxHeap.insert(median);
                    minHeap.insert(number);
                }
                median = (maxHeap.peek() + minHeap.peek()) >> 1;
            }
            System.out.println(median);
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
}
