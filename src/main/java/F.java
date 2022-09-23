import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public final class F {
  private F() {
    // Should not be instantiated
  }

  private static final String OK_MSG = "ok";
  private static final String ERROR_MSG = "error";

  private static class Dequeue {
    private static class Node {
      private int val;
      private Node next;
      private Node prev;

      Node(int val, Node next, Node prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
      }
    }

    Node head;
    Node tail;
    int size;

    Dequeue() {
      head = null;
      tail = null;
      size = 0;
    }

    void pushFront(int n) {
      Node elem = head;
      head = new Node(n, null, null);
      if (size == 0) {
        tail = head;
      } else {
        head.prev = elem;
        elem.next = head;
      }
      size++;
    }

    void pushBack(int n) {
      Node elem = tail;
      tail = new Node(n, null, null);
      if (size == 0) {
        head = tail;
      } else {
        tail.next = elem;
        elem.prev = tail;
      }
      size++;
    }

    void popFront() {
      if (!isEmpty()) {
        head = head.prev;
        if (head != null) {
          head.next = null;
        }
        size--;
      }
    }

    void popBack() {
      if (!isEmpty()) {
        tail = tail.next;
        if (tail != null) {
          tail.prev = null;
        }
        size--;
      }
    }

    boolean isEmpty() {
      return size == 0;
    }

    int getFront() {
      if (!isEmpty()) {
        return head.val;
      }
      throw new NoSuchElementException();
    }

    int getBack() {
      if (!isEmpty()) {
        return tail.val;
      }
      throw new NoSuchElementException();
    }

    int getSize() {
      return size;
    }

    void clear() {
      head = null;
      tail = null;
      size = 0;
    }

  }

  private static void solve(final FastScanner in, final PrintWriter out) {
    String command;
    Dequeue dequeue = new Dequeue();
    do {
      command = in.next();
      switch (command) {
        case "push_front" -> {
          int n = in.nextInt();
          dequeue.pushFront(n);
          out.println(OK_MSG);
        }
        case "push_back" -> {
          int n = in.nextInt();
          dequeue.pushBack(n);
          out.println(OK_MSG);
        }
        case "pop_front" -> {
          if (!dequeue.isEmpty()) {
            out.println(dequeue.getFront());
            dequeue.popFront();
          } else {
            out.println(ERROR_MSG);
          }
        }
        case "pop_back" -> {
          if (!dequeue.isEmpty()) {
            out.println(dequeue.getBack());
            dequeue.popBack();
          } else {
            out.println(ERROR_MSG);
          }
        }
        case "front" -> {
          if (!dequeue.isEmpty()) {
            out.println(dequeue.getFront());
          } else {
            out.println(ERROR_MSG);
          }
        }
        case "back" -> {
          if (!dequeue.isEmpty()) {
            out.println(dequeue.getBack());
          } else {
            out.println(ERROR_MSG);
          }
        }
        case "size" -> {
          out.println(dequeue.getSize());
        }
        case "clear" -> {
          dequeue.clear();
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
