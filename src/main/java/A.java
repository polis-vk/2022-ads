import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class A {
  private A() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    int n = in.nextInt();
    int[] arr = new int[n];
    arr[0] = in.nextInt();
    int min = arr[0];
    for (int i = 1; i < n; ++i) {
      arr[i] = in.nextInt();
      min = Math.min(min, arr[i]);
    }
    int[] count = new int[108];
    for (int i = 0; i < n; ++i) {
      count[arr[i] - min]++;
    }
    for (int i = 0; i < 108; ++i) {
      while(count[i]-- > 0) {
        out.print((min + i) + " ");
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
