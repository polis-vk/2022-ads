import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out){
        Queue queue = new Queue();
        while(true){
            switch (in.next()){
                case "push" :
                    int input = in.nextInt();
                    queue.push(input);
                    out.println("ok");
                    break;
                case "pop" :
                    if(queue.size() == 0){
                        out.println("error");
                    }else{
                        out.println(queue.pop());
                    }
                    break;
                case "front" :
                    if(queue.size() == 0){
                        out.println("error");
                    }else{
                        out.println(queue.front());
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
            }
        }
    }

    public static class Queue{
        private final ArrayList<Integer> array;
        private int front_;
        Queue(){
            array = new ArrayList<>();
            front_ = -1;
        }
        public void push(int n){
            if(array.isEmpty()){
                front_ = 0;
                array.add(n);
            }else{
                front_++;
                if(array.size() > front_){
                    array.set(front_, n);
                }else{
                    array.add(n);
                }
            }
        }
        public String pop(){
            return (String.valueOf(array.remove(0)));
        }
        public String front(){
            return (String.valueOf(array.get(0)));
        }
        public int size(){
            return array.size();
        }
        public boolean isEmpty(){
            return size() == 0;
        }
        public void clear(){
            array.clear();
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
