import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
        Olympian[] copyolympians = Arrays.copyOf(olympians, olympians.length);

        MergeSort<Olympian> olympicsorter = new MergeSort<Olympian>(olympians);

        olympicsorter.mergeSort(olympians, 0, olympians.length - 1);

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

    public static class MergeSort<T extends Comparable<T>> {
        MergeSort(T[] sortableArr){
            helpingArr = Arrays.copyOf(sortableArr, sortableArr.length);
        }

        T[] helpingArr;

        void merge(T[] arr, int low, int mid, int top) {
            int i = low;
            int j = mid + 1;
            int k = low;

            while (i <= mid && j <= top) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    helpingArr[k++] = arr[i++];
                } else {
                    helpingArr[k++] = arr[j++];
                }
            }
            while (i <= mid) {
                helpingArr[k++] = arr[i++];
            }

            for (i = low; i <= top; i++) {
                arr[i] = helpingArr[i];
            }
        }

        void mergeSort(T[] arr, int low, int top) {
            if (top == low) {
                return;
            }

            int mid = (low + ((top - low) / 2));

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, top);

            merge(arr, low, mid, top);
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
