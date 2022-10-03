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
        // Write me
        int quantity;
        quantity = in.nextInt();
        Member members[] = new Member[quantity];

        for (int i = 0; i < quantity; i++) {
            Member temp = new Member();
            temp.setId(in.nextInt());
            temp.setScores(in.nextInt());
            members[i] = temp;
        }
        mergeSort(members, 0, quantity);

        for (Member member : members) {
            System.out.println(member);
        }
    }

    private static void mergeSort(Member[] members, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(members, fromInclusive, mid);
        mergeSort(members, mid, toExclusive);
        merge(members, fromInclusive, mid, toExclusive);
    }

    private static void merge(Member[] members, int fromInclusive, int mid, int toExclusive) {
        Member[] leftEdge = Arrays.copyOfRange(members, fromInclusive, mid);
        Member[] rightEdge = Arrays.copyOfRange(members, mid, toExclusive);

        int i = 0;
        int j = 0;
        int k = fromInclusive;

        for (;i < leftEdge.length && j < rightEdge.length;k++) {
            if (leftEdge[i].compareTo(rightEdge[j]) < 0) {
                members[k] = leftEdge[i++];
            } else {
                members[k] = rightEdge[j++];
            }
        }

        while (i < leftEdge.length) {
            members[k++] = leftEdge[i++];
        }

        while (j < rightEdge.length) {
            members[k++] = rightEdge[j++];
        }

    }

    private static class Member implements Comparable<Member> {
        private int id;
        private int scores;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScores() {
            return scores;
        }

        public void setScores(int scores) {
            this.scores = scores;
        }

        @Override
        public int compareTo(Member o) {
            if (this.scores > o.scores) {
                return -1;
            } else if (this.scores < o.scores) {
                return 1;
            } else {
                return (this.id < o.id) ? -1 : 1;
            }
        }

        @Override
        public String toString() {
            return id + " " + scores;
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