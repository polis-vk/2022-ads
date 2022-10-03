public class Palindrome {
    public void getLongestPalindrome(char[] chars) {
        int[] letterCounter = new int[26];
        for (char c : chars) {
            letterCounter[c - 65]++;
        }
        for (int i = 0; i < letterCounter.length; i++) {
            for (int j = 0; j < letterCounter[i] / 2; j++) {
                System.out.print((char) (i + 65));
            }
        }
        for (int i = 0; i < letterCounter.length; i++) {
            if (letterCounter[i] % 2 == 1) {
                System.out.print((char) (i + 65));
                break;
            }
        }
        for (int i = letterCounter.length - 1; i >= 0; i--) {
            for (int j = 0; j < letterCounter[i] / 2; j++) {
                System.out.print((char) (i + 65));
            }
        }
    }
}
