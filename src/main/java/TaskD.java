import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskD {
    private TaskD() { }

    private static void solve(final FastScanner in, final PrintWriter out) throws Exception {
        String str = in.reader.readLine();
        // ([)]
        // ()[]
        char symbol = ' ';
        boolean flag = true;
        Stack stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            symbol = str.charAt(i);
            if (symbol == '(' || symbol == '[' || symbol == '{') {
                stack.push(symbol);
            } else {
                if(stack.size() == 0) {
                    flag = false;
                    break;
                }
                char stackSymbol = stack.pop();
                if(stackSymbol == '(' && symbol != ')'){
                    flag = false;
                    break;
                }
                if(stackSymbol == '[' && symbol != ']') {
                    flag = false;
                    break;
                }
                if(stackSymbol == '{' && symbol != '}') {
                    flag = false;
                    break;
                }
            }
        }

        if(flag && stack.size() == 0) {
            out.println("yes");
        } else out.println("no");
    }

    private static class Stack {
        private Node head;
        private Node tail;
        private int size;

        private static class Node {
            char data;
            Node pNext;
            Node pPrev;
            public Node(char value) {
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

        public void push(char value) {
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

        public char back() {
            return tail.data;
        }

        public char pop() {
            char value = tail.data;
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

    public static void main(final String[] arg) throws Exception {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}