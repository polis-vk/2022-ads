import java.io.*;
import java.util.StringTokenizer;

 public  class TaskB {

    private static class QueueIml{
        Node head;
        Node tail;

        private int size;

        QueueIml() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        private static class Node {
            int value;
            Node next;

            Node(int value) {
                this.value = value;
                this.next = null;
            }

        }


        public void push(int value) {
            size++;
            Node node = new Node(value);
            if (head == null) {
                head = node;
                tail = head;
                return;
            }
            tail.next = node;
            tail = node;

        }

        public int pop() {
            int value = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return value;
        }

        public int front() {
            return head.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

    }


     private static void solve(final FastScanner in, final PrintWriter out) {
         QueueIml queueIml = new QueueIml();

         while (true) {
             String input = in.next();
             switch (input) {
                 case "push":
                     queueIml.push(in.nextInt());
                     out.println("ok");
                     break;
                 case "pop":
                     try {
                         out.println((queueIml.pop()));
                     } catch (Exception e) {
                         out.println("error");
                     }
                     break;
                 case "front":
                     try {
                         out.println(queueIml.front());
                     } catch (Exception e) {
                         out.println("error");
                     }
                     break;
                 case "size":
                     out.println(queueIml.size());
                     break;
                 case "clear":
                     queueIml.clear();
                     out.println("ok");
                     break;
                 case "exit":
                     out.println("bye");
                     return;
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
         }catch (Exception e) {
             e.printStackTrace();
         }
     }
 }