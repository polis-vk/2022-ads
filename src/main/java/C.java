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
public final class C {
    private C() {
        // Should not be instantiated
    }

    static class MyStack {

        // node of queue
        private class Node {
            int data;
            Node next;

            // c-tor
            Node(int data) {
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
        public void push(int element) {
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
        public int pop() {
            if (_size == 0) {
                throw new RuntimeException();
            } else {
                int value = _head.data;
                _head = _head.next;
                _size--;
                return value;
            }
        }

        // get elem from head of queue
        public int back() {
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        //create queue
        MyStack queue = new MyStack();

        //endless cycle
        while(true){
            String input = in.next(); //read 1st command
            switch (input){
                case "push": // example: push 1
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop": // example: pop
                    try {
                        out.println((queue.pop()));
                    }
                    catch (RuntimeException exc){
                        out.println("error");
                    }
                    break;
                case "back": // example: back
                    try {
                        out.println(queue.back());
                    }
                    catch (RuntimeException exc){
                        out.println("error");
                    }
                    break;
                case "size": // example: size
                    out.println(queue.size());
                    break;
                case "clear": // example: clear
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit": // example: exit
                    out.println("bye");
                    return;
            }
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
