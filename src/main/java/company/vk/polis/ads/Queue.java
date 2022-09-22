package company.vk.polis.ads;

import java.io.PrintWriter;

public class Queue { //Ex2

    static class Node {
        int value;
        Node nextNode;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private static final String BYE = "bye";

    public Queue() {
    }

    private String push(int n) {
        if (isEmpty()) {
            head = new Node(n);
            tail = head;
        } else {
            tail.nextNode = new Node(n);
            tail = tail.nextNode;
        }

        size++;
        return OK;
    }

    private String pop() {
        if (isEmpty()) {
            return ERROR;
        }

        String popValue = Integer.toString(head.value);
        head = head.nextNode;
        size--;
        return popValue;
    }

    private String front() {
        return !isEmpty() ? Integer.toString(head.value) : ERROR;
    }

    private String size() {
        return Integer.toString(size);
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private String clear() {
        head = null;
        size = 0;
        return OK;
    }

    private String exit() {
        head = null;
        size = 0;
        return BYE;
    }

    public static void queueManager(final SolveTemplate.FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        while (printLog(queue, in, out)) {
        }
    }

    private static boolean printLog(Queue queue, final SolveTemplate.FastScanner in, final PrintWriter out) {
        String command = in.next();

        if (command.contains("push")) {
            out.println(queue.push(in.nextInt()));
            return true;
        }

        switch (command) {
            case "pop":
                out.println(queue.pop());
                break;
            case "front":
                out.println(queue.front());
                break;
            case "size":
                out.println(queue.size());
                break;
            case "clear":
                out.println(queue.clear());
                break;
            case "exit":
                out.println(queue.exit());
                return false;
            default:
                return false;
        }

        return true;
    }
}
