package company.vk.polis.ads.part2.paikeee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Olympics {

    private Olympics() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int n = in.nextInt();
        Student[] result = new Student[n];
        for (int i = 0; i < n; i++) {
            String[] student = in.reader.readLine().split(" ");
            result[i] = new Student(Integer.parseInt(student[0]), Integer.parseInt(student[1]));
        }
        mergeSort(result);
        for (int i = 0; i < n; i++) {
            out.println(result[i].toString());
        }
    }

    private static void merge(Student[] elements, int begin, int middle, int end) {
        Student[] left = Arrays.copyOfRange(elements, begin, middle);
        Student[] right = Arrays.copyOfRange(elements, middle, end);
        int li = 0, ri = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li].compareTo(right[ri]) < 0)) {
                elements[i] = left[li++];
            }
            else {
                elements[i] = right[ri++];
            }
        }
    }

    private static void mergeSort(Student[] elements, int begin, int end) {
        if (end - begin <= 1) return;
        int middle = (begin + end) / 2;
        mergeSort(elements, begin, middle);
        mergeSort(elements, middle, end);
        merge(elements, begin, middle, end);
    }

    public static void mergeSort(Student[] elements) {
        mergeSort(elements, 0, elements.length);
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

    private static class Student implements Comparable<Student> {

        private final int id;
        private final int score;

        private Student(int id, int score) {
            this.id = id;
            this.score = score;
        }

        int getId() {
            return id;
        }

        int getScore() {
            return score;
        }

        @Override
        public int compareTo(Student o) {
            if (this.score < o.getScore() || this.score == o.getScore() && this.id > o.getId()) {
                return 1;
            }
            return -1;
        }

        @Override
        public String toString() {
            return id + " " + score;
        }
    }

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

//        TreeMap<Integer, TreeSet<Integer>> results = new TreeMap<>();
//        int n = Integer.parseInt(in.reader.readLine());
//        while (n > 0) {
//            String[] studentData = in.reader.readLine().split(" ");
//            int id = Integer.parseInt(studentData[0]);
//            int score = Integer.parseInt(studentData[1]);
//            TreeSet<Integer> current = results.getOrDefault(score, new TreeSet<>());
//            current.add(id);
//            results.put(score, current);
//            n--;
//        }
//        for (int score: results.descendingKeySet()) {
//            TreeSet<Integer> current = results.get(score);
//            for (int id: current) {
//                out.println(id + " " + score);
//            }
//        }
//        HashMap<Integer, Integer> results = new HashMap<>();
//        int n = Integer.parseInt(in.reader.readLine());
//        while (n > 0) {
//            String[] studentData = in.reader.readLine().split(" ");
//            int id = Integer.parseInt(studentData[0]);
//            int score = Integer.parseInt(studentData[1]);
//            results.put(id, score);
//            n--;
//        }
//        results.entrySet().stream()
//                .sorted((entry1, entry2) -> {
//                    if (entry1.getValue() < entry2.getValue()) {
//                        return 1;
//                    } else if (entry1.getValue().equals(entry2.getValue())) {
//                        if (entry1.getKey() < entry2.getKey()) {
//                            return -1;
//                        }
//                        return 1;
//                    }
//                    return -1;
//                })
//                .forEach(student -> out.println(student.getKey() + " " + student.getValue()));