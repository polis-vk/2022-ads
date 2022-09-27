import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CherepanovTaskB {

    private CherepanovTaskB() {
        // Should not be instantiated
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        String command;
        boolean isWorking = true;
        while (isWorking) {
            command = in.next();
            switch (command) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (queue.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(queue.pop());
                    }
                    break;
                case "front":
                    if (queue.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(queue.front());
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    isWorking = false;
                    break;
            }
        }
    }

    static class Queue {
        private int size = 0;
        private Node head;
        private Node tail;
        static class Node {
            private int item;
            private Node nextNode;
            public Node(Node nextNode, int item) {
                this.nextNode = nextNode;
                this.item = item;
            }
        }
        public Queue() {
            this.head = null;
            this.tail = null;
        }
        public void push(int item) {
            Node node = new Node(null, item);
            this.size++;
            if (this.head == null) {
                this.head = node;
                this.tail = node;
                return;
            }
            this.tail.nextNode = node;
            this.tail = node;
        }
        public int pop() {
            int item = head.item;
            this.head = head.nextNode;
            this.size--;
            return item;
        }
        public int front() {
            return head.item;
        }
        public int size() {
            return size;
        }
        public void clear() {
            this.head = null;
            this.tail = null;
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