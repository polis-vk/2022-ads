
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Part2_5 {
    private Part2_5() {
        // Should not be instantiated
    }
    static final int ENGLISH_SYMBOLS = 26;
    private static void solve(final FastScanner in, final PrintWriter out) {
        int nSymbols = in.nextInt();
        String word = in.next();
        LinkedList<Integer> stack = new LinkedList();
        int[] array = new int[ENGLISH_SYMBOLS];
        for (char symbol: word.toCharArray()
             ) {
            array[getLetterIndex(symbol)]++;
        }
        int i = 0;
        int singleSymbol = -1;
        while(i < ENGLISH_SYMBOLS) {
            if(array[i] == 0) {
                i++;
                continue;
            }
            if(array[i] / 2 > 0) {
                int times = array[i] / 2;
                for (int j = 0; j < times; j++) {
                    stack.addLast(i);
                }
                if(array[i] % 2 == 1 && singleSymbol == -1) {
                    singleSymbol = i;
                }
            }
            else {
                if(singleSymbol == -1) {
                    singleSymbol = i;
                }
            }
            i++;
        }
        StringBuilder str = new StringBuilder();
        for(int j = 0; j < stack.size(); j++) {
            str.append(getIndexChar(stack.get(j)));
        }

        String reversedStr = str.reverse().toString();
        str.reverse();
        if(singleSymbol != -1) {
            str.append(getIndexChar(singleSymbol));
        }
        str.append(reversedStr);
        out.println(str.toString());
    }
    private static int getLetterIndex(char a) {
        return ((int)a - (int)'A');
    }
    private static char getIndexChar(int b) {
        return (char)(b + (int)'A');
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