package company.vk.polis.ads.part4.artemokky;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Merge {

    public static class CountingMergeSort {
        private int[] helpingArr;
        private int inversionCount;

        void merge(int[] arr, int low, int mid, int top) {
            int i = low;
            int j = mid + 1;
            int k = low;

            while (i <= mid && j <= top) {
                if (arr[i] <= arr[j]) {
                    helpingArr[k++] = arr[i++];
                } else {
                    helpingArr[k++] = arr[j++];
                    inversionCount += mid - i + 1;
                }
            }
            while (i <= mid) {
                helpingArr[k++] = arr[i++];
            }

            for (i = low; i <= top; i++) {
                arr[i] = helpingArr[i];
            }
        }

        void mergeSort(int[] arr, int low, int top) {
            if (top <= low) {
                return;
            }

            int mid = (low + ((top - low) >> 1));

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, top);

            merge(arr, low, mid, top);
        }

        void run(int[] arr){
            helpingArr = Arrays.copyOf(arr, arr.length);
            inversionCount = 0;

            mergeSort(arr, 0, arr.length - 1);

            helpingArr = null;
        }

        int getInversionCount(){
            return inversionCount;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int arrSize = in.nextInt();
        int[] arr = new int[arrSize];

        for(int i = 0; i < arrSize; i++){
            arr[i] = in.nextInt();
        }

        CountingMergeSort sorter = new CountingMergeSort();

        sorter.run(arr);

        out.println(sorter.getInversionCount());
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
