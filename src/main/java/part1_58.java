
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
public final class part1_58 {
    private part1_58() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        Queue<Integer> q = new Queue<Integer>();
        while (true){
            String cmd = in.next();
            if (cmd.equals("push")){
                q.push(in.nextInt());
                out.println("ok");
            } else if (cmd.equals("pop")) {
                Integer res = q.pop();
                if (res == null) {
                    out.println("error");
                } else {
                    out.println(res);
                }
            } else if (cmd.equals("front")) {
                Integer res = q.peek();
                if (res == null) {
                    out.println("error");
                } else {
                    out.println(res);
                }
            } else if (cmd.equals("size")) {
                out.println(q.length());
            } else if (cmd.equals("clear")) {
                q = new Queue<>();
                out.println("ok");
            } else if (cmd.equals("exit")){
                out.println("bye");
                return;
            } else {
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

class Queue<T> {

    private Node<T> first; // первый по очереди, то есть добавленный самым первым
    private Node<T> last; // последний по очереди
    private int l;

    public Queue(){
        first = null;
        last = null;
        l = 0;
    }

    public void push(T val){
        l++;
        if (first == null){
            first = new Node<>();
            last = first;
            first.value = val;
            return;
        }
        Node<T> newNode = new Node<>();
        newNode.value = val;
        newNode.prev = last;
        last.next = newNode;
        last = newNode;
    }

    public T peek(){
        if (first == null) return null;
        return first.value;
    }

    public T pop(){
        if (first == null) return null;
        Node<T> toReturn = first;
        first = first.next;
        l--;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        return toReturn.value;
    }

    public int length(){
        return l;
    }


    class Node<T>{
        Node<T> prev; // предыдущий по очереди - тот, кто стоит перед ним
        Node<T> next; // следующий по очереди - тот, кто стоит за ним
        T value;
    }
}
