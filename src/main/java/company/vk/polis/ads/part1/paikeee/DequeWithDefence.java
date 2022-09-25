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
public final class DequeWithDefence {
    private DequeWithDefence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Deque myDeque = new Deque();
        String command = in.reader.readLine();
        while (!command.equals("exit")) {
            switch (command) {
                case "size" -> {
                    out.println(myDeque.size());
                }
                case "clear" -> {
                    myDeque.clear();
                    out.println("ok");
                }
                case "pop_front" -> {
                    if (myDeque.size() != 0) {
                        out.println(myDeque.popFront());
                    } else {
                        out.println("error");
                    }
                }
                case "pop_back" -> {
                    if (myDeque.size() != 0) {
                        out.println(myDeque.popBack());
                    } else {
                        out.println("error");
                    }
                }
                case "front" -> {
                    if (myDeque.size() != 0) {
                        out.println(myDeque.front());
                    } else {
                        out.println("error");
                    }
                }
                case "back" -> {
                    if (myDeque.size() != 0) {
                        out.println(myDeque.back());
                    } else {
                        out.println("error");
                    }
                }
            }
            if (command.startsWith("push_front")) {
                myDeque.pushFront(Integer.parseInt(command.split(" ")[1]));
                out.println("ok");
            }
            if (command.startsWith("push_back")) {
                myDeque.pushBack(Integer.parseInt(command.split(" ")[1]));
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


class Deque {

    private final ArrayList<Integer> deque;
    private int currentSize;

    public Deque() {
        deque = new ArrayList<>();
        currentSize = 0;
    }

    int size() {
        return currentSize;
    }

    void pushBack(int num) {
        deque.add(num);
        currentSize++;
    }

    void pushFront(int num) {
        deque.add(0, num);
        currentSize++;
    }

    int popFront() {
        int temp = front();
        deque.remove(0);
        currentSize--;
        return temp;
    }

    int popBack() {
        int temp = back();
        deque.remove(currentSize - 1);
        currentSize--;
        return temp;
    }

    int front() {
        return deque.get(0);
    }

    int back() {
        return deque.get(currentSize - 1);
    }

    void clear() {
        deque.clear();
        currentSize = 0;
    }
}