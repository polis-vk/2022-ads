package company.vk.polis.ads;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Median {

    //https://www.eolymp.com/ru/submissions/11734095
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Scanner sc = new Scanner(System.in);
        Heap heapMin = new MinHeap();
        Heap heapMax = new Heap();
        int median = 0;
        boolean calculated = true;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (heapMax.size() == 0 && heapMin.size() == 0 && calculated) {
                median = n;
                calculated = false;
            } else {
                if (n < median) {
                    heapMax.insert(n);
                    if (!calculated) {
                        heapMin.insert(median);
                        median = (heapMin.peek() + heapMax.peek()) / 2;
                        calculated = true;
                    } else {
                        median = heapMax.extract();
                        calculated = false;
                    }

                } else {
                    heapMin.insert(n);
                    if (!calculated) {
                        heapMax.insert(median);
                        median = (heapMin.peek() + heapMax.peek()) / 2;
                        calculated = true;
                    } else {
                        median = heapMin.extract();
                        calculated = false;
                    }
                }
            }
            out.println(median);
        }
    }

    public static class MinHeap extends Heap {

        @Override
        protected void swim(int k) {
            while (k > 1 && a.get(k) < a.get(k / 2)) {
                super.swap(k, k / 2);
                k /= 2;
            }
        }

        @Override
        protected void sink(int k) {
            while (2 * k <= super.size()) {
                int j = 2 * k;
                if (j < super.size() && a.get(j) > a.get(j + 1)) {
                    j++;
                }
                if (a.get(k) <= a.get(j)) {
                    break;
                }
                super.swap(k, j);
                k = j;
            }
        }
    }

    public static class Heap {
        protected final List<Integer> a;

        public Heap() {
            a = new ArrayList<>();
            a.add(0);
        }

        public void insert(int x) {
            a.add(x);
            swim(size());
        }


        public int extract() {
            int max = a.get(1);
            swap(1, size());
            a.remove(size());
            sink(1);
            return max;
        }


        public int peek() {
            return a.get(1);
        }


        protected void sink(int k) {
            while (2 * k <= size()) {
                int j = 2 * k; // left child
                if (j < size() && a.get(j) < a.get(j + 1)) {
                    j++; //right child
                }
                if (a.get(k) >= a.get(j)) {
                    break; // invariant holds
                }
                swap(k, j);
                k = j;
            }
        }

        protected void swim(int k) {
            while (k > 1 && a.get(k) > a.get(k / 2)) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void swap(int k1, int k2) {
            int temp = a.get(k1);
            a.set(k1, a.get(k2));
            a.set(k2, temp);
        }

        private int size() {
            return a.size() - 1;
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
        } catch (IOException ignored) {

        }
    }
}
