import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskF {
    private InformaticsTaskF() {
        // Should not be instantiated
    }

    private static final List<Integer> arrList = new ArrayList<>();

    private static String pushFront(int n) {
        arrList.add(0, n);
        return "ok";
    }

    private static String pushBack(int n) {
        arrList.add(n);
        return "ok";
    }

    private static String popFront() {
        if (size() == 0)
            return "error";
        String firstElem = front();
        arrList.remove(0);
        return firstElem;
    }

    private static String popBack() {
        if (size() == 0)
            return "error";
        String lastElem = back();
        arrList.remove(size() - 1);
        return lastElem;
    }

    private static String front() {
        return (size() == 0) ? "error" : "" + arrList.get(0);
    }

    private static String back() {
        return (size() == 0) ? "error" : "" + arrList.get(size() - 1);
    }

    private static int size() {
        return arrList.size();
    }

    private static String clear() {
        arrList.clear();
        return "ok";
    }

    private static String exit() {
        return "bye";
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        while (in.reader.ready()) {
            String[] currSplitLine = in.reader.readLine().split(" ");
            String command = currSplitLine[0];
            out.println(
                    switch (command) {
                        case ("push_front") -> pushFront(Integer.parseInt(currSplitLine[1]));
                        case ("push_back") -> pushBack(Integer.parseInt(currSplitLine[1]));
                        case ("pop_front") -> popFront();
                        case ("pop_back") -> popBack();
                        case ("front") -> front();
                        case ("back") -> back();
                        case ("size") -> size();
                        case ("clear") -> clear();
                        default -> exit();
                    });
            if (command.equals("exit"))
                break;
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}