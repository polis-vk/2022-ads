import java.util.Scanner;

public class Mouse {
    private static String getSequence(int[][] maxValue, int maxAmountOfRows, int maxAmountOfColumns){
        StringBuilder result = new StringBuilder();
        int row = 0;
        int column = maxAmountOfColumns - 1;
        while (row < maxAmountOfRows - 1 && column > 0) {
            if (maxValue[row + 1][column] > maxValue[row][column - 1]) {
                row++;
                result.append('F');
            } else {
                column--;
                result.append('R');
            }
        }

        while (row < maxAmountOfRows - 1) {
            row++;
            result.append('F');
        }

        while (column > 0) {
            column--;
            result.append('R');
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] inputData = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                inputData[i][j] = in.nextInt();
            }
        }

        int[][] arrayForMaxValues = new int[n][m];
        for (int i = n - 1; i >= 0; i--){
            for (int j = 0; j < m; j++){
                if (i == n - 1 && j == 0){
                    arrayForMaxValues[i][j] = inputData[i][j];
                }
                else if (i + 1 < n && j - 1 >= 0) {
                    if (arrayForMaxValues[i][j - 1] >  arrayForMaxValues[i + 1][j]){
                        arrayForMaxValues[i][j] = arrayForMaxValues[i][j - 1] + inputData[i][j];
                    }
                    else{
                        arrayForMaxValues[i][j] = arrayForMaxValues[i + 1][j] + inputData[i][j];
                    }
                }
                else if (i + 1 >= n){
                    arrayForMaxValues[i][j] = arrayForMaxValues[i][j - 1] + inputData[i][j];
                }
                else {
                    arrayForMaxValues[i][j] = arrayForMaxValues[i + 1][j] + inputData[i][j];
                }
            }
        }
        System.out.println(getSequence(arrayForMaxValues, n, m));
    }
}
