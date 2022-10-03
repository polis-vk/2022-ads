import java.io.*;
import java.util.StringTokenizer;

public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N1 = in.nextInt();
        int[] array1 = new int[N1];
        for (int i = 0; i < N1; i++) {
            array1[i] = in.nextInt();
        }
        int N2 = in.nextInt();
        int[] array2 = new int[N2];
        for (int i = 0; i < N2; i++) {
            array2[i] = in.nextInt();
        }
        quickSort(array1, 0 , N1 - 1);
        quickSort(array2, 0 , N2 - 1);
        int i = 0;
        int j = 0;
        while (i != N1 && j != N2) {
            while (i != N1 - 1 && array1[i] == array1[i + 1]) {
                i++;
            }
            while (j != N2 - 1 && array2[j] == array2[j + 1]) {
                j++;
            }
            if (array1[i] != array2[j]) {
                out.println("NO");
                return;
            }
            i++;
            j++;
        }
        out.println(i == N1 && j == N2 ? "YES" : "NO");
    }

    private static void quickSort(int[] array, int left, int right) {
        if (array.length == 0 || left >= right) {
            return;
        }

        int support = array[left + (right - left) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            while (array[i] < support) {
                i++;
            }
            while (array[j] > support) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i++] = array[j];
                array[j--] = temp;
            }
        }

        if (left < j) {
            quickSort(array, left, j);
        }
        if (right > i) {
            quickSort(array, i, right);
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
