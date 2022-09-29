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
public final class B {
  private B() {
    // Should not be instantiated
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
   int x = in.nextInt();
   long a = 1;
   long b = 1;
   for (int i = 1; i < x; ++i) {
     long tmp = (int)(Math.pow(a, 2) - Math.pow(b, 3));
     if (tmp == 0) {
       a++;
       b++;
     } else if (tmp < 0) {
       a++;
     } else {
       b++;
     }
   }
   out.println(Math.min(a * a, b * b * b));
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
