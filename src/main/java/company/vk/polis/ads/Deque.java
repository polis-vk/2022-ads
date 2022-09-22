package company.vk.polis.ads;

import java.io.PrintWriter;

public class Deque { //Ex6


    static class Node {
        int value;
        Node nextNode;
        Node beforeNode;

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

    private String push_front(int n) {
        if (isEmpty()) {
            head = new Node(n);
            tail = head;
        } else {
            Node node = new Node(n);
            node.nextNode = head;
            head.beforeNode = node;
            head = node;
        }

        size++;
        return OK;
    }

    private String push_back(int n) {
        if (isEmpty()) {
            tail = new Node(n);
            head = tail;
        } else {
            Node node = new Node(n);
            node.beforeNode = tail;
            tail.nextNode = node;
            tail = node;
        }

        size++;
        return OK;
    }

    private String pop_front() {
        if (isEmpty()) {
            return ERROR;
        }

        String value = Integer.toString(head.value);
        head = head.nextNode;
        size--;
        return value;
    }

    private String pop_back() {
        if (isEmpty()) {
            return ERROR;
        }

        String value = Integer.toString(tail.value);
        tail = tail.beforeNode;
        size--;
        return value;
    }

    private String front() {
        return !isEmpty() ? Integer.toString(head.value) : ERROR;
    }

    private String back() {
        return !isEmpty() ? Integer.toString(tail.value) : ERROR;
    }

    private String size() {
        return Integer.toString(size);
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private String clear() {
        head = null;
        tail = null;
        size = 0;
        return OK;
    }

    private String exit() {
        head = null;
        tail = null;
        size = 0;
        return BYE;
    }

    public static void stackManager(final SolveTemplate.FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        while (printLog(deque, in, out)) {
        }
    }

    private static boolean printLog(Deque deque, final SolveTemplate.FastScanner in, final PrintWriter out) {
        String command = in.next();

        if (command.contains("push_front")) {
            out.println(deque.push_front(in.nextInt()));
            return true;
        }
        if (command.contains("push_back")) {
            out.println(deque.push_back(in.nextInt()));
            return true;
        }

        switch (command) {
            case "pop_front":
                out.println(deque.pop_front());
                break;
            case "pop_back":
                out.println(deque.pop_back());
                break;
            case "front":
                out.println(deque.front());
                break;
            case "back":
                out.println(deque.back());
                break;
            case "size":
                out.println(deque.size());
                break;
            case "clear":
                out.println(deque.clear());
                break;
            case "exit":
                out.println(deque.exit());
                return false;
            default:
                return false;
        }

        return true;
    }
}
