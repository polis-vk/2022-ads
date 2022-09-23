
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
public final class part1_61 {
    private part1_61() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        OwnDeque<Integer> q = new OwnDeque<Integer>();
        while (true){
            String cmd = in.next();
            if (cmd.equals("push_back")){
                q.push_back(in.nextInt());
                out.println("ok");
            } else if (cmd.equals("push_front")){
                q.push_front(in.nextInt());
                out.println("ok");
            } else if (cmd.equals("pop_front")) {
                Integer res = q.pop_front();
                if (res == null) {
                    out.println("error");
                } else {
                    out.println(res);
                }
            } else if (cmd.equals("pop_back")) {
                Integer res = q.pop_back();
                if (res == null) {
                    out.println("error");
                } else {
                    out.println(res);
                }
            } else if (cmd.equals("front")) {
                Integer res = q.front();
                if (res == null) {
                    out.println("error");
                } else {
                    out.println(res);
                }
            } else if (cmd.equals("back")) {
                Integer res = q.back();
                if (res == null) {
                    out.println("error");
                } else {
                    out.println(res);
                }
            } else if (cmd.equals("size")) {
                out.println(q.length());
            } else if (cmd.equals("clear")) {
                q = new OwnDeque<>();
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

class OwnDeque<T> {
    private Node<T> first;
    private Node<T> last;
    private int l;

    public OwnDeque(){
        first = null;
        last = null;
        l = 0;
    }

    public void push_front(T val){
        l++;
        if (last == null){
            last = new Node<>();
            first = last;
            last.value = val;
            return;
        }

        Node<T> newNode = new Node<>();
        newNode.value = val;
        newNode.next = first;
        first.prev = newNode;
        first = newNode;
    }
    public void push_back(T val){
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

    public T back(){
        if (last == null) return null;
        return last.value;
    }
    public T front(){
        if (first == null) return null;
        return first.value;
    }

    public T pop_back(){
        if (last == null) return null;
        Node<T> toReturn = last;
        last = last.prev;
        l--;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        return toReturn.value;
    }
    public T pop_front(){
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
        Node<T> prev;
        Node<T> next;
        T value;
    }
}
