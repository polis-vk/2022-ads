package company.vk.polis.ads;

import java.util.Scanner;
import java.util.Stack;

public class PostfixRead {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        String[] input = in.nextLine().split(" ");
        String element;
        char check;
        int left, right, result;
        for (String s : input) {
            element = s;
            check = element.charAt(0);
            if (check >= '0' && check <= '9') {
                stack.push(Integer.parseInt(element));
            } else {
                switch (check) {
                    case '+' -> {
                        right = stack.pop();
                        left = stack.pop();
                        result = left + right;
                        stack.push(result);
                    }
                    case '-' -> {
                        right = stack.pop();
                        left = stack.pop();
                        result = left - right;
                        stack.push(result);
                    }
                    case '*' -> {
                        right = stack.pop();
                        left = stack.pop();
                        result = left * right;
                        stack.push(result);
                    }
                    case '/' -> {
                        right = stack.pop();
                        left = stack.pop();
                        result = left / right;
                        stack.push(result);
                    }
                }
            }
        }
        System.out.println(stack.pop());
    }
}