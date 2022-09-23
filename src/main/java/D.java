import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public final class D {
  private D() {
    // Should not be instantiated
  }

  private static boolean isLeftBracket(Character c) {
    return "([{".indexOf(c) != -1;
  }

  private static boolean isOpposite(Character c1, Character c2) {
    return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    String seq = in.next();
    LinkedList< Character > list = new LinkedList<>();
    boolean ans = true;
    for (int i = 0; i < seq.length(); ++i) {
      if (isLeftBracket(seq.charAt(i))) {
        list.addLast(seq.charAt(i));
      } else {
        if (!list.isEmpty() && isOpposite(list.getLast(), seq.charAt(i))) {
          list.removeLast();
        } else {
          ans = false;
          break;
        }
      }
    }
    if (ans && list.isEmpty()) {
      out.println("yes");
    } else {
      out.println("no");
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
