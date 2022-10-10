package company.vk.polis.ads;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

public class TaskC {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Student[] students = new Student[n];
        temp = new Student[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Student(in.nextInt(), in.nextInt());
        }
        mergeSort(students, 0, students.length);

        for (int i = 0; i < n; i++) {
            System.out.println(students[i].getId() + " " + students[i].getPoints());
        }

    }

    private static void mergeSort(Student[] students, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(students, fromInclusive, mid);
        mergeSort(students, mid, toExclusive);
        merge(students, fromInclusive, mid, toExclusive);
    }

    private static Student[] temp;

    private static void merge(Student[] students, int fromInclusive, int mid, int toInclusive) {

        int leftBorder = fromInclusive;
        int rightBorder = mid;

        while (leftBorder < mid && rightBorder < toInclusive) {
            if (students[leftBorder].compareTo(students[rightBorder]) > 0) {
                temp[leftBorder + rightBorder - fromInclusive - mid] = students[leftBorder];
                leftBorder++;
            } else {
                temp[leftBorder + rightBorder - fromInclusive - mid] = students[rightBorder];
                rightBorder++;
            }
        }
        while (leftBorder < mid) {
            temp[leftBorder + rightBorder - fromInclusive - mid] = students[leftBorder];
            leftBorder++;
        }
        while (rightBorder < toInclusive) {
            temp[leftBorder + rightBorder - fromInclusive - mid] = students[rightBorder];
            rightBorder++;
        }
        for (leftBorder = fromInclusive; leftBorder < toInclusive; leftBorder++) {
            students[leftBorder] = temp[leftBorder - fromInclusive];
        }
    }

    private static class Student implements Comparable<Student> {

        private final int id;
        private final int points;

        public Student(int id, int points) {
            this.id = id;
            this.points = points;
        }

        public int getPoints() {
            return points;
        }

        public int getId() {
            return id;
        }

        @Override
        public int compareTo(Student o) {
            return Comparator.comparing(Student::getPoints).thenComparing(Student::getId, Comparator.reverseOrder())
                    .compare(this, o);
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
