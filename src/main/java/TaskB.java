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
public final class TaskB {
    private TaskB() { }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        String command = " ";
        while (!command.equals("exit")) {
            command = in.next();
            switch (command) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (queue.size() == 0) {
                        out.println("error");
                        break;
                    }
                    out.println(queue.pop());
                    break;
                case "front":
                    if (queue.size() == 0){
                        out.println("error");
                        break;
                    }
                    out.println(queue.front());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "exit":
                    command = "exit";
                    break;
            }
        }
        out.println("bye");
    }

    static class Queue {
        private Node head;
        private Node tail;
        private int size;

        private static class Node {
            int data;
            Node pNext;
            public Node(int value) {
                data = value;
                pNext = null;
            }
        }

        public Queue() {
            head = null;
            size = 0;
        }

        public void push(int value) {
            if (head == null) {
                head = new Node(value);
                tail = head;
            } else {
                tail.pNext = new Node(value);
                tail = tail.pNext;
            }
            size++;
        }

        public int front() {
            return head.data;
        }

        public int pop() {
            int value = head.data;
            head = head.pNext;
            if (head == null) {
                tail = null;
            }
            size--;
            return value;
        }

        public int size(){
            return size;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
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