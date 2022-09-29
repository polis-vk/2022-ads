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

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    String str = in.next();
    int[] alphabet = new int[26];
    for (int i = 0; i < n; ++i) {
      alphabet[str.charAt(i) - 65]++;
    }
    StringBuilder builder = new StringBuilder();
    int insertInx = 0;
    for (int i = 0; i < 26; ++i) {
      while (alphabet[i] >= 2) {
        builder.insert(insertInx++, (char)(i + 65));
        builder.insert(insertInx++, (char)(i + 65));
        alphabet[i]-=2;
      }
      insertInx = builder.length() / 2;
    }
    for (int i = 0; i < 26; ++i) {
      if (alphabet[i] == 1) {
        builder.insert(insertInx, (char)(i + 65));
        break;
      }
    }
    out.println(builder);
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
