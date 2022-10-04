import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        Participant[] participants = new Participant[amount];
        for (int i = 0; i < amount; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }
        mergeSort(participants, 0, participants.length);
        Arrays.stream(participants).forEach(s -> System.out.println(s.id + " " + s.points));
    }

    private static void mergeSort(Participant[] participants, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(participants, fromInclusive, mid);
        mergeSort(participants, mid, toExclusive);
        merge(participants, fromInclusive, mid, toExclusive);
    }

    private static void merge(Participant[] participants, int fromInclusive, int mid, int toExclusive) {
        Participant[] leftArr = Arrays.copyOfRange(participants, fromInclusive, mid);
        Participant[] rightArr = Arrays.copyOfRange(participants, mid, toExclusive);
        int leftIndex = 0;
        int rightIndex = 0;
        int insertionIndex = fromInclusive;
        while (fromInclusive + leftIndex < mid && mid + rightIndex < toExclusive) {
            if (leftArr[leftIndex].compareTo(rightArr[rightIndex]) > 0) {
                participants[insertionIndex] = leftArr[leftIndex++];
            } else {
                participants[insertionIndex] = rightArr[rightIndex++];
            }
            insertionIndex++;
        }

        while (fromInclusive + leftIndex < mid) {
            participants[insertionIndex++] = leftArr[leftIndex++];
        }

        while (mid + rightIndex < toExclusive) {
            participants[insertionIndex++] = rightArr[rightIndex++];
        }
    }

    private static class Participant implements Comparable<Participant> {
        int id;
        int points;

        public Participant(int id, int points) {
            this.id = id;
            this.points = points;
        }

        public int getId() {
            return id;
        }

        public int getPoints() {
            return points;
        }

        @Override
        public int compareTo(Participant o) {
            return Comparator.comparing(Participant::getPoints).
                    thenComparing(Participant::getId, Comparator.reverseOrder()).compare(this, o);
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
