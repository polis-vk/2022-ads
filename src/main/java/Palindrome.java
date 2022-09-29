import java.io.*;
import java.util.StringTokenizer;

public class Palindrome {
    private Palindrome() {
        // Should not be instantiated
    }

    private static void solve(final Palindrome.FastScanner in, final PrintWriter out) {
        int N = in.nextInt();
        String input = in.next();

        int[] arr = new int[input.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = input.charAt(i) - 'A';
        }

        int minNum = arr[0];
        int maxNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            minNum = Math.min(minNum, arr[i]);
            maxNum = Math.max(maxNum, arr[i]);
        }

        int[] countArr = new int[maxNum - minNum + 1];
        for (int value : arr) {
            countArr[value - minNum]++;
        }

        int numNotEven = 0;
        int firstNotEvenIndex = countArr.length;
        for (int i = 0; i < countArr.length; i++) {
            if (countArr[i] % 2 == 1) {
                numNotEven++;
                firstNotEvenIndex = Math.min(i, firstNotEvenIndex);
            }
        }

        int[] palindrome;
        if (numNotEven == 0) {
            palindrome = new int[arr.length];
        } else {
            int palindromeLength = 0;

            for (int i = 0; i < countArr.length; i++) {
                if (i != firstNotEvenIndex && countArr[i] % 2 == 1) {
                    countArr[i]--;
                }
                palindromeLength += countArr[i];
            }

            palindrome = new int[palindromeLength];
        }

        for (int i = 0, start = 0, end = palindrome.length - 1; i < countArr.length; i++) {
            for (int k = 0; k < countArr[i] / 2; k++) {
                palindrome[start++] = i + minNum;
            }

            for (int k = 0; k < countArr[i] / 2; k++) {
                palindrome[end--] = i + minNum;
            }
        }

        if (firstNotEvenIndex != countArr.length) {
            palindrome[palindrome.length / 2] = firstNotEvenIndex + minNum;
        }

        for (int num : palindrome) {
            out.print((char) (num + 'A'));
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
        final Palindrome.FastScanner in = new Palindrome.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
