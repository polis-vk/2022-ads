import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskC {

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String command = "";
        while (!command.equals("exit")) {
            command = in.next();
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.size() == 0) {
                        out.println("error");
                        break;
                    }
                    out.println(stack.pop());
                    break;
                case "back":
                    if (stack.size() == 0){
                        out.println("error");
                        break;
                    }
                    out.println(stack.back());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "exit":
                    command = "exit";
                    break;
            }
        }
        out.println("bye");
    }

    private static class Stack {
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

        public Stack() {
            head = null;
            tail = null;
            size = 0;
        }

        public void push(int value) {
            if (head == null) {
                head = new Node(value);
                tail = head;
            } else {
                tail.pNext = new Node(value);
                tail.pNext.pPrev = tail;
                tail = tail.pNext;
            }
            size++;
        }

        public int back() {
            return tail.data;
        }

        public int pop() {
            int value = tail.data;
            tail = tail.pPrev;
            if (tail == null) {
                head = null;
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
