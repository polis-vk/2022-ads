import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskD {
    private TaskD() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeMasOne = in.nextInt();
        int[] masOne = new int[sizeMasOne];
        for (int i = 0; i < sizeMasOne; i++) {
            masOne[i] = in.nextInt();
        }

        int sizeMasTwo = in.nextInt();
        int[] masTwo = new int[sizeMasTwo];
        for (int i = 0; i < sizeMasTwo; i++) {
            masTwo[i] = in.nextInt();
        }

        quickSort(masOne, 0, sizeMasOne);
        quickSort(masTwo, 0, sizeMasTwo);

        if (compare(masOne, masTwo)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private static boolean compare(int[] masOne, int[] masTwo) {
        int i = 0;
        int j = 0;

        boolean flagAnswer = true;
        boolean flagEndSizeMasOne = true;
        boolean flagEndSizeMasTwo = true;

        while (flagEndSizeMasOne || flagEndSizeMasTwo) {

            while (i != masOne.length - 1 && masOne[i] == masOne[i + 1]) {
                i++;
            }

            while (j != masTwo.length - 1 && masTwo[j] == masTwo[j + 1]) {
                j++;
            }

            if (masOne[i] != masTwo[j]) {
                flagAnswer = false;
                break;
            }

            if (i != masOne.length - 1) {
                i++;
            } else {
                flagEndSizeMasOne = false;
            }

            if (j != masTwo.length - 1) {
                j++;
            } else {
                flagEndSizeMasTwo = false;
            }

        }

        return flagAnswer;
    }

    private static void quickSort(int[] mas, int fromIndex, int toIndex) {
        if (fromIndex >= toIndex - 1) {
            return;
        }

        int i = partition(mas, fromIndex, toIndex);
        quickSort(mas, fromIndex, i);
        quickSort(mas, i + 1, toIndex);
    }

    private static int partition(int[] mas, int fromIndex, int toIndex) {
        int pivotElement = mas[fromIndex];
        int i = fromIndex;
        for (int j = fromIndex + 1; j < toIndex; j++) {
            if (mas[j] <= pivotElement) {
                swap(mas, ++i, j);
            }
        }
        swap(mas, i, fromIndex);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
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