//package com.company;

import java.io.*;
import java.util.*;

public class PostfixExpression {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<Integer> operands = new LinkedList<>();
        String input;
        while (in.hasNext()) {
            input = in.next();
            switch (input) {
                case "+":
                    operands.addLast(
                        operands.pollLast() + operands.pollLast()
                    );
                    break;
                case "-":
                    int
                            secondOperand = operands.pollLast(),
                            firstOperand = operands.pollLast();
                            operands.addLast(firstOperand - secondOperand);
                    break;
                case "*":
                    operands.addLast(
                            operands.pollLast() * operands.pollLast()
                    );
                    break;
                default:
                    operands.addLast(Integer.parseInt(input));
                    break;
            }
        }
        System.out.println(operands.pollLast());
    }
}