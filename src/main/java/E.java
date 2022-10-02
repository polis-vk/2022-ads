import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class E {
  private E() {
    // Should not be instantiated
  }

  private static final int ALPHABET_SIZE = 26;

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    String str = in.next();
    int[] alphabet = new int[ALPHABET_SIZE];
    for (int i = 0; i < n; ++i) {
      alphabet[str.charAt(i) - 65]++;
    }
    StringBuilder builder = new StringBuilder();
    int indexOfCenter = -1;
    for (int i = 0; i < ALPHABET_SIZE; ++i) {
      while(alphabet[i] >= 2) {
        builder.append((char)(i + 65));
        alphabet[i] -= 2;
      }
      if (indexOfCenter == -1 && alphabet[i] == 1) {
        indexOfCenter = i;
      }
    }
    String res = builder.toString();
    res = (indexOfCenter == -1) ? res + builder.reverse() : res + (char)(indexOfCenter + 65) + builder.reverse();
    out.println(res);
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
