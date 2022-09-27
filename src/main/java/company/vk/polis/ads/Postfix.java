package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Postfix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack stack = new Stack();
        String str = reader.readLine();
        String[] array = str.split(" ");
        String elementPostfix;

        for (int i = 0; i < array.length; i++) {
            elementPostfix = String.valueOf(array[i]);
            int num1;
            int num2;

            switch (elementPostfix) {
                case "+" -> {
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num1 + num2);
                }
                case "-" -> {
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 - num1);
                }
                case "*" -> {
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num1 * num2);
                }
                default -> stack.push(Integer.parseInt(elementPostfix));
            }
        }
        System.out.println(stack.pop());
    }

}
final class Stack {
    List<Integer> innerArray = new ArrayList<>();

    public String push(Integer n) {
        innerArray.add(n);
        return "ok\n";
    }

    public Integer pop() {
        Integer num = innerArray.get(innerArray.size() - 1);
        innerArray.remove(innerArray.size() - 1);
        return num;
    }

    public Integer back() {
        return innerArray.get(innerArray.size() - 1);
    }

    public Integer size() {
        return innerArray.size();
    }

    public String clear() {
        innerArray.clear();
        return "ok\n";
    }
}
