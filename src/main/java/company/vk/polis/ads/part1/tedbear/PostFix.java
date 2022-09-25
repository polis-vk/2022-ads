package company.vk.polis.ads.part1.tedbear;

import java.io.*;
import java.util.ArrayList;

public class PostFix {

    public static void main(final String[] arg) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MyStack stack = new MyStack();
        String str = reader.readLine();
        String[] strArray = str.split(" ");
        String postFixEntry;

        for (int i = 0; i < strArray.length; i++) {
            postFixEntry = String.valueOf(strArray[i]);
            int num1;
            int num2;

            switch (postFixEntry) {
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
                default -> stack.push(Integer.parseInt(postFixEntry));
            }
        }

        System.out.println(stack.pop());
    }
}

class MyStack {
    private int size;
    private final ArrayList<Integer> internalArray = new ArrayList<>();

    public void push(Integer n) {
        internalArray.add(n);
        this.size++;

    }

    public int pop() {
        int lastElement = internalArray.get(this.size - 1);
        internalArray.remove(this.size - 1);
        this.size--;
        return lastElement;
    }

    public Integer back() {
        return internalArray.get(this.size - 1);
    }

    public Integer size() {
        return this.size;
    }

    public String clear() {
        this.size = 0;
        internalArray.clear();

        return "ok\n";
    }
}
