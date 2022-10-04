import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Participant[] array = new Participant[n];
        buffer = new Participant[n];

        for (int i = 0; i < n; i++) {
            array[i] = new Participant(in.nextInt(), in.nextInt());
        }

        mergeSort(array, 0, array.length);
        for (int i = 0; i < n; i++) {
            out.println(array[i].toString());
        }
    }

    private static class Participant implements Comparable<Participant> {
        final Integer id;
        final Integer mark;

        @Override
        public int compareTo(Participant p) {
            int result = this.mark.compareTo(p.mark);

            if (result == 0) {
                result = -this.id.compareTo(p.id);
            }
            return result;
        }

        @Override
        public String toString() {
            return id + " " + mark;
        }

        public Participant(int id, int mark) {
            this.id = id;
            this.mark = mark;
        }
    }

    private static void mergeSort(Participant[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static Participant[] buffer;

    private static void merge(Participant[] array, int fromInclusive, int mid, int toExclusive) {
        int pointer1 = fromInclusive;
        int pointer2 = mid;

        if (toExclusive - fromInclusive >= 0) {
            System.arraycopy(array, fromInclusive, buffer, fromInclusive, toExclusive - fromInclusive);
        }

        for (int i = fromInclusive; i < toExclusive; i++) {
            if (pointer1 == mid) {
                array[i] = buffer[pointer2++];
            } else if (pointer2 == toExclusive) {
                array[i] = buffer[pointer1++];
            } else if (buffer[pointer1].compareTo(buffer[pointer2]) < 0) {
                array[i] = buffer[pointer2++];
            } else {
                array[i] = buffer[pointer1++];
            }
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
