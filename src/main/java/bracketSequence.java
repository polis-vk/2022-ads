import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bracketSequence {

    private static boolean isBracketsPaired(String inputString, int firstPosition, int secondPosition) {

        return (inputString.charAt(firstPosition) == '(' && inputString.charAt(secondPosition) == ')')
                || (inputString.charAt(firstPosition) == '[' && inputString.charAt(secondPosition) == ']');
    }

    private static void getOptimalSolutionForSegment(String[][] bracketsSequencesMatrix, int startIndex, int endIndex, int minPossibleLength) {

        for (int separatorPosition = 0; separatorPosition + startIndex < endIndex; separatorPosition++) {

            int currentLength = bracketsSequencesMatrix[startIndex][separatorPosition + startIndex].length() + bracketsSequencesMatrix[separatorPosition + startIndex + 1][endIndex].length();

            if (currentLength < minPossibleLength) {
                bracketsSequencesMatrix[startIndex][endIndex] = bracketsSequencesMatrix[startIndex][separatorPosition + startIndex]
                        + bracketsSequencesMatrix[separatorPosition + startIndex + 1][endIndex];
                minPossibleLength = currentLength;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String inputData = in.readLine();

        int size = inputData.length();
        if(size == 0){
            System.out.println("");
            return;
        }

        String[][] bracketsSequencesMatrix = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bracketsSequencesMatrix[i][j] = "";
            }
        }

        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < size; i++) {
            startIndex = 0;
            endIndex = i;

            while (startIndex < size && endIndex < size) {
                if (startIndex == endIndex) {
                    switch (inputData.charAt(startIndex)) {
                        case '(', ')' -> {
                            bracketsSequencesMatrix[startIndex][endIndex] = "()";
                        }
                        case '[', ']' -> {
                            bracketsSequencesMatrix[startIndex][endIndex] = "[]";
                        }
                    }
                } else if (isBracketsPaired(inputData, startIndex, endIndex)) {
                    bracketsSequencesMatrix[startIndex][endIndex] = inputData.charAt(startIndex) + bracketsSequencesMatrix[startIndex + 1][endIndex - 1] + inputData.charAt(endIndex);
                    getOptimalSolutionForSegment(bracketsSequencesMatrix, startIndex, endIndex, bracketsSequencesMatrix[startIndex][endIndex].length());
                } else {
                    getOptimalSolutionForSegment(bracketsSequencesMatrix, startIndex, endIndex, Integer.MAX_VALUE);
                }

                startIndex++;
                endIndex++;
            }
        }

        System.out.println(bracketsSequencesMatrix[0][size - 1]);

    }
}