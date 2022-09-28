package ex.test.bj;

import java.util.*;

public class BOJ_14501_퇴사 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n + 1][2];
		int[] dp = new int[n + 2];
		for(int i = 1; i < n + 1; i++) {
			arr[i][0] = sc.nextInt(); 
			arr[i][1] = sc.nextInt(); 
		}
		
		for(int i = n; i > 0; i--) {
			if(i + arr[i][0] <= n + 1) {
				dp[i] = Math.max(arr[i][1] + dp[i + arr[i][0]], dp[i + 1]);
			}else {
				dp[i] = dp[i + 1];
			}
		}
		
		System.out.println(dp[1]);
		
		
	}
}
