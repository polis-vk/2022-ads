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
public final class Part2Task3 {
    private Part2Task3() {
        // Should not be instantiated
    }

    private static class Student implements Comparable<Student> {

        int id;
        int mark;

        public Student(int id, int mark) {
            this.id = id;
            this.mark = mark;
        }

        @Override
        public int compareTo(Student student) {
            return mark != student.mark ? mark - student.mark : student.id - id;
        }
    }

    private static void mergeSort(Student[] students, int from, int to, Student[] bufferedArray) {
        if (from + 1 >= to) {
            return;
        }
        int mid = from + ((to - from) >> 1);
        mergeSort(students, from, mid, bufferedArray);
        mergeSort(students, mid, to, bufferedArray);
        merge(students, from, mid, to, bufferedArray);
    }

    private static void merge(Student[] students, int from, int mid, int to, Student[] bufferedArray) {
        int i = from;
        int j = mid;

        while (i < mid && j < to) {
            if (students[i].compareTo(students[j]) > 0) {
                bufferedArray[i + j - from - mid] = students[i];
                i++;
            } else {
                bufferedArray[i + j - from - mid] = students[j];
                j++;
            }
        }
        while (i < mid) {
            bufferedArray[i + j - from - mid] = students[i];
            i++;
        }
        while (j < to) {
            bufferedArray[i + j - from - mid] = students[j];
            j++;
        }
        for (i = from; i < to; i++) {
            students[i] = bufferedArray[i - from];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            int id = in.nextInt();
            int mark = in.nextInt();
            students[i] = new Student(id, mark);
        }
        mergeSort(students, 0, n, new Student[n]);
        for (Student student : students) {
            out.println(student.id + " " + student.mark);
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
