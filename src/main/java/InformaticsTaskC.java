import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskC {
    private InformaticsTaskC() {
        // Should not be instantiated
    }

    private static class Particpant implements Comparable<Particpant> {
        private final int id;
        private final int score;

        public Particpant(int id, int score) {
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
        public int compareTo(Particpant o) {
            if (this.score < (o).getScore() || this.score == o.getScore() && this.id > o.getId()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static void sort(Particpant[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid);
        sort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static void merge(Particpant[] array, int fromInclusive, int mid, int toExclusive) {
        int halfIndex1 = fromInclusive;
        int halfIndex2 = mid;
        ArrayList<Particpant> tempList = new ArrayList<>();
        while (halfIndex1 < mid || halfIndex2 < toExclusive) {
            if (halfIndex1 < mid &&
                    (halfIndex2 >= toExclusive || array[halfIndex1].compareTo(array[halfIndex2]) > 0)) {
                tempList.add(array[halfIndex1]);
                halfIndex1++;
            } else {
                tempList.add(array[halfIndex2]);
                halfIndex2++;
            }
        }
        for (int i = 0; i < (toExclusive - fromInclusive); i++) {
            int currIndex = fromInclusive + i;
            array[currIndex] = tempList.get(i);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int number = in.nextInt();
        Particpant[] array = new Particpant[number];
        for (int i = 0; i < number; i++) {
            int id = in.nextInt();
            int score = in.nextInt();
            array[i] = new Particpant(id, score);
        }
        sort(array, 0, array.length);
        for (int i = 0; i < number; i++) {
            out.println(array[i].getId() + " " + array[i].getScore());
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
