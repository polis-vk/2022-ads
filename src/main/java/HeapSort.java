import java.io.*;
import java.util.StringTokenizer;

public class HeapSort {
    private static void sink(int[] mas, int positionStart, int positionEnd) {
        while (positionStart * 2 <= positionEnd) {
            int j = 2 * positionStart;
            if (j < positionEnd && mas[j] < mas[j + 1]) {
                j++;
            }
            if (mas[positionStart] >= mas[j]) {
                break;
            }
            swap(mas, positionStart, j);
            positionStart = j;
        }
    }

    private static void swap(int[] mas, int positionOne, int positionTwo) {
        int temp = mas[positionOne];
        mas[positionOne] = mas[positionTwo];
        mas[positionTwo] = temp;
    }

    private static void makeHeap(int[] mas) {
        int length = mas.length - 1;
        for (int i = length / 2; i >= 1; i--) {
            sink(mas, i, length);
        }
    }

    private static void sort(int[] heap) {
        int length = heap.length - 1;
        while (length > 1) {
            swap(heap, 1, length--);
            sink(heap,1, length);
        }
    }

    private static void heapSort(int[] mas) {
        makeHeap(mas);
        sort(mas);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int cntDigits = in.nextInt();
        int[] mas = new int[++cntDigits];

        for (int i = 1; i < cntDigits; i++) {
            mas[i] = in.nextInt();
        }

        heapSort(mas);

        for (int i = 1; i < cntDigits; i++) {
            out.print(mas[i] + " ");
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
