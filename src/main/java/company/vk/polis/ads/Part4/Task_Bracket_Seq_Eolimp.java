import java.util.Scanner;

public class Task_Bracket_Seq_Eolimp {
    static int[][] lenOfSeq;
    static int[][] position;
    static String s;
    static final int LIMIT = Integer.MAX_VALUE;

    static void outStringOfCalculation(int l, int r){
        if(l > r){
            return;
        }
        if(l == r){
            if(s.charAt(l) == '(' || s.charAt(r) == ')'){
                System.out.print("()");
            }else{
                System.out.print("[]");
            }
        }else if(position[l][r] == -1){
            System.out.print(s.charAt(l)); //первый
            outStringOfCalculation(l + 1, r - 1); //сдвигаем указатели и продолжаем
            System.out.print(s.charAt(r)); //последний
        }else{
            outStringOfCalculation(l, position[l][r]);
            outStringOfCalculation(position[l][r] + 1, r);
        }
    }

    static int minimalBracketsInDP(int l, int r){
        if(l == r){
            return 1;
        }
        if(l > r){
            return 0;
        }
        if(lenOfSeq[l][r] != LIMIT){
            return lenOfSeq[l][r];
        }
        if(s.charAt(l) == '(' && s.charAt(r) == ')'
        || s.charAt(l) == '[' && s.charAt(r) == ']'){
            lenOfSeq[l][r] = minimalBracketsInDP(l + 1, r - 1); //сдвигаем указатели друг к другу
        }
        for(int k = l; k < r; k++){
            int tmp = minimalBracketsInDP(l, k) + minimalBracketsInDP(k+1, r);
            if(tmp < lenOfSeq[l][r]){
                position[l][r] = k;
                lenOfSeq[l][r] = tmp;
            }
        }
        return lenOfSeq[l][r];
    }

    static void initArrays(){
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position.length; j++) {
                position[i][j] = -1;
                lenOfSeq[i][j] = LIMIT;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        if(s.isEmpty()){
            return;
        }
        lenOfSeq = new int[s.length()][s.length()];
        position = new int[s.length()][s.length()];
        initArrays();
        minimalBracketsInDP(0, s.length() - 1);
        outStringOfCalculation(0, s.length() - 1);
    }
}
