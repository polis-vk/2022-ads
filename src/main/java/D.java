import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Map;
/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class D {
    private D() {
        // Should not be instantiated
    }

    static class MyStack {

        // node of queue
        private class Node {
            char data;
            Node next;

            // c-tor
            Node(char data) {
                this.data = data;
            }
        }

        // number of el-s in stack
        private int _size;

        // head of stack
        private Node _head;

        //ctor
        public MyStack() {
            _head = null;

            _size = 0;
        }

        // push elem into queue
        public void push(char element) {
            Node newNode = new Node(element);

            if (_size == 0) {
                _head = newNode;
            } else {
                newNode.next = _head;
                _head = newNode;
            }
            _size++;
        }

        // delete head node from queue
        public char pop() {
            if (_size == 0) {
                throw new RuntimeException();
            } else {
                char value = _head.data;
                _head = _head.next;
                _size--;
                return value;
            }
        }

        // get elem from head of queue
        public char back() {
            if (_size == 0) {
                throw new RuntimeException();
            } else {
                return _head.data;
            }
        }

        // get number of elements of queue
        public int size() {
            return _size;
        }

        // delete elements from queue
        public void clear() {
            _head = null;
            _size = 0;
        }
    }

    static boolean isTrueSequence(final FastScanner in){
        String sequence = new String(in.next());
        if (sequence.length() < 2) {
            return false;
        }
        Map<Character, Character> bracketMap = Map.of('(', ')', '[', ']', '{', '}');

        MyStack bracketStack = new MyStack();

        for(char sym : sequence.toCharArray()){
            if(sym == '(' || sym == '{' || sym == '[') {
                bracketStack.push(sym);
            }
            else if(bracketStack.size() != 0 && sym == bracketMap.get(bracketStack.back())){
                bracketStack.pop();
            }
            else if (sym == ')' || sym == '}' || sym == ']'){
                return false;
            }

        }
        return bracketStack.size() == 0;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(isTrueSequence(in) == true ? "yes" : "no");
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
