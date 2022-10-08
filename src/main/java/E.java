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
public final class E {
    final static char FIRST_LETTER = 'A';
    final static char LAST_LETTER = 'Z';
    final static char OUT_OF_ALPHABET = (char) ('Z' + 1);

    private E() {
        // Should not be instantiated
    }

    static int[] getCountArr(char[] arr, char min, char max) {
        int minmaxDiff = max - min;

        int[] countArr = new int[minmaxDiff + 1];
        for (int character : arr) {
            countArr[character - min]++;
        }
        return countArr;
    }

    static String getPalindrome(int[] letters) {
        StringBuilder palindrome = new StringBuilder();

        char lowestChar = OUT_OF_ALPHABET;

        // Проходимся от А до Z
        // Будем вставлять буквы в конец StringBuilder,
        // а затем просто пройдемся с конца до начала,
        // чтобы в зеркальном отражении вставить полученную строку в её конец
        for (int i = 0; i < letters.length; i++) {
            // Пока таких букв есть больше двух и не меньше одной
            while (letters[i] / 2 != 0) {
                // Вставляем две буквы в середину палиндрома
                palindrome.append((char) (i + 'A'));
                letters[i] -= 2;
            }
            // Если ещё не задана минимальная в алфавитном порядке буква и текущая буква осталась одна
            // Сохраняем её
            if (lowestChar == OUT_OF_ALPHABET && letters[i] == 1) {
                lowestChar = (char) (i + 'A');
                letters[i]--;
            }
        }

        int midOfPalindrome = palindrome.length();

        // Можем вставить в середину минимальную по алфавитному порядку букву,
        // которая была сохранена ранее, если такая есть
        if (lowestChar != OUT_OF_ALPHABET) {
            palindrome.append(lowestChar);
        }

        // Вставляем вторую половину палиндрома
        for(int i = midOfPalindrome - 1; i >= 0; i--){
            palindrome.append(palindrome.charAt(i));
        }

        return palindrome.toString();
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Можно обойтись без числа букв
        in.nextInt();

        // Считываем буквенную последовательность
        String stringIn = in.next();

        // Получаем счетный массив строчных букв
        int[] letters = getCountArr(stringIn.toCharArray(), FIRST_LETTER, LAST_LETTER);

        // Получаем искомый палиндром
        String palindrome = getPalindrome(letters);

        out.println(palindrome);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
