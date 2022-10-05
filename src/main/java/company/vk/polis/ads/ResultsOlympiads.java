//package company.vk.polis.ads;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class ResultsOlympiads {

    private static <T> void merge(ArrayList<T> list, int fromInclusive, int mid, int toExclusive, Comparator<T> comparator) {
        ArrayList<T> temp = new ArrayList<>();

        int leftIndex = fromInclusive;
        int rightIndex = mid;
        while (leftIndex < mid && rightIndex < toExclusive) {
            if (comparator.compare(list.get(leftIndex), list.get(rightIndex)) == -1) {
                temp.add(list.get(leftIndex++));
            } else {
                temp.add(list.get(rightIndex++));
            }
        }

        while (leftIndex < mid) {
            temp.add(list.get(leftIndex++));
        }

        while (rightIndex < toExclusive) {
            temp.add(list.get(rightIndex++));
        }

        for (int i = 0; i < temp.size(); fromInclusive++) {
            list.set(fromInclusive, temp.get(i++));
        }
    }

    public static <T> void mergeSort(ArrayList<T> array, int fromInclusive, int toExclusive, Comparator<T> comparator) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid, comparator);
        mergeSort(array, mid, toExclusive, comparator);
        merge(array, fromInclusive, mid, toExclusive, comparator);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<MemberOlympiada> members = new ArrayList<>();
        int capacity = in.nextInt();
        for (int i = 0; i < capacity; i++) {
            members.add(new MemberOlympiada(in.nextInt(), in.nextInt()));
        }
        mergeSort(members, 0, capacity, new Comparator<MemberOlympiada>() {
            @Override
            public int compare(MemberOlympiada o1, MemberOlympiada o2) {
                if ((o1.getScore() > o2.getScore()) || (o1.getScore() == o2.getScore() && o1.getId() < o2.getId())) {
                    return -1;
                } else if (o1.getScore() < o2.getScore() || o1.getId() > o2.getId()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (MemberOlympiada member : members) {
            out.write(member.getId() + " " + member.getScore() + "\n");
        }
    }

    private static final class MemberOlympiada {
        private final int id;
        private final int score;

        public MemberOlympiada(int id, int score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public int getScore() {
            return score;
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

