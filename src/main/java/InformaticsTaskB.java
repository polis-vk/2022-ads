import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskB {

    private InformaticsTaskB() {
        // Should not be instantiated
    }

    private static final List<Integer> linkedList = new LinkedList<>();

    private static String push(int n) {
        linkedList.add(n);
        return "ok";
    }

    private static String pop() {
        if (size() == 0)
            return "error";
        String firstElem = front();
        linkedList.remove(0);
        return firstElem;
    }

    private static String front() {
        if (size() == 0)
            return "error";
        return "" + linkedList.get(0);
    }

    private static int size() {
        return linkedList.size();
    }

    private static String clear() {
        linkedList.clear();
        return "ok";
    }

    private static String exit() {
        return "bye";
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        while (in.reader.ready()) {
            String currLine = in.reader.readLine();
            out.println(
                    switch (currLine) {
                        case ("pop") -> pop();
                        case ("front") -> front();
                        case ("size") -> size();
                        case ("clear") -> clear();
                        case ("exit") -> exit();
                        default -> push(Integer.parseInt(currLine.split(" ")[1]));
                    });
            if (currLine.equals("exit"))
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
