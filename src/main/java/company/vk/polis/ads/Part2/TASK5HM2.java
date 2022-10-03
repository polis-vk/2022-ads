import java.util.Scanner;

public class TASK5HM2 {
    static void fillFrequencyArray(int[] array, String s){
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i)]++;
        }
    }

    static void twoHalvesPalindrome(int[] array, StringBuilder palindrome, int whatHalf){
        if(whatHalf == 0){
            for(int i = 65; i <= 90; i++){
                for (int j = 1; j <= array[i] / 2; j++) {
                    palindrome.append((char) i);
                }
            }
        }else{
            for (int i = 90; i >= 65; i--) {
                for (int j = 1; j <= array[i] / 2; j++) {
                    palindrome.append((char) i);
                }
            }
        }
    }

    static void centerSymbolOfPalindrom(int[] array, StringBuilder palindrome){
        for (int i = 65; i <= 90; i++) {
            if(array[i] % 2 == 1){
                palindrome.append((char) i);
                break;
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        String s = in.next();

        int[] array = new int[128];

        StringBuilder palindrome = new StringBuilder();

        fillFrequencyArray(array, s);

        twoHalvesPalindrome(array, palindrome, 0);

        centerSymbolOfPalindrom(array, palindrome);

        twoHalvesPalindrome(array, palindrome, 1);

        System.out.println(palindrome);
    }
}

