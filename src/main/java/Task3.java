import java.io.*;
import java.util.StringTokenizer;

public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        IdAndScorePair[] array = new IdAndScorePair[N];
        for (int i = 0; i < N; i++) {
            array[i] = new IdAndScorePair(in.nextInt(), in.nextInt());
        }

        mergeSort(array, N);
        for (IdAndScorePair pair : array) {
            out.println(pair.id + " " + pair.score);
        }
    }

    public static void mergeSort(IdAndScorePair[] a, int N) {
        if (N < 2) {
            return;
        }

        int mid = N / 2;

        IdAndScorePair[] leftArray = new IdAndScorePair[mid];
        IdAndScorePair[] rightArray = new IdAndScorePair[N - mid];

        System.arraycopy(a, 0, leftArray, 0, mid);
        System.arraycopy(a, mid, rightArray, 0, N - mid);

        mergeSort(leftArray, mid);
        mergeSort(rightArray, N - mid);
        merge(a, leftArray, rightArray, mid, N - mid);
    }

    public static void merge(
            IdAndScorePair[] array,
            IdAndScorePair[] leftArray,
            IdAndScorePair[] rightArray,
            int left,
            int right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right) {
            if (leftArray[i].compareTo(rightArray[j]) < 0) {
                array[k++] = leftArray[i++];
            }
            else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < left) {
            array[k++] = leftArray[i++];
        }
        while (j < right) {
            array[k++] = rightArray[j++];
        }
    }

    private static class IdAndScorePair implements Comparable<IdAndScorePair> {
        private int id;
        private int score;

        public IdAndScorePair(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(IdAndScorePair o) {
            return this.score != o.score ? o.score - this.score : this.id - o.id;
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
