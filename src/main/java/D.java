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
public final class D {
    private D() {
        // Should not be instantiated
    }

    static int[] readArr(final FastScanner in) {
        int numAmount = in.nextInt();
        int[] numsIn = new int[numAmount];
        for (int i = 0; i < numAmount; i++) {
            numsIn[i] = in.nextInt();
        }

        return numsIn;
    }

    static String compareArrs(int[] arr1, int[] arr2) {

        int currNum;

        for (int ctr1 = 0, ctr2 = 0; ctr1 < arr1.length && ctr2 < arr1.length; ) {
            if (arr1[ctr1] == arr2[ctr2]) {
                currNum = arr1[ctr1];
                ctr1++;
                ctr2++;
                while (ctr1 < arr1.length && arr1[ctr1] == currNum) {
                    ctr1++;
                }
                while (ctr2 < arr2.length && arr2[ctr2] == currNum) {
                    ctr2++;
                }
            } else {
                return "NO";
            }
        }
        return "YES";
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Прочтем входные массивы
        int[] arr1 = readArr(in);
        int[] arr2 = readArr(in);


        QuickSort sorter = new QuickSort();

        sorter.quickSort(arr1, 0, arr1.length - 1);
        sorter.quickSort(arr2, 0, arr2.length - 1);

        out.println(compareArrs(arr1, arr2));
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    public static class QuickSort {
        int partition(int[] arr, int low, int high) {
            // Выбираем последний элемент массива в качестве опорного элемента
            int knot = arr[high];
            int i = (low - 1);
            // Производим сравнения слева от опорного элемента
            for (int j = low; j < high; j++) {
                if (arr[j] > knot) {
                    i++;
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }

            int tmp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = tmp;

            return i + 1;
        }


        // Рекурсивно сортируем части разделенного массива
        void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                // Разделяем каждую массив на две части
                int knot = partition(arr, low, high);

                // Сортируем каждую разделенную часть
                quickSort(arr, low, knot - 1);
                quickSort(arr, knot + 1, high);
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
}
