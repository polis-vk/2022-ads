import java.io.*;
import java.util.StringTokenizer;

public class TaskF {

    private static class Deque{
        Node head;
        Node tail;

        private int size;

        Deque() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        private static class Node {
            int value;
            Node next;
            Node prev;

            Node(int value) {
                this.value = value;
                this.prev = null;
                this.next = null;
            }

        }


        public void pushFront(int value) {
            Node node = new Node(value);

            if (size == 0) {
                head = node;
                tail = head;
                size++;
                return;
            }

                node.next = head;
                head.prev = node;
                head = node;
                size++;
        }

        public void pushBack(int value) {
            Node node = new Node(value);

            if (size == 0) {
                tail = node;
                head  = tail;
                size++;
                return;
            }

            tail.next = node;
            node.prev = tail;
            tail = node;
            size++;
        }

        public int  popFront() {
            int value = head.value;

            if (size == 1) {
                head = null;
                tail = null;
                size--;
                return value;
            }

            head = head.next;
            head.prev = null;
            size--;
            return value;
        }

        public int  popBack() {
            int value = tail.value;

            if (size == 1) {
                head = null;
                tail = null;
                size--;
                return value;
            }
            tail = tail.prev;
            tail.next = null;
            size--;
            return value;
        }

        public void clear () {
            head = null;
            tail = null;
            size = 0;
        }

        public int front() {
            return head.value;
        }

        public int back(){
            return tail.value;
        }

        public int size(){
            return size;
        }




    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        String command = in.next();
        while (!command.equals("exit")) {
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
                    if (deque.head == null) {
                        out.println("error");
                    } else {
                        out.println(deque.popFront());
                    }
                    break;
                case "pop_back":
                    if (deque.tail == null) {
                        out.println("error");
                    } else {
                        out.println(deque.popBack());
                    }
                    break;
                case "front":
                    if (deque.head == null) {
                        out.println("error");
                    } else {
                        out.println(deque.front());
                    }
                    break;
                case "back":
                    if (deque.tail == null) {
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
            }
            command = in.next();
        }
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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
