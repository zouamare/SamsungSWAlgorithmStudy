package solo.study;

import java.util.Scanner;

public class BOJ_11057_오르막수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[][] dp = new int[1001][10];  // dp[자릿수][숫자 가장 앞자리 수]
		
		dp[1][0] = 1;  // 1자리수, 0으로 시작
		dp[1][1] = 1;  // 1자리수, 1로 시작
		dp[1][2] = 1;
		dp[1][3] = 1;
		dp[1][4] = 1;
		dp[1][5] = 1;
		dp[1][6] = 1;
		dp[1][7] = 1;
		dp[1][8] = 1;
		dp[1][9] = 1;
		
		for (int i = 2; i < n + 1; i++) {
			// 9로 시작하는 오르막 수는 항상 1가지
			dp[i][9] = 1;  
			for (int j = 8; j >= 0; j--) {
				dp[i][j] = dp[i - 1][j] + dp[i][j + 1] % 10007;
			}
		}
		
		int sum = 0;
		
		// n자리수의 i로 시작하는 오르막 수를 모두 더함
		for (int i = 0; i < 10; i++) {  
			sum += dp[n][i];
		}
		
		System.out.println(sum % 10007);
	}

}
