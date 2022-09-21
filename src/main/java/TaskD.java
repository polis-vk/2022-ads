import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskD {
    private static final String YES_MSG = "yes";
    private static final String NO_MSG = "no";

    private TaskD() {
        // Should not be instantiated
    }

    private static boolean isRightBracket(char element){
        return ")]}".contains(Character.toString(element));
    }

    private static boolean isLeftBracket(char element){
        return "[{(".contains(Character.toString(element));
    }

    private static boolean combinationIsCorrect(char elementOfStack, char currentElement){
        boolean areTwoParentheses = elementOfStack == '(' && currentElement == ')';
        boolean areTwoCurlyBrackets = elementOfStack == '{' && currentElement == '}';
        boolean areTwoSquareBrackets = elementOfStack == '[' && currentElement == ']';
        return areTwoParentheses || areTwoCurlyBrackets || areTwoSquareBrackets;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack<Character> myStack = new Stack<>();
        char[] line = in.next().toCharArray();
        boolean responseHasBeenReceived = false;
        for (char element: line){
            if (isRightBracket(element) && myStack.isEmpty()){
                responseHasBeenReceived = true;
                out.println(NO_MSG);
                break;
            }
            if(isLeftBracket(element)){
                myStack.push(element);
            }
            else {
                if (!myStack.isEmpty() && combinationIsCorrect(myStack.back(), element)){
                    myStack.pop();
                }
                else {
                    responseHasBeenReceived = true;
                    out.println(NO_MSG);
                    break;
                }
            }
        }
        if(!responseHasBeenReceived){
            if (myStack.isEmpty()){
                out.println(YES_MSG);
            }
            else {
                myStack.clear();
                out.println(NO_MSG);
            }
        }
    }

    private static class Stack<E>{

        private static class Node<E> {
            public Node<E> next;
            public Node<E> prev;
            public E value;

            public Node(E value) {
                next = null;
                prev = null;
                this.value = value;
            }
        }

        private Node<E> head;
        private Node<E> tail;
        private int size;

        public Stack(){
            head = tail = null;
            size = 0;
        }

        public void push(E value) {
            if (head == null){
                head = tail = new Node<>(value);
            }
            else {
                tail.next = new Node<>(value);
                tail.next.prev = tail;
                tail = tail.next;
            }
            size++;
        }

        public void pop() {
            if (head == tail) {
                clear();
            }
            else {
                tail = tail.prev;
                tail.next = null;
                size--;
            }
        }

        public E back() {
            return tail.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
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
