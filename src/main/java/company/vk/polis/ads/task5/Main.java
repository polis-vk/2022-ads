package company.vk.polis.ads.task5;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11845184
 * @author Dmitry Schitinin
 */
public final class Main {
    static int inversCount = 0;
    private Main() {
        // Should not be instantiated
    }
    public static int [] sortArray(int[] arrayA){ // сортировка Массива который передается в функцию

        if (arrayA == null) {
            return null;
        }

        if (arrayA.length < 2) {
            return arrayA; // возврат в рекурсию в строки ниже см комменты.
        }

        int [] arrayB = new int[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);


        int [] arrayC = new int[arrayA.length - arrayA.length / 2];
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);


        arrayB = sortArray(arrayB);
        arrayC = sortArray(arrayC);


        return mergeArray(arrayB, arrayC);
    }
    public static int [] mergeArray(int [] arrayA, int [] arrayB) {

        int[] arrayC = new int[arrayA.length + arrayB.length];
        int positionA = 0, positionB = 0;
        boolean isNext = true;
        //int[] copyArray =
        for (int i = 0; i < arrayC.length; i++) {
            if (positionA == arrayB.length) {
                arrayC[i] = arrayA[i - positionA];

                    inversCount += countOfInv(arrayA, arrayB, i - positionA, 0);

                positionB++;
            } else if (positionB == arrayA.length) {
                arrayC[i] = arrayB[i - positionB];
                positionA++;
            } else if (arrayA[i - positionA] < arrayB[i - positionB]) {
                arrayC[i] = arrayA[i - positionA];
                if(isNext) {
                    inversCount += countOfInv(arrayA, arrayB, i - positionA, 0);
                }
                isNext = true;
                positionB++;
            } else {
                arrayC[i] = arrayB[i - positionB];
                if(isNext) {
                    inversCount += countOfInv(arrayA, arrayB, i - positionA, 0);
                }
                isNext = false;
                positionA++;

            }
        }
        return arrayC;
    }
    private static int countOfInv(int[] arrayA, int[] arrayB, int iA, int iB) {
        int count = 0;
        for(int j = iB; j < arrayB.length ; j++) {
            if(arrayA[iA] > arrayB[j]) {
                count++;
            }
        }
        return count;
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        sortArray(array);

        out.println(inversCount);
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
