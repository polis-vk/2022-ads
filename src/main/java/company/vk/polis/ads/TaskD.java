package company.vk.polis.ads;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class TaskD {
    private static boolean solve(final FastScanner in) {
        HashSet<Integer> set1 = new HashSet<>();
        int n1 = in.nextInt();
        for (int i = 0; i < n1; i++) {
            set1.add(in.nextInt());
        }

        HashSet<Integer> set2 = new HashSet<>();
        int n2 = in.nextInt();
        for (int i = 0; i < n2; i++) {
            set2.add(in.nextInt());
        }

        return set1.equals(set2);

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
        try (PrintWriter ignored = new PrintWriter(System.out)) {
            if (solve(in)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
