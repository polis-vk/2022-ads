import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskE {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            Stack stack = new Stack();
            String[] strings = in.readLine().split(" ");
            for (int i = 0; i < strings.length; i++) {
                switch (strings[i]) {
                    case "+" -> stack.push(stack.pop() + stack.pop());
                    case "-" -> {
                        int subtrahend = stack.pop();
                        int minuend = stack.pop();
                        stack.push(minuend - subtrahend);
                    }
                    case "*" -> stack.push(stack.pop() * stack.pop());
                    case "/" -> {
                        int divider = stack.pop();
                        int dividend = stack.pop();
                        stack.push(dividend / divider);
                    }
                    default -> stack.push(Integer.parseInt(strings[i]));
                }
            }
            System.out.println(stack.pop());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Stack {
        private Node rear, front;
        private int size;

        private class Node {
            final private int number;
            private Node previous;

            Node(int number, Node previous) {
                this.number = number;
                this.previous = previous;
            }
        }

        public Stack() {
            rear = null;
            front = null;
            size = 0;
        }

        public void push(int n) {
            if (size == 0) {
                front = rear = new Node(n, null);
            } else {
                Node oldRear = rear;
                rear = new Node(n, oldRear);
            }
            size++;
        }

        public int pop() {
            int num = rear.number;
            rear = rear.previous;
            size--;
            if (size == 0) {
                front = null;
            }
            return num;
        }

        public int back() {
            return rear.number;
        }

        public int size() {
            return size;
        }

        public void clear() {
            while (size != 0) {
                rear = rear.previous;
                size--;
            }
            rear = null;
            front = null;
        }
    }

}
