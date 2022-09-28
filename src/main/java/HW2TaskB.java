import java.util.Scanner;

public class HW2TaskB {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        int squareIndex = 1;
        int cubeIndex = 1;
        long squareValue = 0;
        long cubeValue = 0;
        int currentIndex = 1;
        while (currentIndex != input) {
            squareValue = (long) Math.pow(squareIndex, 2);
            cubeValue = (long) Math.pow(cubeIndex, 3);
            if(cubeValue == squareValue){
                squareIndex++;
                cubeIndex++;
            }
            else if (squareValue < cubeValue) {
                squareIndex++;
            }
            else {
                cubeIndex++;
            }
            currentIndex++;
        }
        squareValue = (long) Math.pow(squareIndex, 2);
        cubeValue = (long) Math.pow(cubeIndex, 3);
        System.out.println(Math.min(squareValue, cubeValue));
    }
}