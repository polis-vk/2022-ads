import java.util.Scanner;

public class HW2TaskE {
    private static final byte ALPHABET_SIZE = 26;

    private static int getAlphabetIndex(char element){
        return element - 'A';
    }

    private static char getLetterFromIndex(int index){
        return (char)(index + 'A');
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] alphabet = new int[ALPHABET_SIZE];
        char[] data = in.next().toCharArray();
        for (char el : data){
            alphabet[getAlphabetIndex(el)]++;
        }

        StringBuilder result = new StringBuilder();
        int indexOfSingleLetter = -1;
        for (int i = 0; i < alphabet.length; i++){
            while (alphabet[i] / 2 != 0) {
                int midIndex = result.length() / 2;
                result.insert(midIndex, getLetterFromIndex(i));
                result.insert(midIndex, getLetterFromIndex(i));
                alphabet[i] -= 2;
            }
            if (alphabet[i] % 2 == 1 && indexOfSingleLetter == -1){
                indexOfSingleLetter = i;
            }
        }
        if (indexOfSingleLetter != -1){
            int midIndex = result.length() / 2;
            result.insert(midIndex, getLetterFromIndex(indexOfSingleLetter));
        }
        System.out.println(result);
    }
}
