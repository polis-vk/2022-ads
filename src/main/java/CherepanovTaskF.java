import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CherepanovTaskF {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        String command;
        boolean isWorking = true;
        while (isWorking) {
            command = in.next();
            switch (command) {
                case "push_front":
                    deque.pushFront(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.pushBack(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    if (deque.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.popFront());
                    }
                    break;
                case "pop_back":
                    if (deque.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.popBack());
                    }
                    break;
                case "front":
                    if (deque.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.front());
                    }
                    break;
                case "back":
                    if (deque.size() == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.back());
                    }
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    isWorking = false;
                    break;
            }
        }
    }

    static class Deque {
        private int size = 0;
        private Node head;
        private Node tail;
        static class Node {
            private int item;
            private Node next;
            private Node previous;
            public Node(int item, Node next, Node previous) {
                this.item = item;
                this.next = next;
                this.previous = previous;
            }
        }
        public Deque() {
            this.head = null;
            this.tail = null;
        }
        public void pushFront(int item) {
            Node node = new Node(item, null, null);
            if (this.head == null) {
                this.tail = node;
            } else {
                node.next = this.head;
                this.head.previous = node;
            }
            this.head = node;
            this.size++;
        }
        public void pushBack(int item) {
            Node node = new Node(item, null, null);
            if (this.tail == null) {
                this.head = node;
            } else {
                node.previous = this.tail;
                this.tail.next = node;
            }
            this.tail = node;
            this.size++;
        }
        public int popFront() {
            int item = this.head.item;
            this.head = this.head.next;
            if (this.head != null) {
                this.head.previous = null;
            } else {
                this.tail = null;
            }
            this.size--;
            return item;
        }
        public int popBack() {
            int item = this.tail.item;
            this.tail = this.tail.previous;
            if (this.tail != null) {
                this.tail.next = null;
            } else {
                this.head = null;
            }
            this.size--;
            return item;
        }
        public int front() {
            return this.head.item;
        }
        public int back() {
            return this.tail.item;
        }
        public int size() {
            return this.size;
        }
        public void clear() {
            /*Node temp;
            while (this.head != null) {
                temp = this.head;
                this.head = this.head.next;
                temp = null;
            }*/
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
