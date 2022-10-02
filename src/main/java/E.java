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

        int palindromeMid = 0;

        char lowestChar = OUT_OF_ALPHABET;

        // Проходимся от А до Z
        // Будем вставлять буквы в середину, и, проходясь от младшей буквы к старшей,
        // получим, что первая половина палиндрома будет отсортирована в алфавитном порядке
        for (int i = 0; i < letters.length; i++) {
            // Пока таких букв есть больше двух и не меньше одной
            while (letters[i] / 2 != 0) {
                // Вставляем две буквы в середину палиндрома
                palindromeMid = palindrome.length() / 2;
                palindrome.insert(palindromeMid, (char) (i + 'A'));
                palindrome.insert(palindromeMid, (char) (i + 'A'));
                letters[i] -= 2;
            }
            // Если ещё не задана минимальная в алфавитном порядке буква и текущая буква осталась одна
            // Сохраняем её
            if (lowestChar == OUT_OF_ALPHABET && letters[i] == 1) {
                lowestChar = (char) (i + 'A');
                letters[i]--;
            }
        }
        // Когда все возможные пары букв вставлены в палиндром
        // Можем вставить в середину минимальную по алфавитному порядку букву, которая была сохранена ранее
        if (lowestChar != OUT_OF_ALPHABET) {
            palindromeMid = palindrome.length() / 2;
            palindrome.insert(palindromeMid, lowestChar);
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
