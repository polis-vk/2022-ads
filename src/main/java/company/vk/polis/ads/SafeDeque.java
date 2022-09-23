package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class SafeDeque {
    private SafeDeque() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        boolean flag = true;
        do {
            switch (in.next()) {
                case "push_front":
                    deque.Push_front(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.Push_back(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    if (!(deque.IsEmpty())) {
                        out.println(deque.Pop_front());
                        break;
                    }
                    out.println("error");
                    break;
                case "pop_back":
                    if (!(deque.IsEmpty())) {
                        out.println(deque.Pop_back());
                        break;
                    }
                    out.println("error");
                    break;
                case "front":
                    if (!(deque.IsEmpty())) {
                        out.println(deque.Front());
                        break;
                    }
                    out.println("error");
                    break;
                case "back":
                    if (!(deque.IsEmpty())) {
                        out.println(deque.Back());
                        break;
                    }
                    out.println("error");
                    break;
                case "size":
                    out.println(deque.Size());
                    break;
                case "clear":
                    deque.Clear();
                    out.println("ok");
                    break;
                default:
                    out.println("bye");
                    flag = false;
                    break;
            }
        } while (flag);
    }

    private static class Deque {
        private static Node head;
        private static Node tail;
        private static int size;

        private static class Node {
            public Node next;
            public Node prev;
            public int value;

            public Node(int value) {
                this.value = value;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public void setPrev(Node prev) {
                this.prev = prev;
            }
        }

        public void Push_front(int number) {
            Node node = new Node(number);
            if (head == null) {
                head = node;
                tail = head;
            } else {
                node.setNext(head);
                head.setPrev(node);
                head = node;
            }
            size++;
        }

        public void Push_back(int number) {
            Node node = new Node(number);
            if (tail == null) {
                tail = node;
                head = tail;
            } else {
                node.setPrev(tail);
                tail.setNext(node);
                tail = node;
            }
            size++;
        }

        public int Pop_front() {
            if (head.next == null) {
                int value = head.value;
                head = null;
                tail = null;
                size--;
                return value;
            } else {
                size--;
                int value = head.value;
                head = head.next;
                head.prev = null;
                return value;
            }
        }

        public int Pop_back() {
            if (tail.prev == null) {
                int value = tail.value;
                head = null;
                tail = null;
                size--;
                return value;
            } else {
                size--;
                int value = tail.value;
                tail = tail.prev;
                tail.next = null;
                return value;
            }
        }

        public void Clear() {
            head = null;
            tail = null;
            size = 0;
        }

        public int Back() { // done
            return tail.value;
        }

        public int Front() { // done
            return head.value;
        }

        public int Size() { // done
            return size;
        }

        public boolean IsEmpty() { // done
            return size <= 0;
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
