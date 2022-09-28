package algo;

import java.util.Scanner;

public class BOJ_1463_1로만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[1000001];
		dp[2] = 1;
		dp[3] = 1;
		int num = 3;
		while(num++ < N) {
			int one = Integer.MAX_VALUE;
			int two = Integer.MAX_VALUE;
			int three = Integer.MAX_VALUE;
			int ans;
			if(num % 2 == 0) two = dp[num / 2] + 1;
			if(num % 3 == 0) three = dp[num / 3] + 1;
			one = dp[num - 1] + 1;
			ans = Math.min(one, two);
			ans = Math.min(ans, three);
			dp[num] = ans;
		}
		System.out.println(dp[N]);
	}
}
