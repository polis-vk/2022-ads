package company.vk.polis.ads;

import java.io.PrintWriter;
import java.util.Scanner;

public class PostfixNotation { //Ex5
    private static final Stack stack = new Stack();

    public static void solvedPostfixNotation(final PrintWriter out) {
        for (String temp : new Scanner(System.in).nextLine().trim().split(" ")) {
            switch (temp) {
                case "+":
                    stack.push(applyOperation('+', Integer.parseInt(stack.pop()), Integer.parseInt(stack.pop())));
                    break;
                case "-":
                    int second = Integer.parseInt(stack.pop());
                    int first = Integer.parseInt(stack.pop());
                    stack.push(applyOperation('-', first, second));
                    break;
                case "*":
                    stack.push(applyOperation('*', Integer.parseInt(stack.pop()), Integer.parseInt(stack.pop())));
                    break;
                default:
                    stack.push(Integer.parseInt(temp));
            }
        }

        out.print(Integer.parseInt(stack.pop()));
    }

    private static int applyOperation(char op, int first, int second) {
        switch (op) {
            case '+':
                return first + second;
            case '-':
                return first - second;
            case '*':
                return first * second;
            case '/':
                return first / second;
            case '%':
                return first % second;
            default:
                return 0;
        }
    }
}
