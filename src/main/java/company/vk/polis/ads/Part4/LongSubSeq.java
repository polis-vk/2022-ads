import java.util.Scanner;

public class LongSubSeq {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int[] amas = new int[a];
        for (int i = 0; i < a; i++) {
            amas[i] = in.nextInt();
        }

        int b = in.nextInt();
        int[] bmas = new int[b];
        for (int i = 0; i < b; i++) {
            bmas[i] = in.nextInt();
        }

        int[][] dp = new int[2*a][2*b];
        for (int i = 1; i <= a; i++) {
            for (int j = 1; j <= b; j++) {
                if(amas[i-1] == bmas[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[a][b]);
    }
}
