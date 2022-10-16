package company.vk.polis.ads.part4.GenryEden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EOlymp4261 {
    private EOlymp4261() {
        // Should not be instantiated
    }
    // submission url: https://www.eolymp.com/ru/submissions/11798882
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        out.println(countInverses(arr, 0, n).counter);
    }

    private static ArrWithCounter countInverses(int[] inpArr, int from, int to){
        if (from == to - 1){
            return new ArrWithCounter(inpArr[from], 0);
        }
        int midIndex = from + ((to - from) >> 1);
        ArrWithCounter leftPart = countInverses(inpArr, from, midIndex);
        ArrWithCounter rightPart = countInverses(inpArr, midIndex, to);
        ArrWithCounter ans = mergeWithCount(leftPart.arr, rightPart.arr);
        ans.counter += leftPart.counter + rightPart.counter;
        return ans;
    }

    private static ArrWithCounter mergeWithCount(int[] first, int[] second){
        ArrWithCounter ans = new ArrWithCounter(new int[first.length + second.length], 0);
        int firstCnt = 0;
        int secondCnt = 0;
        while (firstCnt < first.length && secondCnt < second.length){
            if (first[firstCnt] > second[secondCnt]){
                ans.counter += (first.length - firstCnt);
                ans.arr[firstCnt + secondCnt] = second[secondCnt++];
            } else {
                ans.arr[firstCnt + secondCnt] = first[firstCnt++];
            }
        }
        while (firstCnt < first.length){
            ans.arr[firstCnt + secondCnt] = first[firstCnt];
            firstCnt++;
        }
        while (secondCnt < second.length){
            ans.arr[firstCnt + secondCnt] = second[secondCnt];

            secondCnt++;
        }
        return ans;
    }

    private static class ArrWithCounter{
        public int[] arr;
        public int counter;
        public ArrWithCounter(int[] arr, int counter){
            this.arr = arr;
            this.counter = counter;
        }

        public ArrWithCounter(int val, int counter){
            this.arr = new int[]{val};
            this.counter = counter;
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
