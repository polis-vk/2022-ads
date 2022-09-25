import java.util.ArrayDeque;
import java.util.Scanner;

public class Task5 {
    static int calculatePostfix(String s){
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        s += '\0'; //simulate end of line
        int index = 0;
        while(s.charAt(index) != '\0'){
            if(Character.isDigit(s.charAt(index))){
                arrayDeque.push(s.charAt(index) - '0');
            }else if(s.charAt(index) == '+'){
                arrayDeque.push(arrayDeque.pop() + arrayDeque.pop());
            }else if(s.charAt(index) == '-'){
                int x1 = arrayDeque.pop();
                int x2 = arrayDeque.pop();
                arrayDeque.push(x2 - x1);
            }else if(s.charAt(index) == '*'){
                arrayDeque.push(arrayDeque.pop() * arrayDeque.pop());
            }else if(s.charAt(index) == '/'){
                int div = arrayDeque.pop();
                arrayDeque.push(arrayDeque.pop() / div);
            }
            index++;
        }
        int resultOfCalculate = arrayDeque.pop();
        if(arrayDeque.isEmpty()){
            return resultOfCalculate;
        }else {
            return 0;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(calculatePostfix(input));
    }
}
