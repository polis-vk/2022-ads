import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
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
public final class TASK4HM2 {
    private TASK4HM2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        boolean answer = true;
        int n = in.nextInt();
        int[] array1 = new int[n];
        for(int i = 0; i < n; i++){
            array1[i] = in.nextInt();
        }
        quickSort(array1, 0, n - 1);
        int m = in.nextInt();
        int[] array2 = new int[m];
        for(int i = 0; i < m; i++){
            array2[i] = in.nextInt();
        }
        quickSort(array2, 0, m - 1);
        int i = 0, j = 0;
        while(i < array1.length && j < array2.length){
            i = indexOfEqualsSubarrays(array1, i);
            j = indexOfEqualsSubarrays(array2, j);
            if (array1[i] != array2[j]) {
                answer = false;
                break;
            }
            i++;
            j++;
        }
        boolean limit1 = i >= array1.length;
        boolean limit2 = j >= array2.length;

        answer = limit1 && limit2; //this 1 test :)))

        if(answer){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    static int indexOfEqualsSubarrays(int[] array, int index) {
        int indexCopy = index;
        while (indexCopy < array.length - 1 && array[indexCopy] == array[indexCopy + 1]) {
            indexCopy++;
        }
        return indexCopy;
    }


    static void quickSort(int[] array, int low, int high){
        if(low < high){
            int partitionIndex = partition(array, low, high);
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    static int partition(int[] array, int low, int high){
        int pivot = array[high];
        int count = low - 1;
        for(int j = low; j <= high - 1; j++){
            if(array[j] < pivot){
                count++;
                swap(array, count, j);
            }
        }
        swap(array, count + 1, high);
        return count+1;
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
