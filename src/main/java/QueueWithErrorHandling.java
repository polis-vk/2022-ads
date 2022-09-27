import java.io.*;
import java.util.StringTokenizer;

public class QueueWithErrorHandling {
    private static final class IntQueue {
        private static class Node {
            Node prevNode;
            Node nextNode;
            int value;
        }

        Node frontNode;
        Node backNode;
        int size;

        public IntQueue() {
            this.clear();
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.frontNode = null;
            this.backNode = null;
            this.size = 0;
        }

        public Integer front() {
            if (this.frontNode == null) {
                return null;
            }
            return this.frontNode.value;
        }

        public Integer pop() {
            if (this.frontNode == null) {
                return null;
            }

            int value = this.frontNode.value;
            size--;

            Node nexFrontNode = frontNode.nextNode;
            if (nexFrontNode == null) {
                this.frontNode = null;
                return value;
            }

            this.frontNode = nexFrontNode;
            nexFrontNode.prevNode = null;
            return value;
        }

        public void push(int n) {
            if (this.frontNode == null) {
                this.frontNode = new Node();
                this.backNode = this.frontNode;
            } else {
                Node prevBackNode = this.backNode;
                this.backNode = new Node();
                prevBackNode.nextNode = this.backNode;
                this.backNode.prevNode = prevBackNode;
            }
            size++;
            this.backNode.value = n;
        }
    }

    private QueueWithErrorHandling() {
        // Should not be instantiated
    }

    private static void solve(final QueueWithErrorHandling.FastScanner in, final PrintWriter out) {
        QueueWithErrorHandling.IntQueue intQueue = new QueueWithErrorHandling.IntQueue();
        boolean isExit = false;

        while (isExit != true) {
            String command = in.next();
            String message = "error";

            switch (command) {
                case "size":
                    message = Integer.toString(intQueue.size());
                    break;
                case "front":
                    Integer numFront = intQueue.front();
                    if (numFront != null) {
                        message = Integer.toString(numFront);
                    }
                    break;
                case "pop":
                    Integer numPop = intQueue.pop();
                    if (numPop != null) {
                        message = Integer.toString(numPop);
                    }
                    break;
                case "push":
                    int n = in.nextInt();
                    intQueue.push(n);
                    message = "ok";
                    break;
                case "clear":
                    intQueue.clear();
                    message = "ok";
                    break;
                case "exit":
                    isExit = true;
                    message = "bye";
                    break;
            }

            out.println(message);
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
        final QueueWithErrorHandling.FastScanner in = new QueueWithErrorHandling.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
