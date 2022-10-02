import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Part2_3 {
    private Part2_3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numOfParticipants = in.nextInt();
        Participant[] participants = new Participant[numOfParticipants];
        for(int i = 0; i < numOfParticipants; i++) {
            participants[i] = new Participant(in.nextInt(), in.nextInt());
        }
        mergeSort(participants, 0 , numOfParticipants);
        for(int i = 0; i < numOfParticipants; i++) {
            out.println(participants[i].toString());
        }
    }
    private static void mergeSort(Participant[] array, int fromInclusive, int toExclusive) {
        if(fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        mergeSort(array, fromInclusive, mid);
        mergeSort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }
    private static void merge(Participant[] array, int fromInclusive, int mid, int toExclusive) {

        int positionA = 0, positionB = 0;
         Participant[] arrayCopy = new Participant[toExclusive - fromInclusive];
         ParticipantCompare participantCompare = new ParticipantCompare();
        for(int i = 0; i < arrayCopy.length; i++) {
            if(positionA == mid - fromInclusive) {
                arrayCopy[i] = array[mid + positionB];
                positionB++;
            } else if (positionB == toExclusive - mid) {
                arrayCopy[i] = array[fromInclusive + positionA];
                positionA++;
            }
            else if(participantCompare.compare(array[fromInclusive + positionA], array[mid + positionB]) < 0) {
                arrayCopy[i] = array[fromInclusive + positionA];
                positionA++;
            } else  {
                arrayCopy[i] = array[mid + positionB];
                positionB++;
            }
        }
        for(int i = 0; i < arrayCopy.length; i++) {
            array[fromInclusive + i] = arrayCopy[i];
        }

    }
    private static class Participant  {
        int indexNumber;
        int points;
        Participant(int indexNumber, int points) {
            this.indexNumber = indexNumber;
            this.points = points;
        }
        public String toString() {
            return (indexNumber + " " + points);
        }
    }
    private static class ParticipantCompare implements Comparator<Participant> {

        public int compare(Participant a, Participant b) {
            if(a.points > b.points) {
                return -1;
            } else if (a.points < b.points) {
                return 1;
            } else {
                if(a.indexNumber < b.indexNumber) {
                    return -1;
                } else if (a.indexNumber > b.indexNumber) {
                    return 1;
                } else {
                    return 0;
                }
            }
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