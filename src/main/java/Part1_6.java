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
public final class Part1_6 {
    static final String MESSAGE_OK = "ok";
    static final String MESSAGE_BYE = "bye";
    static final String MESSAGE_ERROR = "error";

    private Part1_6() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String word = in.next();
        MyListDeque deque = new MyListDeque();
        while (word.compareTo("exit") != 0) {
            switch (word) {
                case "push_front":
                    deque.push_front(in.nextInt());
                    out.println(MESSAGE_OK);
                    break;
                case "push_back":
                    deque.push_back(in.nextInt());
                    out.println(MESSAGE_OK);
                    break;

                case "pop_front":
                    if(deque.size() == 0) {
                        out.println(MESSAGE_ERROR);
                    }
                    else {
                        out.println(deque.pop_front());
                    }
                    break;
                case "pop_back":
                    if(deque.size() == 0) {
                        out.println(MESSAGE_ERROR);
                    }
                    else {
                        out.println(deque.pop_back());
                    }
                    break;
                case "front":
                    if(deque.size() == 0){
                        out.println(MESSAGE_ERROR);
                    }
                    else {
                        out.println(deque.firstNode.value);
                    }
                    break;
                case "back":
                    if(deque.size() == 0){
                        out.println(MESSAGE_ERROR);
                    }
                    else {
                        out.println(deque.lastNode.value);
                    }
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println(MESSAGE_OK);
                    break;
                default:
                    out.println(MESSAGE_ERROR);
                    break;
            }
            word = in.next();
        }
        out.println(MESSAGE_BYE);
    }
    private static class MyListDeque {

        Node firstNode;
        Node lastNode;


        private class Node {
            Node prev;
            Node next;
            int value;
            Node(int value, Node prev, Node next) {
                this.value = value;
                this.prev = prev;
                this.next = next;
            }

        }
        public void push_front(int value) {
            Node newFirstNode = new Node(value, null, firstNode);
            if(firstNode != null) {
                firstNode.prev = newFirstNode;
            }
            firstNode = newFirstNode;
            if(size() == 1) {
                lastNode = firstNode;
            }

        }
        public void push_back(int value) {
            Node newLastNode = new Node(value, lastNode, null);
            if(lastNode != null) {
                lastNode.next = newLastNode;
            }
            lastNode = newLastNode;
            if(size() == 0) {
                firstNode = lastNode;
            }

        }
        public int pop_back() {
            int result = lastNode.value;
            if(lastNode.prev != null) {
                lastNode = lastNode.prev;
            }
            else {
                clear();
                return result;
            }
            lastNode.next = null;
            return result;

        }
        public int pop_front() {
            int result = firstNode.value;
            if(firstNode.next != null) {
                firstNode = firstNode.next;
            }
            else {
                clear();
                return result;
            }
            firstNode.prev = null;
            return result;

        }


        private int size() {
            if(firstNode == null) {
                return 0;
            }
            Node node = firstNode;
            int count = 1;
            while (node.next != null) {
                node = node.next;
                count++;
            }
            return count;
        }

        public void clear() {
            firstNode = null;
            lastNode = null;

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

