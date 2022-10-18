package company.vk.polis.ads.part4.artemokky;

import java.io.*;
import java.util.*;



public class BiggestSeq
{
    static int[] readSeq(FastScanner in){
        int[] seq = new int[in.nextInt()];
        for (int i = 0; i < seq.length; i++) {
            seq[i] = in.nextInt();
        }
        return seq;
    }

    static void solve(FastScanner in, PrintWriter out){
        int seq1[];
        int seq2[];
        int res[][];

        seq1 = readSeq(in);
        seq2 = readSeq(in);


        res = new int[2][seq2.length + 1];
        for (int i = 1; i <= seq1.length; i++) {
            for (int j = 1; j <= seq2.length; j++) {
                if (seq1[i - 1] == seq2[j - 1]) {
                    res[i % 2][j] = res[i % 2][j - 1] + 1;
                } else {
                    res[i % 2][j] = Math.max(res[(i - 1) % 2][j], res[i % 2][j - 1]);
                }
            }
        }

        out.println(res[seq1.length % 2][seq2.length]);
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