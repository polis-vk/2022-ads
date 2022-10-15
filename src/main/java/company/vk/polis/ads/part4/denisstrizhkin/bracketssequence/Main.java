package company.vk.polis.ads.part4.denisstrizhkin.bracketssequence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11797962
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int[][] dArr;
    private static boolean[][] dArrMatch;
    private static int[][] dArrK;
    private static List<Character> sequence;

    private static boolean isCompatible(int i, int j) {
        if (sequence.get(i) == '(' && sequence.get(j) == ')') {
            return true;
        } else if (sequence.get(i) == '[' && sequence.get(j) == ']') {
            return true;
        }

        return false;
    }

    private static int getDValue(int i, int j) {
        if (i == j) {
            dArrK[i][j] = -1;
            return 1;
        }

        int matchD = Integer.MAX_VALUE;
        if (isCompatible(i, j)) {
            matchD = dArr[i + 1][j - 1];
        }

        int minD = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int left = dArr[i][k];
            int right = dArr[k + 1][j];

            if (left + right < minD) {
                minD = left + right;
                dArrK[i][j] = k;
            }
        }

        if (matchD < minD) {
            dArrMatch[i][j] = true;
            return matchD;
        }

        return minD;
    }

    private static String getNewSequence(int i, int j) {
        if (i > j) {
            return "";
        } else if (dArrMatch[i][j]) {
            return sequence.get(i) + getNewSequence(i + 1, j - 1) + sequence.get(j);
        } if (dArrK[i][j] == -1) {
            switch (sequence.get(i)) {
                case '(':
                case ')':
                    return "()";
                case '[':
                case ']':
                    return "[]";
            }
        }

        return getNewSequence(i, dArrK[i][j]) + getNewSequence(dArrK[i][j] + 1, j);
    }

    private static void solve(final Main.FastScanner in, final PrintWriter out) {
        sequence = new ArrayList<>();

        try {
            String input = in.next();
            for (Character c : input.toCharArray()) {
                sequence.add(c);
            }
        } catch (NullPointerException e) {
            out.println("");
        }

        dArr = new int[sequence.size()][sequence.size()];
        dArrMatch = new boolean[sequence.size()][sequence.size()];
        dArrK = new int[sequence.size()][sequence.size()];

        for (int j = 0; j < sequence.size(); j++) {
            for (int i = 0; j + i < sequence.size(); i++) {
                dArr[i][j + i] = getDValue(i, j + i);
            }
        }

        out.println(getNewSequence(0, sequence.size() - 1));
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
        final Main.FastScanner in = new Main.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}