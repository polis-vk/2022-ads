package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class TaskC {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[][] students = new int[n][2];
        for (int i = 0; i < n; i++) {
            students[i][0] = in.nextInt();
            students[i][1] = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                if (students[i][1] < students[j][1]) {
                    int temp0, temp1;
                    temp0 = students[i][0];
                    temp1 = students[i][1];
                    students[i][0] = students[j][0];
                    students[i][1] = students[j][1];
                    students[j][0] = temp0;
                    students[j][1] = temp1;
                } else if (students[i][1] == students[j][1]) {
                    if (students[i][0] > students[j][0]) {
                        int temp0;
                        temp0 = students[i][0];
                        students[i][0] = students[j][0];
                        students[j][0] = temp0;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(students[i][0] + " " + students[i][1]);
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
