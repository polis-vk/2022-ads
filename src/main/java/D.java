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

    static int minNum(int[] arr){
        int min = Integer.MAX_VALUE;
        for(int num : arr){
            if(num < min){
                min = num;
            }
        }
        return min;
    }

    static int maxNum(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int num : arr){
            if(num > max){
                max = num;
            }
        }
        return max;
    }

    static int[] getCountArr(int[] arr, int min, int max){
        int minmaxDiff = max - min;

        int[] countArr = new int[minmaxDiff + 1];
        for(int num : arr){
            countArr[num - min]++;
        }

        return countArr;
    }

    static int[] readArr(final FastScanner in){
        int numAmount = in.nextInt();
        int[] numsIn = new int[numAmount];
        for(int i = 0; i < numAmount; i++){
            numsIn[i] = in.nextInt();
        }

        return numsIn;
    }

    static String compareArrs(int[] arr1, int[] arr2){
        if(arr1.length != arr2.length){
            return "NO";
        }
        else{
            for(int i = 0; i < arr1.length; i++){
                // если один элемент присутствует в одном массиве, а в другом нет - return "NO"
                if((arr1[i] == 0 && arr2[i] != 0) || (arr1[i] != 0 && arr2[i] == 0)){
                    return "NO";
                }
            }
        }
        return "YES";
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Прочтем входные массивы
        int[] arr1 = readArr(in);
        int[] arr2 = readArr(in);


        // Для экономии места сразу найдем минимумы и максимумы
        int arr1Min = minNum(arr1);
        int arr1Max = maxNum(arr1);
        int arr2Min = minNum(arr2);
        int arr2Max = maxNum(arr2);

        // Чтобы немного сократить время работы программы, попарно сравним минимумы и максимумы
        if(arr1Min != arr2Min && arr1Max != arr2Max){
            out.println("NO");
            return;
        }

        // Получим счетные массивы
        int[] countArr1 = getCountArr(arr1, arr1Min, arr1Max);
        int[] countArr2 = getCountArr(arr2, arr2Min, arr2Max);

        // Сравним их на содержание одинаковых элементов
        out.println(compareArrs(countArr1,countArr2));
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
