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
        public int compareTo(Participant other) {
            if (points == other.points) {
                return id > other.id ? -1 : 1;
            } else if (points > other.points) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return id + " " + points;
        }

    }

    private static void merge(Participant[] participantsArray, int fromInclusive, int mid, int toExclusive) {
        // Create temporary arrays
        Participant[] leftArray = new Participant[mid - fromInclusive];
        Participant[] rightArray = new Participant[toExclusive - mid];
        System.arraycopy(participantsArray, fromInclusive, leftArray, 0, leftArray.length);
        System.arraycopy(participantsArray, mid, rightArray, 0, rightArray.length);
        // Merge algorithm
        int curLeft = 0;
        int curRight = 0;
        int curPos = fromInclusive;
        while (curLeft < leftArray.length && curRight < rightArray.length) {
            if (leftArray[curLeft].compareTo(rightArray[curRight]) > 0) {
                participantsArray[curPos++] = leftArray[curLeft++];
            } else {
                participantsArray[curPos++] = rightArray[curRight++];
            }
        }
        while (curLeft < leftArray.length) {
            participantsArray[curPos++] = leftArray[curLeft++];
        }
        while (curRight < rightArray.length) {
            participantsArray[curPos++] = rightArray[curRight++];
        }
    }

    private static void mergeSort(Participant[] participantsArray, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(participantsArray, fromInclusive, mid);
        mergeSort(participantsArray, mid, toExclusive);
        merge(participantsArray, fromInclusive, mid, toExclusive);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Read N
        int participantAmount = in.nextInt();
        // Read data to array
        Participant[] participantsArray = new Participant[participantAmount];
        for (int i = 0; i < participantAmount; i++) {
            participantsArray[i] = new Participant(in.nextInt(), in.nextInt());
        }
        mergeSort(participantsArray, 0, participantAmount);
        for (Participant participant : participantsArray) {
            out.println(participant + " ");
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
