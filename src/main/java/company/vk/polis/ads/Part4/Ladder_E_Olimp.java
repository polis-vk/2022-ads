import java.util.Scanner;

public class Ladder_E_Olimp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = 0;
        int j = 0;
        int max;
        int[] mas = new int[10000];
        int n = in.nextInt();
        for(i = 1; i <= n; i++) {
            mas[i] = in.nextInt();
        }
        int k = in.nextInt();
        mas[0]=0; //начальная ступенька
        mas[n+1]=0; //конечная ступенька

        for(i = 1; i <= n+1; i++){
            max = mas[j];
            for(j = i-k; j < i; j++){
                if(j < 0){
                    j = 0;
                }
                if(max < mas[j]) {
                    max = mas[j];
                }
            }
            mas[i] += max;
        }
        System.out.println(mas[n+1]);

    }
}

