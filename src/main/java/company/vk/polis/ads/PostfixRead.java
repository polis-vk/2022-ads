
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class PostfixRead {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();
        String[] input = in.nextLine().split(" ");
        String element;
        char check;
        int left, right, result;
        for (String s : input) {
            element = s;
            check = element.charAt(0);
            if (check >= '0' && check <= '9') {
                stack.addLast(Integer.parseInt(element));
            } else {
                switch (check) {
                    case '+' -> {
                        right = stack.pollLast();
                        left = stack.pollLast();
                        result = left + right;
                        stack.addLast(result);
                    }
                    case '-' -> {
                        right = stack.pollLast();
                        left = stack.pollLast();
                        result = left - right;
                        stack.addLast(result);
                    }
                    case '*' -> {
                        right = stack.pollLast();
                        left = stack.pollLast();
                        result = left * right;
                        stack.addLast(result);
                    }
                    case '/' -> {
                        right = stack.pollLast();
                        left = stack.pollLast();
                        result = left / right;
                        stack.addLast(result);
                    }
                }
            }
        }
        System.out.println(stack.pollLast());
    }
}