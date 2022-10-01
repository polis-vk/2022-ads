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
public final class Part1_2 {
    static final String MESSAGE_OK = "ok";
    static final String MESSAGE_BYE = "bye";
    static final String MESSAGE_ERROR = "error";

    private Part1_2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String word = in.next();
        MyListQueue queue = new MyListQueue();
        while (word.compareTo("exit") != 0) {
            switch (word) {
                case "push": 
                    queue.push(in.nextInt());
                    out.println(MESSAGE_OK);
                    break;
                case "pop":
                    if(queue.size() == 0) {
                        out.println(MESSAGE_ERROR);
                    }
                    else {
                        out.println(queue.pop());
                    }
                    break;
                case "front":
                    if(queue.size() == 0){
                        out.println(MESSAGE_ERROR);
                    }
                    else {
                        out.println(queue.lastNode.value);
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
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
    private static class MyListQueue {
        
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
        public void push(int value) {
            Node newFirstNode = new Node(value, null, firstNode);
            if(firstNode != null) {
                firstNode.prev = newFirstNode;
            }
            firstNode = newFirstNode;
            if(size() == 1) {
                lastNode = firstNode;
            }

        }
        public int pop() {
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
