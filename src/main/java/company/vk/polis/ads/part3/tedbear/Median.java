package company.vk.polis.ads.part3.tedbear;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11743048

public class Median {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        // Write me
        HeapDesc heapDesc = new HeapDesc();
        HeapAsc heapAsc = new HeapAsc();
        int element;
        int median = 0;
        int counterOfElements = 0;

        do {
            counterOfElements++;
            element = in.nextInt();

            if (counterOfElements == 1) {
                median = element;
            } else if (counterOfElements % 2 == 0) {
                if (element > median) {
                    heapAsc.insert(element);
                    heapDesc.insert(median);
                } else {
                    heapDesc.insert(element);
                    heapAsc.insert(median);
                }

                median = (heapAsc.getFirst() + heapDesc.getFirst()) / 2;
            } else if (counterOfElements % 2 != 0) {
                if (element < median) {
                    heapDesc.insert(element);
                    median = heapDesc.returnFirst();
                } else if (element > median) {
                    heapAsc.insert(element);
                    median = heapAsc.returnFirst();
                }
            }
            out.write(median + "\n");
        } while (in.reader.ready());

    }

    public static class HeapAsc {
        private final ArrayList<Integer> storage = new ArrayList<>();

        // Добавим 0 в конструкторе, чтобы индексация начиналась с 1;
        public HeapAsc() {
            storage.add(0);
        }

        public int getFirst() {
            return storage.get(1);
        }

        public int returnFirst() {
            int minElement = storage.get(1);
            storage.set(1, storage.get(storage.size() - 1));
            storage.remove(storage.size() - 1);

            if (storage.size() > 2) {
                sink(1);
            }

            return minElement;
        }

        private void sink(int k) {
            while (2 * k <= storage.size() - 1) {
                int j = 2 * k;
                if (j < storage.size() - 1 && storage.get(j) > storage.get(j + 1)) {
                    j++;
                }
                if (storage.get(k) < storage.get(j)) {
                    break;
                }
                swap(storage, k, j);
                k = j;
            }
        }

        public void insert (int element) {
            storage.add(element);
            swim(storage.size() - 1);
        }

        private void swim(int n) {
            int indexOfNewElement = n;

            while (indexOfNewElement > 1 && storage.get(indexOfNewElement) < storage.get(indexOfNewElement / 2)) {
                swap(storage, indexOfNewElement, indexOfNewElement / 2);
                indexOfNewElement = indexOfNewElement / 2;
            }
        }
    }

    public static class HeapDesc {
        private final ArrayList<Integer> storage = new ArrayList<>();

        // Добавим 0 в конструкторе, чтобы индексация начиналась с 1;
        public HeapDesc() {
            storage.add(0);
        }

        public int getFirst() {
            return storage.get(1);
        }

        public int returnFirst() {
            int maxElement = storage.get(1);
            storage.set(1, storage.get(storage.size() - 1));
            storage.remove(storage.size() - 1);

            if (storage.size() > 2) {
                sink(1);
            }

            return maxElement;
        }

        private void sink(int k) {
            while (2 * k <= storage.size() - 1) {
                int j = 2 * k;
                if (j < storage.size() - 1 && storage.get(j) < storage.get(j + 1)) {
                    j++;
                }
                if (storage.get(k) > storage.get(j)) {
                    break;
                }
                swap(storage, k, j);
                k = j;
            }
        }

        public void insert(int element) {
            storage.add(element);
            swim(storage.size() - 1);
        }

        private void swim(int n) {
            int indexOfNewElement = n;

            while (indexOfNewElement > 1 && storage.get(indexOfNewElement) > storage.get(indexOfNewElement / 2)) {
                swap(storage, indexOfNewElement, indexOfNewElement / 2);
                indexOfNewElement = indexOfNewElement / 2;
            }
        }
    }

    public static void swap(ArrayList<Integer> array, int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
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
