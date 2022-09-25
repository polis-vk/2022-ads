import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskB {
    private TaskB() {
        // Should not be instantiated

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me

        MyQueue queue = new MyQueue();
        String command = in.next();;;

        while (!command.equals("exit")) {
            switch (command) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    Integer upper = queue.pop();
                    out.println((upper == null) ? "error" : upper);
                    break;
                case "front":
                    Integer upper1 = queue.front();
                    out.println((upper1 == null) ? "error" : upper1);
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
            }
            command = in.next();
        }
        out.println("bye");
    }

    private static class MyQueue {
        private static class Node {
            private int data;
            private Node prev;

            public Node(int data) {
                this.data = data;
            }

            public Node getPrev() {
                return prev;
            }

            public void setPrev(Node prev) {
                this.prev = prev;
            }

            public int getData() {
                return data;
            }
        }

        private Node tail;
        private Node head;
        private int size;

        public MyQueue() {
            tail = null;
            head = null;
            size = 0;
        }


        public void push(int item) {
            if (tail == null) {
                tail = new Node(item);
                head = tail;
            } else {
                head.setPrev(new Node(item));
                head = head.getPrev();
            }
            size++;

        }

        public Integer pop() {
            Integer pop;
            if (tail == null) {
                pop = null;
            } else {
                size--;
                pop = tail.getData();
                tail = tail.getPrev();
            }
            return pop;
        }

        public int size() {
            return size;
        }

        public Integer front() {
            return (tail == null) ? null : tail.getData();
        }

        public void clear() {
            tail = null;
            head = null;
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