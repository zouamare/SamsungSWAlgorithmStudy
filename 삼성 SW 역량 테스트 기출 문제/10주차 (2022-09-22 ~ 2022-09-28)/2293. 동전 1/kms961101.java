package algo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2293_동전1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] coin = new int[n + 1];
		int[] dp = new int[k + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			coin[i] = sc.nextInt();
			for (int j = coin[i]; j <= k; j++) {
				dp[j] = dp[j] + dp[j - coin[i]];
			}
		}
		System.out.println(dp[k]);
	}
}
