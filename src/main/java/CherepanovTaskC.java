import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CherepanovTaskC {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    static class Participant implements Comparable<Participant> {
        private final int id;
        private final int points;

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
            if (this.points < o.getPoints()) {
                return -1;
            } else if (this.points == o.getPoints()) {
                return this.id < o.getId() ? 1 : -1;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return String.format("%d %d", this.id, this.points);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        Participant[] participants = new Participant[length];

        for (int i = 0; i < length; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }

        mergeSort(participants, 0, length);

        for (Participant participant : participants) {
            out.println(participant.toString());
        }
    }

    public static void mergeSort(Participant[] participants, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(participants, fromInclusive, mid);
        mergeSort(participants, mid, toExclusive);
        merge(participants, fromInclusive, mid, toExclusive);
    }

    public static void merge(Participant[] participants, int left, int mid, int right) {
        int i = 0;
        int j = 0;
        Participant[] result = new Participant[right - left];

        while (left + i < mid && mid + j < right) {
            if (participants[left + i].compareTo(participants[mid + j]) > 0) {
                result[i + j] = participants[left + i];
                i++;
            } else {
                result[i + j] = participants[mid + j];
                j++;
            }
        }

        while (left + i < mid) {
            result[i + j] = participants[left + i];
            i++;
        }

        while (mid + j < right) {
            result[i + j] = participants[mid + j];
            j++;
        }

        System.arraycopy(result, 0, participants, left, i + j);
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
