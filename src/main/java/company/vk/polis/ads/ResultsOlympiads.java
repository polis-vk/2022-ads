package company.vk.polis.ads;

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

    private static <T> void merge(List<T> src1, int src1Start, List<T> src2, int src2Start, List<T> dest, int destStart, int size, Comparator<T> comparator) {
        int index1 = src1Start;
        int index2 = src2Start;

        int src1End = Math.min(src1Start + size, src1.size());
        int src2End = Math.min(src2Start + size, src2.size());

        int iterCount = src1End - src1Start + src2End - src2Start;

        for (int i = destStart; i < destStart + iterCount; i++) {
            if (index1 < src1End && (index2 >= src2End ||
                    comparator.compare(src1.get(index1),src2.get(index2)) == -1)) { // возможно не -1
                dest.set(i, src1.get(index1));
                index1++;
            } else {
                dest.set(i, src2.get(index2));
                index2++;
            }
        }
    }

    private static <T> List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        List<T> temp;
        List<T> currentSrc = list;
        List<T> currentDest = new ArrayList<>(list.size());

        int size = 1;
        while (size < list.size()) {
            for (int i = 0; i < list.size(); i += 2 * size) {
                merge(currentSrc, i, currentSrc, i + size, currentDest, i, size , comparator);
            }
            temp = currentSrc;
            currentSrc = currentDest;
            currentDest = temp;
            size = size * 2;
        }
        return currentSrc;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<MemberOlympiada> members = new ArrayList<>();
        int capacity = in.nextInt();
        for (int i = 0; i < capacity; i++) {
            members.add(new MemberOlympiada(in.nextInt(), in.nextInt()));
        }
        Collections.sort(members, new Comparator<MemberOlympiada>() {
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

