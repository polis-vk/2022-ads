package company.vk.polis.ads.part3.tedbear;

import java.io.*;
import java.util.StringTokenizer;

//https://www.eolymp.com/ru/submissions/11668153

public class Heaping {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int quantityOfCommands = in.nextInt();
        Heap heap = new Heap(quantityOfCommands);
        int nextCommand;

        for (int i = 0; i < quantityOfCommands; i++) {
            nextCommand = in.nextInt();

            switch (nextCommand) {
                case 0 -> {
                    int elementToAdd = in.nextInt();
                    heap.insert(elementToAdd);
                }
                case 1 -> out.write(heap.delMax() + "\n");
            }
        }

    }

    public static class Heap {
        private int size;
        private int lastIndex = 0;
        private final int[] storage;

        public Heap(int size) {
            this.size = ++size;
            storage = new int[size];
        }

        public void insert (int element) {
            storage[++lastIndex] = element;
            swim(lastIndex);
        }

        private void swim(int n) {
            int indexOfNewElement = n;

            while (indexOfNewElement > 1 && storage[indexOfNewElement] > storage[indexOfNewElement / 2]) {
                swap(storage, indexOfNewElement, indexOfNewElement / 2);
                indexOfNewElement = indexOfNewElement / 2;
            }
        }

        public int delMax() {
            int maxElement = storage[1];
            storage[1] = storage[lastIndex];

            if (lastIndex == 1) {
                storage[lastIndex] = 0;
                lastIndex--;
            } else {
                storage[lastIndex] = 0;
                lastIndex--;
                sink(1);
            }

            return maxElement;
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && storage[j] < storage[j + 1]) {
                    j++;
                }
                if (storage[k] >= storage[j]) {
                    break;
                }
                swap(storage, k, j);
                k = j;
            }
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
