import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public final class C {
  private C() {
    // Should not be instantiated
  }

  private static final String OK_MSG = "ok";
  private static final String ERROR_MSG = "error";

  private static class Stack{
    private static class Node {
      private int val;
      private Node next;

      Node(int val, Node next) {
        this.val = val;
        this.next = next;
      }
    }

    Node head;
    int size;

    Stack() {
      head = null;
      size = 0;
    }

    void push(int n) {
      if (head == null) {
        head = new Node(n, null);
      } else {
        head = new Node(n, head);
      }
      size++;
    }

    void pop() {
      if (head != null) {
        head = head.next;
        size--;
      }
    }

    boolean isEmpty() {
      return head == null;
    }

    int getBack() {
      if (head != null) {
        return head.val;
      }
      throw new NoSuchElementException();
    }

    int getSize() {
      return size;
    }

    void clear() {
      head = null;
      size = 0;
    }

  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    String command;
    Stack stack = new Stack();
    do {
      command = in.next();
      switch (command) {
        case "push" -> {
          int n = in.nextInt();
          stack.push(n);
          out.println(OK_MSG);
        }
        case "pop" -> {
          if (!stack.isEmpty()) {
            out.println(stack.getBack());
            stack.pop();
          } else {
            out.println(ERROR_MSG);
          }
        }
        case "back" -> {
          if (!stack.isEmpty()) {
            out.println(stack.getBack());
          } else {
            out.println(ERROR_MSG);
          }
        }
        case "size" -> {
          out.println(stack.getSize());
        }
        case "clear" -> {
          stack.clear();
          out.println(OK_MSG);
        }
      }
    } while (!command.equals("exit"));
    out.println("bye");
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
