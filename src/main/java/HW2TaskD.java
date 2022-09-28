import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class HW2TaskD {
    private HW2TaskD() {
        // Should not be instantiated
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static int randomPartition(int[] data, int startIndex, int endIndex){
        int i = ThreadLocalRandom.current().nextInt(endIndex - startIndex) + startIndex;
        swap(data, startIndex, i);
        return partition(data, startIndex, endIndex);
    }

    private static int partition(int[] data, int startIndex, int endIndex){
        int selectedItem = data[startIndex];
        int i = startIndex;
        for (int j = startIndex + 1; j < endIndex; j++){
            if (data[j] <= selectedItem){
                swap(data, ++i, j);
            }
        }
        swap(data, i, startIndex);
        return i;
    }

    private static void quickSort(int[] data, int startIndex, int endIndex){
        if (startIndex >= endIndex - 1){
            return;
        }
        int i = randomPartition(data, startIndex, endIndex);
        quickSort(data, startIndex, i);
        quickSort(data, i + 1, endIndex);
    }

    private static boolean areSimilar(int[] data1, int[] data2){
        int i = 0;
        int j = 0;
        while (i < data1.length && j < data2.length){
            if (data1[i] != data2[j]){
                return false;
            }
            while (i + 1 < data1.length && data1[i] == data1[i + 1]) {
                i++;
            }
            while (j + 1 < data2.length && data2[j] == data2[j + 1]) {
                j++;
            }
            i++;
            j++;
        }
        return i == data1.length && j == data2.length;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size1 = in.nextInt();
        int[] array1 = new int[size1];
        for (int i = 0; i < size1; i++){
            array1[i] = in.nextInt();
        }
        int size2 = in.nextInt();
        int[] array2 = new int[size2];
        for (int i = 0; i < size2; i++){
            array2[i] = in.nextInt();
        }
        quickSort(array1, 0, size1);
        quickSort(array2, 0, size2);

        if (areSimilar(array1, array2)){
            System.out.println("YES");
        }
        else {
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
