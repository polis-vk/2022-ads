import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public final class E {
  private E() {
    // Should not be instantiated
  }

  private static int doOperation(int left, int right, char operation) {
    int res = 0;
    switch (operation) {
      case '+' -> {
        res = left + right;
      }
      case '-' -> {
        res = left - right;
      }
      case '*' -> {
        res = left * right;
      }
    }
    return res;
  }
  private static void solve(final FastScanner in, final PrintWriter out) {
    LinkedList< Integer > digits = new LinkedList<>();
    Scanner sc = new Scanner(System.in);
    String expr = sc.nextLine();
    String [] parts = expr.split(" ");
    for (String s : parts){
      if (Character.isDigit(s.charAt(0))) {
        digits.addLast(Integer.parseInt(Character.toString(s.charAt(0))));
      } else {
        int right = digits.removeLast();
        int left = digits.removeLast();
        digits.addLast(doOperation(left, right, s.charAt(0)));
      }
    }

    out.println(digits.getLast());
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
