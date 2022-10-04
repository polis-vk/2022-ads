import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskC {

    private static class Participant implements Comparable<Participant> {
        private int identNumber;
        private int result;

        Participant(int identNumber, int result) {
            this.identNumber = identNumber;
            this.result = result;
        }

        public int getIdentNumber() {
            return identNumber;
        }

        public int getResult() {
            return result;
        }

        @Override
        public int compareTo(Participant participant) {
            int result = Integer.compare(getResult(), participant.result);
            if (result == 0) {
                return -Integer.compare(getIdentNumber(), participant.identNumber);
            }
            return result;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int cntParticipant = in.nextInt();
        Participant[] participants = new Participant[cntParticipant];

        for (int i = 0; i < cntParticipant; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }

        mergeSort(participants, 0, cntParticipant);

        for (int i = 0; i < cntParticipant; i++) {
            System.out.println(participants[i].getIdentNumber() + " " + participants[i].getResult());
        }
    }

    private static void mergeSort(Participant[] mas, int fromIndex, int toIndex) {
        if (fromIndex == toIndex - 1) {
            return;
        }
        int mid = toIndex - (toIndex - fromIndex) / 2;
        mergeSort(mas, fromIndex, mid);
        mergeSort(mas, mid, toIndex);
        merge(mas, fromIndex, mid, toIndex);
    }

    private static void merge(Participant[] mas, int fromIndex, int mid, int toIndex) {
        int sizeLeftMas = mid - fromIndex;
        int sizeRightMas = toIndex - mid;
        Participant[] masLeft = new Participant[sizeLeftMas];
        Participant[] masRight = new Participant[sizeRightMas];

        System.arraycopy(mas, fromIndex, masLeft, 0, sizeLeftMas);
        System.arraycopy(mas, mid, masRight, 0, sizeRightMas);

        int i = 0;
        int j = 0;
        int index = fromIndex;

        while (i < sizeLeftMas && j < sizeRightMas) {
            if (masLeft[i].compareTo(masRight[j]) > 0) {
                mas[index++] = masLeft[i++];
            } else {
                mas[index++] = masRight[j++];
            }
        }

        while (i < sizeLeftMas) {
            mas[index++] = masLeft[i++];
        }

        while (j < sizeRightMas) {
            mas[index++] = masRight[j++];
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
