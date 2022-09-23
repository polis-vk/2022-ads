package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class SafeQueue {
    private SafeQueue() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        boolean flag = true;
        do {
            switch (in.next()) {
                case "push" -> {
                    out.println("ok");
                    queue.Push(in.nextInt());
                }
                case "pop" -> {
                    if (!(queue.IsEmpty())) {
                        out.println(queue.Pop());
                        break;
                    }
                    out.println("error");
                }
                case "front" -> {
                    if (!(queue.IsEmpty())) {
                        out.println(queue.Front());
                        break;
                    }
                    out.println("error");
                }
                case "size" -> out.println(queue.Size());
                case "clear" -> {
                    out.println("ok");
                    queue.Clear();
                }
                default -> {
                    out.println("bye");
                    flag = false;
                }
            }
        } while (flag);
    }

    private static class Queue {
        private static Node head;
        private static Node tail;
        private static int size;

        private static class Node {
            public Node next;
            public int value;

            public Node(int value) {
                this.value = value;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }

        public void Push(int number) {
            Node node = new Node(number);
            if (tail == null) {
                head = node;
            } else {
                tail.setNext(node);
            }
            tail = node;
            size++;
        }

        public int Pop() {
            if (head.next == null) {
                int value = head.value;
                head = null;
                tail = null;
                size--;
                return value;
            }
            size--;
            int value = head.value;
            head = head.next;
            return value;
        }

        public void Clear() {
            head = null;
            tail = null;
            size = 0;
        }

        public int Front() {
            return head.value;
        }

        public int Size() {
            return size;
        }

        public boolean IsEmpty() {
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