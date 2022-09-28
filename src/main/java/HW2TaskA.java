import java.util.Scanner;

public class HW2TaskA {
    private static final int LIMITED_ARRAY_SIZE = 215;

    private static int getIndex(int number, int dIndex){
        return number - dIndex;
    }

    private static int getNumber(int index, int dIndex){
        return index + dIndex;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] limitedArray = new int[LIMITED_ARRAY_SIZE];
        int dIndex = 0;
        for (int i = 0; i < size; i++){
            if (i == 0){
                int firstElement = in.nextInt();
                dIndex = firstElement - LIMITED_ARRAY_SIZE / 2;
                limitedArray[getIndex(firstElement, dIndex)]++;
            }
            else {
                limitedArray[getIndex(in.nextInt(), dIndex)]++;
            }
        }
        boolean withSpace = false;
        for (int i = 0; i < limitedArray.length; i++){
            for (int j = 0; j < limitedArray[i]; j++){
                if (withSpace){
                    System.out.print(" ");
                }
                System.out.print(getNumber(i, dIndex));
                withSpace = true;
            }
        }
    }
}
