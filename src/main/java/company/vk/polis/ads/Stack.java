package company.vk.polis.ads;

import java.io.PrintWriter;

public class Stack { //Ex3

    static class Node {
        int value;
        Node nextNode;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private int size;
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private static final String BYE = "bye";

    public Stack() {
    }

    public String push(int n) {
        if (isEmpty()) {
            head = new Node(n);
        } else {
            Node node = new Node(n);
            node.nextNode = head;
            head = node;
        }

        size++;
        return OK;
    }

    public String pop() {
        if (isEmpty()) {
            return ERROR;
        }

        String popValue = Integer.toString(head.value);
        head = head.nextNode;
        size--;
        return popValue;
    }

    public String back() {
        return !isEmpty() ? Integer.toString(head.value) : ERROR;
    }

    public String size() {
        return Integer.toString(size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String clear() {
        head = null;
        size = 0;
        return OK;
    }

    public String exit() {
        head = null;
        size = 0;
        return BYE;
    }

    public static void stackManager(final SolveTemplate.FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        while (printLog(stack, in, out)) {
        }
    }

    private static boolean printLog(Stack stack, final SolveTemplate.FastScanner in, final PrintWriter out) {
        String command = in.next();

        if (command.contains("push")) {
            out.println(stack.push(in.nextInt()));
            return true;
        }

        switch (command) {
            case "pop":
                out.println(stack.pop());
                break;
            case "back":
                out.println(stack.back());
                break;
            case "size":
                out.println(stack.size());
                break;
            case "clear":
                out.println(stack.clear());
                break;
            case "exit":
                out.println(stack.exit());
                return false;
            default:
                return false;
        }

        return true;
    }

}
