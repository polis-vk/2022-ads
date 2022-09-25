import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task6 {
    private Task6() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        while(true){
            switch (in.next()){
                case "push_front":
                    int front = in.nextInt();
                    deque.push_front(front);
                    out.println("ok");
                    break;
                case "push_back":
                    int back = in.nextInt();
                    deque.push_back(back);
                    out.println("ok");
                    break;
                case "pop_front":
                    if(deque.size() == 0){
                        out.println("error");
                    }else{
                        out.println(deque.pop_front());
                    }
                    break;
                case "pop_back":
                    if(deque.size() == 0){
                        out.println("error");
                    }else{
                        out.println(deque.pop_back());
                    }
                    break;
                case "front":
                    if(deque.size() == 0){
                        out.println("error");
                    }else{
                        out.println(deque.front());
                    }
                    break;
                case "back": //1 2 3 4 5
                    if(deque.size() == 0){
                        out.println("error");
                    }else{
                        out.println(deque.back());
                    }
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
            }
        }
    }

static class Deque{
    private final List<Integer> arrayList;
    private int front;
    private int back;
    public Deque(){
        arrayList = new ArrayList<>();
        front = -1;
        back = 0;
    }
    public void push_front(int n){

        arrayList.add(0, n);
    }
    public void push_back(int n){

        arrayList.add(n);
    }
    public int pop_front(){
        int topElement = arrayList.get(0);
        arrayList.remove(0);
        return topElement;
    }
    public int pop_back(){
        int bottomElement = arrayList.get(arrayList.size() - 1);
        arrayList.remove(arrayList.size() - 1);
        return bottomElement;
    }
    public int front(){
        int topElement = arrayList.get(0);
        return topElement;
    }
    public int back(){
        int bottomElement = arrayList.get(arrayList.size() - 1);
        return bottomElement;
    }
    public int size(){
        return arrayList.size();
    }
    public void clear(){
        arrayList.clear();
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