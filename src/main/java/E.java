import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class E {
    private E() {
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

    static final int NUMCHAR_OFFSET = 48;

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner input = new Scanner(System.in);
        String expression = new String(input.nextLine());
        MyStack calcStack = new MyStack();

        for(char sym : expression.toCharArray()){
            if(sym >= '0' && sym <= '9'){
                calcStack.push(sym - NUMCHAR_OFFSET);
            }
            else{
                switch (sym){
                    case ' ':
                        break;
                    case '+':
                        calcStack.push(calcStack.pop() + calcStack.pop());
                        break;
                    case '-':
                        calcStack.push(-(calcStack.pop() - calcStack.pop()));
                        break;
                    case '*':
                        calcStack.push(calcStack.pop() * calcStack.pop());
                        break;

                }
            }
        }
        out.println(calcStack.pop());
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
