import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CherepanovTaskC {

    private CherepanovTaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String command;
        boolean isWorking = true;
        while (isWorking) {
            command = in.next();
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(stack.pop());
                    }
                    break;
                case "back":
                    if (stack.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(stack.back());
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    isWorking = false;
                    break;
            }
        }
    }

    static class Stack {
        private int size = 0;
        private Node head;
        public Stack() {
            this.head = null;
        }
        static class Node {
            int item;
            Node previousNode;
            public Node(int item, Node previousNode) {
                this.item = item;
                this.previousNode = previousNode;
            }
        }
        public void push(int item) {
            Node node = new Node(item, null);
            this.size++;
            if (head == null) {
                this.head = node;
                return;
            }
            node.previousNode = this.head;
            this.head = node;
        }
        public int pop() {
            int item = this.head.item;
            head = this.head.previousNode;
            this.size--;
            return item;
        }
        public int back() {
            return this.head.item;
        }
        public int size() {
            return this.size;
        }
        public void clear() {
            this.head = null;
            this.size = 0;
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
