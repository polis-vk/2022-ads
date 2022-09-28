package company.vk.polis.ads.week2;

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
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static class Participant implements Comparable<Participant> {
        int id;
        int points;

        public Participant(int id, int points) {
            this.id = id;
            this.points = points;
        }

        @Override
        public int compareTo(Participant o) {
            if (this.points == o.points) {
                return this.id - o.id;
            } else {
                return o.points - this.points;
            }
        }

        @Override
        public String toString() {
            return id + " " + points;
        }
    }

    private static void merge(Participant[] a, Participant[] temp, int fromInclusive, int mid, int toExclusive) {
        int i = fromInclusive;
        int j = mid;
        int k = fromInclusive;

        while (i < mid && j < toExclusive) {
            if (a[i].compareTo(a[j]) <= 0) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i < mid) {
            temp[k++] = a[i++];
        }

        while (j < toExclusive) {
            temp[k++] = a[j++];
        }

        for (k = fromInclusive; k < toExclusive; k++) {
            a[k] = temp[k];
        }
    }

    private static void mergeSort(Participant[] a, Participant[] temp, int fromInclusive, int toExclusive) {
        if (toExclusive <= fromInclusive + 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(a, temp, fromInclusive, mid);
        mergeSort(a, temp, mid, toExclusive);
        merge(a, temp, fromInclusive, mid, toExclusive);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Participant[] a = new Participant[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = new Participant(in.nextInt(), in.nextInt());
        }

        mergeSort(a, new Participant[n], 0, n);

        for (Participant participant : a) {
            out.println(participant);
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
