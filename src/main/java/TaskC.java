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
        int n = in.nextInt();
        Participant[] participants = new Participant[n];
        for (int i = 0; i < n; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }

        mergeSort(participants, 0, n);
        for (Participant participant : participants) {
            out.println(participant);
        }
    }

    private static void mergeSort(Participant[] participants, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;
        mergeSort(participants, fromInclusive, mid);
        mergeSort(participants, mid, toExclusive);
        merge(participants, fromInclusive, mid, toExclusive);
    }

    private static void merge(Participant[] participants, int fromInclusive, int mid, int toExclusive) {
        Participant[] leftArray = Arrays.copyOfRange(participants, fromInclusive, mid);
        Participant[] rightArray = Arrays.copyOfRange(participants, mid, toExclusive);
        int i = 0;
        int j = 0;
        int index = fromInclusive;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].compareTo(rightArray[j]) > 0) {
                participants[index] = leftArray[i];
                i++;
            } else {
                participants[index] = rightArray[j];
                j++;
            }
            index++;
        }

        while (i < leftArray.length) {
            participants[index] = leftArray[i];
            index++;
            i++;
        }

        while (j < rightArray.length) {
            participants[index] = rightArray[j];
            index++;
            j++;
        }
    }

    static class Participant implements Comparable<Participant> {
        final int id;
        final int score;

        private static final Comparator<Participant> PARTICIPANT_COMPARATOR =
                Comparator.comparing(Participant::getScore)
                        .thenComparing(Participant::getId, Comparator.reverseOrder());

        public Participant(int id, int score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Participant o) {
            return PARTICIPANT_COMPARATOR.compare(this, o);
        }

        @Override
        public String toString() {
            return id + " " + score;
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

