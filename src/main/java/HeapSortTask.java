import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HeapSortTask {
    private HeapSortTask() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] array = new int[size];
        fillArray(array, in);
        heapSort(array);
        printArray(array, out);
    }

    private static void fillArray(int[] array, final FastScanner in) {
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
    }

    private static void printArray(int[] array, final PrintWriter out) {
        for (int i = 0; i < array.length; i++) {
            out.print(array[i] + " ");
        }
    }

    private static void heapSort(int[] array) {
        int size = array.length;
        int i = (size - 1) / 2;
        while (i >= 0) {
            heapify(array, i--, size);
        }

        while (size > 0) {
            array[size - 1] = extract(array, size);
            size--;
        }

    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static int rightChild(int i) {
        return 2 * i + 2;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void heapify(int[] array, int i, int size) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;
        if (left < size && array[left] > array[i]) {
            largest = left;
        }
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(array, i, largest);
            heapify(array, largest, size);
        }
    }

    private static int extract(int[] array, int size) {
        int firstElement = array[0];
        array[0] = array[size - 1];
        heapify(array, 0, size - 1);
        return firstElement;
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

