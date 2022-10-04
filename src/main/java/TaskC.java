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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Participant[] participants = new Participant[n];
        for (int i = 0; i < n; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }
        mergeSort(participants, 0, participants.length);
        for (Participant participant : participants) {
            out.println(participant);
        }
    }

    public static void mergeSort(Participant[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    public static void merge(Participant[] array, int fromInclusive, int mid, int toExclusive) {
        Participant[] sortParticipants = new Participant[array.length];
        int leftCursor = fromInclusive;
        int rightCursor = mid;
        int i = 0;
        while (leftCursor != mid && rightCursor != toExclusive) {
            if (array[leftCursor].compareTo(array[rightCursor]) < 0) {
                sortParticipants[i] = array[leftCursor];
                i++;
                leftCursor++;
            } else {
                sortParticipants[i] = array[rightCursor];
                i++;
                rightCursor++;
            }
        }
        while (leftCursor != mid) {
            sortParticipants[i] = array[leftCursor];
            i++;
            leftCursor++;
        }
        while (rightCursor != toExclusive) {
            sortParticipants[i] = array[rightCursor];
            i++;
            rightCursor++;
        }
        for (leftCursor = 0; leftCursor < i; leftCursor++) {
            array[leftCursor + fromInclusive] = sortParticipants[leftCursor];
        }
    }

    private static final class Participant implements Comparable<Participant> {
        private final int id;
        private final int points;

        private Participant(int id, int points) {
            this.id = id;
            this.points = points;
        }

        @Override
        public int compareTo(Participant participant) {
            return (points == participant.points) ? id - participant.id : participant.points - points;
        }

        @Override
        public String toString() {
            return id + " " + points;
        }

    }
    /*
    //informaitcs не принимает java16
        private record Participant(int id, int points) implements Comparable<Participant> {

        @Override
        public int compareTo(Participant participant) {
            return (points == participant.points) ? id - participant.id : participant.points - points;
        }

        @Override
        public String toString() {
            return id + " " + points;
        }

    }
     */

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