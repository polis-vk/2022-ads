import java.util.*;
import java.util.Scanner;

public class Task4 {
    static String balanceBrackets(String s){
        ArrayDeque<Character> stackArray = new ArrayDeque<>();
        s += '\0'; //simulate end of line
        char temp;
        int count = 0;
        int index = 0;
        while(s.charAt(index) != '\0'){
            if(s.charAt(index) == '('
                    || s.charAt(index) == '['
                    || s.charAt(index) == '{'){
                stackArray.push(s.charAt(index));
                index++;
                count++;
            }else if(s.charAt(index) == ')'
                    || s.charAt(index) == ']'
                    || s.charAt(index) == '}'){
                count--;
                if(count < 0){
                    return "no";
                }
                temp = stackArray.pop();
                if(!(temp == '(' && s.charAt(index) == ')'
                        || temp == '[' && s.charAt(index) == ']'
                        || temp == '{' && s.charAt(index) == '}')){
                    return "no";
                }
                index++;
            }else{
                index++;
            }
        }
        if(count != 0){
            return "no";
        }else{
            return "yes";
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        System.out.println(balanceBrackets(input));
    }
}