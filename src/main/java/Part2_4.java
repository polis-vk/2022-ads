import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Part2_4 {
    private Part2_4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arrayA = new int[n];
        for(int i = 0; i < n; i++) {
            arrayA[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] arrayB = new int[m];
        for(int i = 0; i < m; i++) {
            arrayB[i] = in.nextInt();
        }
        quickSort(arrayA, 0 , n);
        quickSort(arrayB, 0 , m);
        int positionA = 0, positionB = 0;
        while(true) {
            boolean aEnd = false;
            boolean bEnd = false;
            if(arrayA[positionA] == arrayB[positionB]) {
                int prev = arrayA[positionA];

                do{
                    if(positionA + 1 < arrayA.length) {
                        positionA++;
                        if(prev != arrayA[positionA]) {
                            break;
                        }
                    }
                    else {
                        aEnd = true;
                        break;
                    }
                }while(true);
                do{
                    if(positionB + 1 < arrayB.length) {
                        positionB++;
                        if(prev != arrayB[positionB]) {
                            break;
                        }
                    }
                    else {
                        bEnd = true;
                        break;
                    }
                }while(true);
                if(aEnd == true && bEnd == true) {
                    out.println("YES");
                    break;
                } else if (aEnd != bEnd) {
                    out.println("NO");
                    break;
                }
            }
            else {
                out.println("NO");
                break;
            }
        }
    }
    private static void quickSort(int[] array, int fromInclusive, int toExclusive) {
        if(fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = partition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }
    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for(int j = fromInclusive + 1; j < toExclusive; j++) {
            if(array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
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