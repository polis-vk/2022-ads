package company.vk.polis.ads.task3;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }
    private static boolean contains(int[] array, int value) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == value) {
                return true;
            }
        }
        return false;
    }
    private static int indexOfElement(int[] array, int value) {
        for(int i = array.length - 1; i >= 0; i--) {
            if(array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count1 = in.nextInt();

        int[] sequence1 = new int[count1 + 1];
        for(int i = 1; i < count1 + 1; i++) {
            sequence1[i] = in.nextInt();
        }
        int count2 = in.nextInt();
        int[] sequence2 = new int[count2 + 1];
        for(int i = 1; i < count2 + 1; i++) {
            sequence2[i] = in.nextInt();
        }
//        int[] MaxSequence = new int[count2];
//        for(int i = 0; i < count2; i++) {
//            if(contains(sequence1, sequence2[i])) {
//                int maxCurrentSequence = 0;
//                for(int j = i - 1; j >= 0; j--) {
//                    if(indexOfElement(sequence1, sequence2[j]) < indexOfElement(sequence1, sequence2[i])) {
//                        if(MaxSequence[j] > maxCurrentSequence) {
//                            maxCurrentSequence = MaxSequence[j];
//                        }
//                    }
//                }
//                MaxSequence[i] += maxCurrentSequence + 1;
//            }
//        }
//        int result = 0;
//        for(int i = count2 - 1; i >= 0; i--) {
//            if(MaxSequence[i] > result) {
//                result = MaxSequence[i];
//            }
//        }
//        out.println(result);
        int[][] maxCurrentSequence = new int[2][count2 + 1];
        for (int i = 1; i <= count1; i++){
            for (int j = 1; j <= count2; j++){
                if (sequence1[i] == sequence2[j]){
                    maxCurrentSequence[i % 2][j] = 1 + maxCurrentSequence[(i - 1) % 2][j - 1];
                }
                else{
                    maxCurrentSequence[i % 2][j] = Math.max(maxCurrentSequence[(i - 1) % 2][j], maxCurrentSequence[i % 2][j - 1]);
                }
            }
        }
        out.println(maxCurrentSequence[count1 % 2][count2]);
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
