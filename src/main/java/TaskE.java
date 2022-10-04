import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public final class TaskE {
    private final static int letters = 26;

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            byte[] count = new byte[letters];
            in.readLine();
            for (byte b : in.readLine().getBytes(StandardCharsets.UTF_8)) {
                count[b - 'A']++;
            }
            StringBuilder halfPalindrome = new StringBuilder();
            String middle = "";
            for (byte letter = 0; letter < letters; letter++) {
                if (middle.equals("") && count[letter] % 2 == 1) {
                    middle = getChar(letter);
                }
                halfPalindrome.append(getChar(letter).repeat(count[letter] / 2));
            }
            String firstHalf = halfPalindrome.toString();
            out.write(firstHalf + middle + halfPalindrome.reverse());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getChar(byte b) {
        return String.valueOf((char) (b + 'A'));
    }
}
//не знаю, почему проходит только на 25