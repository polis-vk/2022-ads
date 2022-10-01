import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Olimpia {

    private static class Member implements Comparable<Member> {

        private final int number;
        private final int points;

        public Member(int number, int points) {
            this.number = number;
            this.points = points;
        }

        @Override
        public int compareTo(Member o) {
            return (points == o.points) ? number - o.number : o.points - points;
        }

        @Override
        public String toString() {
            return number + " " + points;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Member[] members = new Member[in.nextInt()];

        for (int i = 0; i < members.length; i++) {
            int number = in.nextInt();
            int points = in.nextInt();
            members[i] = new Member(number, points);
        }

        sort(members, 0, members.length);

        for (Member member : members) {
            out.println(member);
        }
    }

    public static void sort(Member[] members, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(members, fromInclusive, mid);
        sort(members, mid, toExclusive);
        merge(members, fromInclusive, mid, toExclusive);
    }

    public static void merge(Member[] members, int fromInclusive, int mid, int toExclusive) {
        Member[] sortMembers = new Member[members.length];

        int i = fromInclusive;
        int j = mid;
        int index = 0;
        while (i != mid && j != toExclusive) {
            if (members[i].compareTo(members[j]) < 0) {
                sortMembers[index] = members[i];
                index++;
                i++;
            } else {
                sortMembers[index] = members[j];
                index++;
                j++;
            }
        }

        while (j != toExclusive) {
            sortMembers[index] = members[j];
            index++;
            j++;
        }
        while (i != mid) {
            sortMembers[index] = members[i];
            index++;
            i++;
        }

        for (i = 0; i < index; i++) {
            members[i + fromInclusive] = sortMembers[i];
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
