package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Task_A {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        int[] arr = new int[N];;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++){
            arr[i]= in.nextInt();
            if (arr[i] < min){
                min = arr[i];
            }
            if (arr[i] > max){
                max = arr[i];
            }
        }
        final int range = max - min + 1;
        int[] newArr = new int[range];
        for (int i = 0; i < N; i++){
            newArr[arr[i] - min]++;
        }
        for(int i = 0; i < range; ++i){
            if (newArr[i] > 0){
                System.out.println(i+min);
                newArr[i]--;
                i--;
            }
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
