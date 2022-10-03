package company.vk.polis.ads;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Task_D {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int Nf = in.nextInt();
        int[] firstArr = new int[Nf];
        for (int i = 0; i < Nf; i++) {
            firstArr[i] = in.nextInt();
        }
        int Ns = in.nextInt();
        int[] secondArr = new int[Ns];
        for (int i = 0; i < Ns; i++) {
            secondArr[i] = in.nextInt();
        }
        Set<Integer> firstSet = new HashSet<>();
        for (int digit : firstArr) {
            if (!firstSet.contains(digit)) {
                firstSet.add(digit);
            }
        }
        Set<Integer> secondSet = new HashSet<>();
        for (int digit : secondArr) {
            if (!secondSet.contains(digit)) {
                secondSet.add(digit);
            }
        }
        if (firstSet.equals(secondSet)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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
