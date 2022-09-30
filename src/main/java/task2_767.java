
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class task2_767 {
    private task2_767() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int a = in.nextInt();
        int[] aArr = new int[a];
        for (int i = 0; i < a; i++) {
            aArr[i] = in.nextInt();
        }

        int b = in.nextInt();
        int[] bArr = new int[b];
        for (int i = 0; i < b; i++) {
            bArr[i] = in.nextInt();
        }

        quickSort(aArr);
        quickSort(bArr);
        int j = 0;
        int i = 0;
        while (i < aArr.length && j < bArr.length) {
            if (aArr[i] != bArr[j]) {
                out.println("NO");
                return;
            }


            // пропускаем подрядидущие одинаковые символы
            do {
                i++;
            } while (i < aArr.length && aArr[i] == bArr[j]);

            do {
                j++;
            } while (j < bArr.length && aArr[i - 1] == bArr[j]);
        }

        if (i == aArr.length && j == bArr.length) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length);
    }

    private static void quickSort(int[] arr, int fromInc, int toExc) {
        if (toExc <= fromInc + 1) {
            return;
        }

        int mid = partition(arr, fromInc, toExc);
        quickSort(arr, fromInc, mid);
        quickSort(arr, mid + 1, toExc);
    }

    private static int partition(int[] arr, int fromInc, int toExc) {

        int rAddress = ThreadLocalRandom.current().nextInt(fromInc, toExc);
        int tmp = arr[rAddress];
        arr[rAddress] = arr[fromInc];
        arr[fromInc] = tmp;

        int pivot = arr[fromInc];
        int ans = fromInc;

        for (int i = fromInc + 1; i < toExc; i++) {
            if (arr[i] <= pivot) {
                ans++;
                tmp = arr[i];
                arr[i] = arr[ans];
                arr[ans] = tmp;
            }
        }

        tmp = arr[fromInc];
        arr[fromInc] = arr[ans];
        arr[ans] = tmp;

        return ans;
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
