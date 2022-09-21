import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    // constants
    public static final String PUSH_MSG = "push";
    public static final String POP_MSG = "pop";
    public static final String BACK_MSG = "back";
    public static final String SIZE_MSG = "size";
    public static final String CLEAR_MSG = "clear";
    public static final String EXIT_MSG = "exit";
    public static final String OK_MSG = "ok";
    public static final String ERROR_MSG = "error";
    public static final String BYE_MSG = "bye";

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> stack = new LinkedList<>();
        while(true){
            String command = in.next();
            switch (command){
                case PUSH_MSG:
                    stack.add(0, in.nextInt());
                    out.println(OK_MSG);
                    break;
                case POP_MSG:
                    if(stack.isEmpty())
                        out.println(ERROR_MSG);
                    else{
                        out.println(stack.get(0));
                        stack.remove(0);
                    }
                    break;
                case BACK_MSG:
                    if (stack.isEmpty())
                        out.println(ERROR_MSG);
                    else
                        out.println(stack.get(0));
                    break;
                case SIZE_MSG:
                    out.println(stack.size());
                    break;
                case CLEAR_MSG:
                    stack.clear();
                    out.println(OK_MSG);
                    break;
                case EXIT_MSG:
                    out.println(BYE_MSG);
                    return;
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
