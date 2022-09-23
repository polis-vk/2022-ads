
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class part1_55 {
    private part1_55() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        Stack<Integer> stack = new Stack<>();

        while (true) {
            String cmd = in.next();
            switch (cmd) {
                case "push" :
                    int n = in.nextInt();
                    stack.push(n);
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(stack.pop());
                    } catch (EmptyStackException e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(stack.back());
                    } catch (EmptyStackException e) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack = new Stack<>();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
                default:
                    throw new IllegalStateException();
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

class Stack<T> {
    int pointer; // указывает на последний добавленный элемент
    T[] arr;
    public Stack(){
        this(16);
    }

    public Stack(int startSize) {
        pointer = -1;
        arr = (T[]) new Object[startSize];
    }

    public void push(T val){
        if (pointer >= arr.length - 1) resize(arr.length*2);
        arr[++pointer] = val;
    }

    public T pop(){
        if (pointer == -1) {
            throw new EmptyStackException();
        }

        return arr[pointer--];
    }

    public T back(){
        if (pointer == -1) {
            throw new EmptyStackException();
        }

        return arr[pointer];
    }

    public int size(){
        return pointer + 1;
    }

    public void resize(int newSize) {
        arr = java.util.Arrays.copyOf(arr, arr.length*2);
    }


}