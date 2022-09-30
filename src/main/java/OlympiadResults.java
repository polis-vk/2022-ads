import java.io.*;
import java.util.StringTokenizer;

public class OlympiadResults {
    private OlympiadResults() {
        // Should not be instantiated
    }

    private static class Result implements Comparable<Result> {
        int score;
        int id;

        public Result() {

        }

        public Result(Result result) {
            this.score = result.score;
            this.id = result.id;
        }

        @Override
        public int compareTo(Result result) {
            if (this.score < result.score) {
                return 1;
            } else if (this.score == result.score && this.id > result.id) {
                return 1;
            } else if (this.score == result.score && this.id == result.id) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private static void merge(Result[] results, int fromInclusive, int mid, int toExclusive) {
        Result[] arr1 = new Result[mid - fromInclusive];
        Result[] arr2 = new Result[toExclusive - mid];

        for (int i = fromInclusive; i < mid; i++) {
            arr1[i - fromInclusive] = new Result(results[i]);
        }

        for (int i = mid; i < toExclusive; i++) {
            arr2[i - mid] = new Result(results[i]);
        }

        int k = fromInclusive;
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].compareTo(arr2[j]) < 1) {
                results[k] = new Result(arr1[i++]);
            } else {
                results[k] = new Result(arr2[j++]);
            }
            k++;
        }

        while (i < arr1.length) {
            results[k++] = new Result(arr1[i++]);
        }

        while (j < arr2.length) {
            results[k++] = new Result(arr2[j++]);
        }
    }

    private static void mergeSort(Result[] results, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(results, fromInclusive, mid);
        mergeSort(results, mid, toExclusive);
        merge(results, fromInclusive, mid, toExclusive);
    }

    private static void solve(final OlympiadResults.FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        Result[] results = new Result[N];

        for (int i = 0; i < results.length; i++) {
            results[i] = new Result();
            results[i].id = in.nextInt();
            results[i].score = in.nextInt();
        }

        mergeSort(results, 0, results.length);

        for (Result result : results) {
            out.println(result.id + " " + result.score);
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
        final OlympiadResults.FastScanner in = new OlympiadResults.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
