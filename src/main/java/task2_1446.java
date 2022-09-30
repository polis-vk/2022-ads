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
public final class task2_1446 {
    private task2_1446() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Comparable[] inp = new Comparable[n];
        for (int i = 0; i < n; i++) {
            inp[i] = new Member(in.nextInt(), in.nextInt());
        }

        inp = mergeSort(inp);

        for (Comparable member : inp) {
            out.println((Member) member);
        }

    }

    private static Comparable[] mergeSort(Comparable[] a) {
        return mergeSort(a, 0, a.length);
    }

    private static Comparable[] mergeSort(Comparable[] a, int from, int to) {
        if (from == to - 1) {
            return new Comparable[]{a[from]};
        }

        int midIndex = from + ((to - from) >> 1);

        Comparable[] leftPart = mergeSort(a, from, midIndex);
        Comparable[] rightPart = mergeSort(a, midIndex, to);
        return merge(leftPart, rightPart);
    }

    private static Comparable[] merge(Comparable[] a, Comparable[] b) {
        Comparable[] ans = new Comparable[a.length + b.length];
        int aCnt = 0;
        int bCnt = 0;

        while (aCnt < a.length && bCnt < b.length) {
            if (a[aCnt].compareTo(b[bCnt]) > 0) {
                ans[aCnt + bCnt] = a[aCnt++];
            } else {
                ans[aCnt + bCnt] = b[bCnt++];
            }
        }

        Comparable[] remainingArr;
        int remCnt;
        int ansCnt = aCnt + bCnt;
        if (a.length == aCnt) {
            remainingArr = b;
            remCnt = bCnt;
        } else {
            remainingArr = a;
            remCnt = aCnt;
        }

        while (remCnt < remainingArr.length) {
            ans[ansCnt++] = remainingArr[remCnt++];
        }

        return ans;
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

class Member implements Comparable {
    int id;
    int score;

    public String toString() {
        return id + " " + score;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Member(int id, int score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Member) {
            if (this.score != ((Member) o).score) {
                return this.score - ((Member) o).score;
            } else {
                return ((Member) o).id - this.id;
            }
        }
        throw new RuntimeException();
    }
}
