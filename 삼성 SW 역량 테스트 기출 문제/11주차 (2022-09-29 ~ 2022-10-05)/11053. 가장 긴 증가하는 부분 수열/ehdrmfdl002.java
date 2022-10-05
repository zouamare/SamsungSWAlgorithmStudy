package solo.study;

import java.util.Scanner;

public class BOJ_11503_가장긴증가하는부분수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int arr[] = new int[n];
		int dp[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		dp[0] = 1;
		int ans = 1;
		
		for(int i = 1; i < n; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && dp[i] <= dp[j])
					dp[i] = dp[j] + 1;
			}
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
	}

}
