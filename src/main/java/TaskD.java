import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TaskD {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            final String YES = "yes";
            final String NO = "no";
            if (isValidBrackets(in.readLine())) {
                System.out.println(YES);
            } else {
                System.out.println(NO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidBrackets(String string) {
        Map<Integer, Integer> brackets = new HashMap<>();
        brackets.put((int) ')', (int) '(');
        brackets.put((int) '}', (int) '{');
        brackets.put((int) ']', (int) '[');
        Stack stack = new Stack();
        for (int i :
                string.getBytes(StandardCharsets.UTF_8)) {
            if (brackets.containsValue(i)) {
                stack.push(i);
            } else if (brackets.containsKey(i)) {
                if (stack.size == 0 || stack.pop() != brackets.get(i))
                    return false;
            }
        }
        return stack.size == 0;
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
