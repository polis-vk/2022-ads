package part1;

import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {

    private static class MyStack<T> {
        ArrayList<T> stack = new ArrayList<>();

        public void push(T x) {
            stack.add(x);
        }

        public void pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException();
            }
            stack.remove(stack.size() - 1);
        }

        public T peek() {
            if (stack.isEmpty()) {
                throw new RuntimeException();
            }
            return stack.get(stack.size() - 1);
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        Scanner scanner = new Scanner(System.in);
        String brackets = scanner.nextLine();
        char a;
        int i, k;
        boolean flag = true;
        for (i = 0; i < brackets.length(); i++) {
            a = brackets.charAt(i);
            switch (a) {
                case '(' -> stack.push(1);
                case '[' -> stack.push(2);
                case '{' -> stack.push(3);
                case ')' -> {
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    k = stack.peek();
                    stack.pop();
                    if (k != 1) flag = false;
                }
                case ']' -> {
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    k = stack.peek();
                    stack.pop();
                    if (k != 2) flag = false;
                }
                case '}' -> {
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    k = stack.peek();
                    stack.pop();
                    if (k != 3) flag = false;
                }
                default -> {
                }
            }
            if (!flag) {
                break;
            }
        }
        if (flag && stack.isEmpty()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
