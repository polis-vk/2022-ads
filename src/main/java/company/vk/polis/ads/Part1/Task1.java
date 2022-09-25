import java.util.*;

public class Task1{
    static long lastDigit(long n){
        return n%10;
    }
    public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long n = in.nextInt();
    System.out.println(lastDigit(n));
    }
}
