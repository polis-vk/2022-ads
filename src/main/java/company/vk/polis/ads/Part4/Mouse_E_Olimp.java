import java.util.Scanner;

public class Mouse_E_Olimp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder ans = new StringBuilder();
        int n=in.nextInt();
        int m=in.nextInt();
        int[][] dp = new int[100][100];
        for (int i=n-1; i>=0; i--){
            for (int j=0; j<m; j++) {
                dp[i][j]=in.nextInt();
            }
        }

        for(int i=1;i<n;i++){
            dp[i][0]=dp[i][0]+dp[i-1][0];
        }
        for(int j=1;j<m;j++){
            dp[0][j]=dp[0][j]+dp[0][j-1];
        }
        for (int i=1; i<n; i++){
            for (int j=1; j<m; j++) {
                dp[i][j]=dp[i][j]+Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int k=n-1, t=m-1;
        while (k>0 || t>0){
            if (k>0 && t>0){
                if (dp[k-1][t]>dp[k][t-1]){
                    ans.append("F");
                    k--;
                }
                else{
                    ans.append("R");
                    t--;
                }
            }
            else if (k==0){
                ans.append("R");
                t--;
            }
            else if (t==0){
                ans.append("F");
                k--;
            }
        }
        System.out.println(ans.reverse());
    }
}
