package company.vk.polis.ads.part1.paikeee;

import java.util.ArrayList;
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
public final class QueueWithDefence {
    private QueueWithDefence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Queue myQueue = new Queue();
        String command = in.reader.readLine();
        while (!command.equals("exit")) {
            switch (command) {
                case "size" -> {
                    out.println(myQueue.size());
                }
                case "clear" -> {
                    myQueue.clear();
                    out.println("ok");
                }
                case "pop" -> {
                    if (myQueue.size() != 0) {
                        out.println(myQueue.pop());
                    } else {
                        out.println("error");
                    }
                }
                case "front" -> {
                    if (myQueue.size() != 0) {
                        out.println(myQueue.front());
                    } else {
                        out.println("error");
                    }
                }
            }
            if (command.startsWith("push")) {
                myQueue.push(Integer.parseInt(command.split(" ")[1]));
                out.println("ok");
            }
            command = in.reader.readLine();
        }
        out.println("bye");
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
            try {
                solve(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


class Queue {

    private final ArrayList<Integer> queue;
    private int currentSize;

    public Queue() {
        queue = new ArrayList<>();
        currentSize = 0;
    }

    int size() {
        return currentSize;
    }

    void push(int num) {
        queue.add(num);
        currentSize++;
    }

    int pop() {
        int temp = front();
        queue.remove(0);
        currentSize--;
        return temp;
    }

    int front() {
        return queue.get(0);
    }

    void clear() {
        queue.clear();
        currentSize = 0;
    }
}