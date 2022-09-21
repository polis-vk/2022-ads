import java.util.Scanner;

public final class TaskE {

    private static class Stack<E> {

        private static class Node<E> {
            public Node<E> next;
            public Node<E> prev;
            public E value;

            public Node(E value) {
                next = null;
                prev = null;
                this.value = value;
            }
        }

        private Node<E> head;
        private Node<E> tail;
        private int size;

        public Stack() {
            head = tail = null;
            size = 0;
        }

        public void push(E value) {
            if (head == null){
                head = tail = new Node<>(value);
            }
            else {
                tail.next = new Node<>(value);
                tail.next.prev = tail;
                tail = tail.next;
            }
            size++;
        }

        public void pop() {
            if (head == tail) {
                clear();
            }
            else {
                tail = tail.prev;
                tail.next = null;
                size--;
            }
        }

        public E back() {
            return tail.value;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }

    private static boolean isOperator(char element) {
        return "+-*".contains(Character.toString((element)));
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        Stack<Integer> stackOfDigits = new Stack<>();
        char[] input = in.nextLine().toCharArray();
        for(char element: input){
            if(Character.isDigit(element)){
                stackOfDigits.push(Character.getNumericValue(element));
            }
            else if (isOperator(element)) {
                int firstNumber = stackOfDigits.back();
                stackOfDigits.pop();
                int secondNumber = stackOfDigits.back();
                stackOfDigits.pop();
                switch (element){
                    case '+':
                        secondNumber += firstNumber;
                        break;
                    case '-':
                        secondNumber -= firstNumber;
                        break;
                    case '*':
                        secondNumber *= firstNumber;
                        break;
                }
                stackOfDigits.push(secondNumber);
            }
        }
        System.out.println(stackOfDigits.back());
        stackOfDigits.pop();
    }
}
