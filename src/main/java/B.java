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
public final class B {

    // my realisation of queue
    static class MyQueue {

        // node of queue
        private class Node {
            int data;
            Node next;

            // c-tor
            Node(int data) {
                this.data = data;
            }
        }

        // number of el-s in queue
        private int _size;

        // head & tail of queue
        private Node _head;
        private Node _tail;

        //ctor
        public MyQueue() {
            _head = null;
            _tail = null;

            _size = 0;
        }

        // push elem into queue
        public void push(int element) {
            Node newNode = new Node(element);

            if (_size == 0) {
                _head = newNode;
                _tail = newNode;
            } else {
                _tail.next = newNode;
                _tail = _tail.next;
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
        public int front() {
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
            _tail = null;
            _size = 0;
        }
    }

    private B() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        //create queue
        MyQueue queue = new MyQueue();

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
                case "front": // example: front
                    try {
                        out.println(queue.front());
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
