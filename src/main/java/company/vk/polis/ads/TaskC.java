

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
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static class Member implements Comparable<Member> {
        private int id;
        private int points;

        public Member(int id, int points) {
            this.id = id;
            this.points = points;
        }

        public int compareTo(Member member) {
            if (this.points != member.points) {
                return points - member.points;
            } else {
                return member.id - id;
            }
        }

    }

    public static void merge(Member[] members, int start, int mid, int end) {
        int i, j, k;
        int n1 = mid - start + 1;
        int n2 = end - mid;
        Member[] LeftArray = new Member[n1];
        Member[] RightArray = new Member[n2];
        for (i = 0; i < n1; i++) {
            LeftArray[i] = members[start + i];
        }
        for (j = 0; j < n2; j++) {
            RightArray[j] = members[mid + 1 + j];
        }
        i = 0;
        j = 0;
        k = start;
        while (i < n1 && j < n2) {
            if (LeftArray[i].compareTo(RightArray[j]) <= 0) {
                members[k] = LeftArray[i];
                i++;
            } else {
                members[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            members[k] = LeftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            members[k] = RightArray[j];
            j++;
            k++;
        }
    }

    static void mergeSort(Member[] members, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(members, start, mid);
            mergeSort(members, mid + 1, end);
            merge(members, start, mid, end);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        int id = 0;
        int points = 0;
        Member[] members = new Member[length];
        for (int i = 0; i < length; i++) {
            id = in.nextInt();
            points = in.nextInt();
            members[i] = new Member(id, points);
        }
        mergeSort(members, 0, length - 1);
        for (int i = 0; i < members.length / 2; i++) {
            Member temp = members[i];
            members[i] = members[members.length - 1 - i];
            members[members.length - 1 - i] = temp;
        }
        for (Member i : members) {
            out.println(i.id + " " + i.points);
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
