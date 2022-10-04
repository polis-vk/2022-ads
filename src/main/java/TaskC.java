import java.io.*;
import java.util.StringTokenizer;

public class TaskC {

    private static class StackIml{
        Node head;
        Node tail;

        private int size;

        StackIml() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        private static class Node {
            int value;

           Node prev;

            Node(int value) {
                this.value = value;
                this.prev = null;
            }

        }


        public void push(int value) {
            size++;
           Node node = new Node(value);
            if (head == null) {
                head = node;
                tail = head;
                return;
            }
            node.prev = tail;
            tail = node;
        }

        public int pop() {
            int value = tail.value;
            tail = tail.prev;
            if (head == null) {
                tail = null;
            }
            size--;
            return value;
        }

        public int back() {
            return tail.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

    }




    private static void solve(final FastScanner in, final PrintWriter out) {
        StackIml stackIml = new StackIml();
        while (true) {
            String input = in.next();
            switch (input) {
                case "push":
                    stackIml.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println((stackIml.pop()));
                    } catch (Exception e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(stackIml.back());
                    } catch (Exception e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stackIml.size());
                    break;
                case "clear":
                    stackIml.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
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
            final TaskC.FastScanner in = new TaskC.FastScanner(System.in);
            try (PrintWriter out = new PrintWriter(System.out)) {
                solve(in, out);
            }catch (Exception e) {
                e.printStackTrace();
            }
    }
}
