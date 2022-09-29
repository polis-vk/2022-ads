import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class SimilarArrays {
    private SimilarArrays() {
        // Should not be instantiated
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(arr, pivotIndex, right);
        int pivot = arr[right];

        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i++, j);
            }
        }

        swap(arr, right, i);
        return i;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    private static void readArray(final SimilarArrays.FastScanner in, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
    }
    private static void solve(final SimilarArrays.FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] arr1 = new int[N];
        readArray(in, arr1);

        N = in.nextInt();
        int[] arr2 = new int[N];
        readArray(in, arr2);

        if (arr1.length > arr2.length) {
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        quickSort(arr1, 0, arr1.length - 1);
        quickSort(arr2, 0, arr2.length - 1);

        int num = arr1[0];
        int i = 0, j = 0;
        while (true) {
            if (arr2[j] != num) {
                break;
            }

            while (j < arr2.length && arr2[j] == num) {
                j++;
            }

            while (i < arr1.length && arr1[i] == num) {
                i++;
            }

            if (i == arr1.length || j == arr2.length) {
                break;
            }

            num = arr1[i];
        }

        if (j == arr2.length && i == arr1.length) {
            out.println("YES");
        } else {
            out.println("NO");
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
        final SimilarArrays.FastScanner in = new SimilarArrays.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
