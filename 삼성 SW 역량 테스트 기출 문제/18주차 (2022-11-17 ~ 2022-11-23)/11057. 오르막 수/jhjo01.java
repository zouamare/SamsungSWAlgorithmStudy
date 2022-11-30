package BOJ;

import java.util.Scanner;

public class BOJ_11057_오르막수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        int[][] dp = new int[N+1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 1; i < N+1; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < 10; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 10007;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = answer + dp[N][i];
        }

        System.out.println(answer % 10007);
    }
}
