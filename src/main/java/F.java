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
public final class F {
    private F() {
        // Should not be instantiated
    }

    static class MyDeque {

        // node of deque
        private class Node {
            int data;
            Node next;
            Node prev;

            // c-tor
            Node(int data) {
                this.data = data;
            }
        }


        private int _size;

        private Node _front;
        private Node _back;

        //ctor
        public MyDeque() {
            _front = null;
            _back = null;

            _size = 0;
        }

        public void push_front(int element) {
            Node newNode = new Node(element);

            if (_size == 0) {
                _front = newNode;
                _back = newNode;
            } else {
                newNode.prev = _front;
                _front.next = newNode;
                _front = newNode;
            }
            _size++;
        }

        public void push_back(int element) {
            Node newNode = new Node(element);

            if (_size == 0) {
                _front = newNode;
                _back = newNode;
            } else {
                newNode.next = _back;
                _back.prev = newNode;
                _back = newNode;
            }
            _size++;
        }

        public int pop_back() {
            if (_size == 0) {
                throw new RuntimeException();
            } else {
                int value = _back.data;
                _back = _back.next;
                _size--;
                return value;
            }
        }

        public int pop_front() {
            if (_size == 0) {
                throw new RuntimeException();
            } else {
                int value = _front.data;
                _front = _front.prev;
                _size--;
                return value;
            }
        }

        public int back() {
            if (_size == 0) {
                throw new RuntimeException();
            } else {
                return _back.data;
            }
        }

        public int front() {
            if (_size == 0) {
                throw new RuntimeException();
            } else {
                return _front.data;
            }
        }

        public int size() {
            return _size;
        }

        public void clear() {
            _back = null;
            _front = null;
            _size = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        MyDeque deque = new MyDeque();


        while(true){
            String input = in.next();
            switch (input){
                case "push_front":
                    deque.push_front(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.push_back(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    try {
                        out.println((deque.pop_front()));
                    }
                    catch (RuntimeException exc){
                        out.println("error");
                    }
                    break;
                case "pop_back":
                    try {
                        out.println((deque.pop_back()));
                    }
                    catch (RuntimeException exc){
                        out.println("error");
                    }
                    break;
                case "front":
                    try {
                        out.println(deque.front());
                    }
                    catch (RuntimeException exc){
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(deque.back());
                    }
                    catch (RuntimeException exc){
                        out.println("error");
                    }
                    break;
                case "size": // example: size
                    out.println(deque.size());
                    break;
                case "clear": // example: clear
                    deque.clear();
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
