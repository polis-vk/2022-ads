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
public final class OlympiadResults {
    private OlympiadResults() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        Schoolboy[] array = new Schoolboy[size];
        fillArray(array, in);
        sort(array, size);
        for (int i = 0; i < size; i++) {
            out.println(array[i].getId() + " " + array[i].getScore());
        }
    }

    private static void fillArray(Schoolboy[] array, final FastScanner in) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Schoolboy();
            array[i].setId(in.nextInt());
            array[i].setScore(in.nextInt());
        }
    }

    private static void sort(Schoolboy[] array, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        Schoolboy[] leftArray = new Schoolboy[mid];
        Schoolboy[] rightArray = new Schoolboy[size - mid];
        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[i];
        }
        for (int i = mid; i < size; i++) {
            rightArray[i - mid] = array[i];
        }
        sort(leftArray, mid);
        sort(rightArray, size - mid);
        merge(array, leftArray, rightArray, mid, size - mid);
    }

    private static void merge(Schoolboy[] array, Schoolboy[] leftArray, Schoolboy[] rightArray, int left, int right) {

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (leftArray[i].compareTo(rightArray[j]) > 0) {
                array[k] = leftArray[i];
                k++;
                i++;
            } else {
                array[k] = rightArray[j];
                k++;
                j++;
            }
        }
        while (i < left) {
            array[k] = leftArray[i];
            k++;
            i++;
        }
        while (j < right) {
            array[k] = rightArray[j];
            k++;
            j++;
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

    public static class Schoolboy implements Comparable<Schoolboy> {
        private int id;
        private int score;

        public Schoolboy() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public int compareTo(Schoolboy o) {
            if (this.getScore() > o.getScore()) {
                return 1;
            } else if (this.getScore() == o.getScore() && this.getId() < o.getId()) {
                return 1;
            } else if (this.getScore() == o.getScore() && this.getId() == o.getId()) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
