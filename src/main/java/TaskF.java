import java.io.*;
import java.util.StringTokenizer;

public class TaskF {

    private static void solve(final FastScanner in, final PrintWriter out){
        Deque deque = new Deque();
        String command = "";
        while (!command.equals("exit")) {
            command = in.next();
            switch (command) {
                case "push_front":
                    deque.push_front(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.push_back(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    if (deque.size() == 0) {
                        out.println("error");
                        break;
                    }
                    out.println(deque.pop_front());
                    break;
                case "pop_back":
                    if (deque.size() == 0){
                        out.println("error");
                        break;
                    }
                    out.println(deque.pop_back());
                    break;
                case "front":
                    if (deque.size() == 0){
                        out.println("error");
                        break;
                    }
                    out.println(deque.front());
                    break;
                case "back":
                    if (deque.size() == 0){
                        out.println("error");
                        break;
                    }
                    out.println(deque.back());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "exit":
                    command = "exit";
                    break;
            }
        }
        out.println("bye");
    }

    private static class Deque {
        private Node head;
        private Node tail;
        private int size;

        private static class Node {
            int data;
            Node pNext;
            Node pPrev;

            public Node(int value) {
                data = value;
                pNext = null;
                pPrev = null;
            }
        }

        public Deque() {
            head = null;
            tail = null;
            size = 0;
        }

        public void push_front(int value) { // в начало
            if(head == null) {
                head = new Node(value);
                tail = head;
            } else {
                head.pPrev = new Node(value);
                head.pPrev.pNext = head;
                head = head.pPrev;
            }
            size++;
        }

        public void push_back(int value) { // в конец
            if(head == null) {
                head = new Node(value);
                tail = head;
            } else {
                tail.pNext = new Node(value);
                tail.pNext.pPrev = tail;
                tail = tail.pNext;
            }
            size++;
        }

        public int pop_front() {
            int value = head.data;
            head = head.pNext;
            if(head == null) {
                tail = null;
            } else {
                head.pPrev = null;
            }
            size--;
            return value;
        }

        public int pop_back() {
            int value = tail.data;
            tail = tail.pPrev;
            if (tail == null) {
                head = null;
            } else {
                tail.pNext = null;
            }
            size--;
            return value;
        }

        public int front() {
            return head.data;
        }

        public int back() {
            return tail.data;
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
