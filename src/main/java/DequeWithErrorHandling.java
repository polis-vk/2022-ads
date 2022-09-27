import java.io.*;
import java.util.StringTokenizer;

public class DequeWithErrorHandling {
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

        public Integer back() {
            if (this.backNode == null) {
                return null;
            }
            return this.backNode.value;
        }

        public Integer pop_front() {
            if (this.frontNode == null) {
                return null;
            }

            int value = this.frontNode.value;
            size--;

            Node nexFrontNode = frontNode.nextNode;
            if (nexFrontNode == null) {
                this.frontNode = null;
                this.backNode = null;
                return value;
            }

            this.frontNode = nexFrontNode;
            this.frontNode.prevNode = null;
            return value;
        }

        public Integer pop_back() {
            if (this.backNode == null) {
                return null;
            }

            int value = this.backNode.value;
            size--;

            Node nextBackNode = backNode.prevNode;
            if (nextBackNode == null) {
                this.backNode = null;
                this.frontNode = null;
                return value;
            }

            this.backNode = nextBackNode;
            this.backNode.nextNode = null;
            return value;
        }

        public void push_back(int n) {
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

        public void push_front(int n) {
            if (this.frontNode == null) {
                this.frontNode = new Node();
                this.backNode = this.frontNode;
            } else {
                Node prevFrontNode = this.frontNode;
                this.frontNode = new Node();
                prevFrontNode.prevNode = this.frontNode;
                this.frontNode.nextNode = prevFrontNode;
            }
            size++;
            this.frontNode.value = n;
        }
    }

    private DequeWithErrorHandling() {
        // Should not be instantiated
    }

    private static void solve(final DequeWithErrorHandling.FastScanner in, final PrintWriter out) {
        DequeWithErrorHandling.IntQueue intQueue = new DequeWithErrorHandling.IntQueue();
        boolean isExit = false;

        while (isExit != true) {
            String command = in.next();
            String message = "error";

            switch (command) {
                case "size":
                    message = Integer.toString(intQueue.size());
                    break;
                case "front": {
                    Integer num = intQueue.front();
                    if (num != null) {
                        message = Integer.toString(num);
                    }
                    break;
                }
                case "back": {
                    Integer num = intQueue.back();
                    if (num != null) {
                        message = Integer.toString(num);
                    }
                    break;
                }
                case "pop_front": {
                    Integer num = intQueue.pop_front();
                    if (num != null) {
                        message = Integer.toString(num);
                    }
                    break;
                }
                case "pop_back": {
                    Integer num = intQueue.pop_back();
                    if (num != null) {
                        message = Integer.toString(num);
                    }
                    break;
                }
                case "push_back": {
                    int num = in.nextInt();
                    intQueue.push_back(num);
                    message = "ok";
                    break;
                }
                case "push_front": {
                    int num = in.nextInt();
                    intQueue.push_front(num);
                    message = "ok";
                    break;
                }
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
        final DequeWithErrorHandling.FastScanner in = new DequeWithErrorHandling.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
