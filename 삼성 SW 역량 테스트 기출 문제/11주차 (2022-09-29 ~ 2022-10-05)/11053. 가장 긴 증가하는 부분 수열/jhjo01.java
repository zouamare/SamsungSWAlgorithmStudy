package day1005;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11053_가장긴증가하는부분수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] data = new int[N];
		
		for(int i = 0; i < N; i++) {
			data[i] = sc.nextInt();			
		}
		
		int[] dp = new int[N];
		dp[N-1] = 1;
		int max = 1;
		
		for(int i = N-2; i >= 0 ; i--) {
			int cnt = 0;
			for(int j = i; j < N; j++) {
				if(dp[j] >= cnt && data[i] < data[j]) {
					cnt = dp[j] + 1;
				}
			}
			dp[i] = cnt == 0 ? 1 : cnt;
			max = Math.max(max, dp[i]);
			
			
//			System.out.println(Arrays.toString(data));
//			System.out.println(Arrays.toString(dp));
//			System.out.println();
		}
		
		System.out.println(max);
		
		
	}
}
