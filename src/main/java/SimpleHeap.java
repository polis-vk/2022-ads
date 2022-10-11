import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SimpleHeap {
    public static class Heap {
        private final ArrayList<Integer> heap;

        public Heap() {
            heap = new ArrayList<>();
            heap.add(null);
        }

        public void insert(int value) {
            heap.add(value);
            swim(getSize());
        }

        public int exctract() {
            int maxValue = heap.get(1);
            swap(1, getSize());
            heap.remove(getSize());
            sink(1);
            return maxValue;
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int cntCommands = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < cntCommands; i++) {
            int command = in.nextInt();
            switch (command) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.exctract());
                    break;
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
