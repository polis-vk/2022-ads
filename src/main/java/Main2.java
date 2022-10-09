import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main2 { // m2

    private static final int[] arrayMax = new int[500001];
    private static final int[] arrayMin = new int[500002];
    private static int sizeMax = 0;
    private static int sizeMin = 0;

    private static void swimMax(int k) {
        while (k > 1 && arrayMax[k] > arrayMax[k / 2]) {
            swap(arrayMax, k, k / 2);
            k = k / 2;
        }
    }

    public static void insertMax(int elem) {
        arrayMax[++sizeMax] = elem;
        swimMax(sizeMax);
    }

    private static void swimMin(int k) {
        while (k > 1 && arrayMin[k] < arrayMin[k / 2]) {
            swap(arrayMin, k, k / 2);
            k = k / 2;
        }
    }

    public static void insertMin(int elem) {
        arrayMin[++sizeMin] = elem;
        swimMin(sizeMin);
    }

    private static void swap(int[] array, int k, int halfK) {
        int temp = array[k];
        array[k] = array[halfK];
        array[halfK] = temp;
    }

    private static void sinkMax(int k) {
        while (2 * k <= sizeMax) {
            int j = 2 * k;
            if (j < sizeMax && arrayMax[j] < arrayMax[j + 1]) {
                j++;
            }
            if (arrayMax[k] >= arrayMax[j]) {
                break;
            }
            swap(arrayMax, k, j);
            k = j;
        }
    }

    private static int extractMax() {
        int max = arrayMax[1];
        swap(arrayMax, 1, sizeMax--);
        sinkMax(1);
        return max;
    }

    private static void sinkMin(int k) {
        while (2 * k <= sizeMin) {
            int j = 2 * k;
            if (j < sizeMin && arrayMin[j] > arrayMin[j + 1]) {
                j++;
            }
            if (arrayMin[k] <= arrayMin[j]) {
                break;
            }
            swap(arrayMin, k, j);
            k = j;
        }
    }

    private static int extractMin() {
        int min = arrayMin[1];
        swap(arrayMin, 1, sizeMin--);
        sinkMin(1);
        return min;
    }

    public static int peekMax() {
        if (sizeMax == 0) {
            return 0;
        }
        return arrayMax[1];
    }

    public static int peekMin() {
        if (sizeMin == 0) {
            return 0;
        }
        return arrayMin[1];
    }

    private static int calcM2(int elem) {
        if (sizeMin == sizeMax) {
            insertMax(elem);
            insertMin(extractMax());
        } else {
            insertMin(elem);
            insertMax(extractMin());
        }

        int median;
        if (sizeMin > sizeMax) {
            median = peekMin();
        }else {
            median = (peekMin() + peekMax()) / 2;
        }

        return median;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            out.println(calcM2(scanner.nextInt()));
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
