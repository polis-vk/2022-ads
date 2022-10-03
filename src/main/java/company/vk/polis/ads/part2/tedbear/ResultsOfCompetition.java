package company.vk.polis.ads.part2.tedbear;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ResultsOfCompetition {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int quantityOfMembers = in.nextInt();

        ArrayList<Member> memberList = new ArrayList<>();
        Member member;

        for (int i = 0; i < quantityOfMembers; i++) {
            member = new Member(in.nextInt(), in.nextInt());
            memberList.add(member);
        }

        sort(memberList, 0, quantityOfMembers);

        for (int i = 0; i < quantityOfMembers; i++) {
            out.write(memberList.get(i).getId() + " ");
            out.write(memberList.get(i).getResult() + "\n");
        }
    }

    public static void sort(ArrayList<Member> array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) / 2);
        sort(array, fromInclusive, mid);
        sort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static void merge(ArrayList<Member> arrayToSort, int fromInclusive, int mid, int toExclusive) {
        ArrayList<Member> tempArray = new ArrayList<>();

        int leftIndex = fromInclusive;
        int rightIndex = mid;

        while (leftIndex < mid && rightIndex < toExclusive) {
            if (arrayToSort.get(leftIndex).compareTo(arrayToSort.get(rightIndex)) > 0) {
                tempArray.add(arrayToSort.get(leftIndex));
                leftIndex++;
            } else {
                tempArray.add(arrayToSort.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < mid) {
            tempArray.add(arrayToSort.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < toExclusive) {
            tempArray.add(arrayToSort.get(rightIndex));
            rightIndex++;
        }


        for (int i = 0; i < tempArray.size(); fromInclusive++) {
            arrayToSort.set(fromInclusive, tempArray.get(i++));
        }
    }

    private static class Member implements Comparable<Member> {
        private final int id;
        private final int result;

        public Member(int id, int result) {
            this.id = id;
            this.result = result;
        }

        public int getResult() {
            return this.result;
        }
        public int getId() {
            return this.id;
        }

        @Override
        public int compareTo(Member o) {
            if (this.result > o.getResult() || (this.id < o.getId() && this.result == o.getResult())) {
                return 1;
            } else if (this.result < o.getResult() && this.id > o.getId()) {
                return -1;
            }

            return 0;
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
