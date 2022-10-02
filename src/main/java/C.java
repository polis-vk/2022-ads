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

public final class C {
    private C() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int OlympianNumber = in.nextInt();

        // Выделяем массив под данные олимпиадников
        Olympian[] olympians = new Olympian[OlympianNumber];

        int inID;
        int inScore;
        // Заполняем данные олимпиадников
        for (int i = 0; i < OlympianNumber; i++) {
            inID = in.nextInt();
            inScore = in.nextInt();
            olympians[i] = new Olympian(inID, inScore);
        }

        QuickSort<Olympian> sorter = new QuickSort<Olympian>();

        // Сортируем быстрой сортировкой
        sorter.quickSort(olympians, 0, olympians.length - 1);

        for (Olympian participant : olympians) {
            out.println(participant.toString());
        }

    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    public static class QuickSort<T extends Comparable<T>> {
        int partition(T[] arr, int low, int high) {
            // Выбираем последний элемент массива в качестве опорного элемента
            T knot = arr[high];
            int i = (low - 1);
            // Производим сравнения слева от опорного элемента
            for (int j = low; j < high; j++) {
                if (arr[j].compareTo(knot) > 0) {
                    i++;
                    T tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }

            T tmp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = tmp;

            return i + 1;
        }


        // Рекурсивно сортируем части разделенного массива
        void quickSort(T[] arr, int low, int high) {
            if (low < high) {
                // Разделяем каждую массив на две части
                int knot = partition(arr, low, high);

                // Сортируем каждую разделенную часть
                quickSort(arr, low, knot - 1);
                quickSort(arr, knot + 1, high);
            }
        }
    }

    static class Olympian implements Comparable<Olympian> {
        private final int id;
        private final int score;

        Olympian(int id, int score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public int getScore() {
            return score;
        }

        public int compareTo(Olympian o) {
            // Если this.score > o.score, то this > o
            int scoreDiff = this.getScore() - o.getScore();

            // Если this.score == o.score, то
            // если this.id > o.id, то o this < o
            if (scoreDiff == 0) {
                return o.getId() - this.getId();
            }

            return scoreDiff;
        }

        @Override
        public String toString() {
            return id + " " + score;
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
