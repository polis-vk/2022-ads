import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW2TaskC {
    private HW2TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        Participant[] participants = new Participant[count];
        for (int i = 0; i < count; i++) {
            int id = in.nextInt();
            int score = in.nextInt();
            participants[i] = new Participant(id, score);
        }
        sort(participants, 0, participants.length);
        for (Participant participant : participants) {
            out.println(participant);
        }
    }

    private static void sort(Participant[] participants, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(participants, fromInclusive, mid);
        sort(participants, mid, toExclusive);
        merge(participants, fromInclusive, mid, toExclusive);
    }

    private static void merge(Participant[] participants, int fromInclusive, int mid, int toExclusive) {
        Participant[] sorted = new Participant[toExclusive - fromInclusive];
        int i = fromInclusive;
        int j = mid;
        for (int position = 0; position < sorted.length; position++) {
            if (i == mid) {
                sorted[position] = participants[j];
                j++;
            } else if (j == toExclusive) {
                sorted[position] = participants[i];
                i++;
            } else {
                if (participants[i].compareTo(participants[j]) > 0) {
                    sorted[position] = participants[i];
                    i++;
                } else {
                    sorted[position] = participants[j];
                    j++;
                }
            }
        }
        System.arraycopy(sorted, 0, participants, fromInclusive, sorted.length);
    }

    private static class Participant implements Comparable<Participant> {
        private final int id;
        private final int score;

        public Participant(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Participant o) {
            if (this.score == o.score) {
                return (this.id > o.id) ? -1 : 1;
            } else {
                return (this.score > o.score) ? 1 : -1;
            }
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
