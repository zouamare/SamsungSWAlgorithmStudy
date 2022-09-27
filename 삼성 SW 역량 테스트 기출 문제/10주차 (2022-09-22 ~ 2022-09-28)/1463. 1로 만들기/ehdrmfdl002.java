package solo.study;

import java.util.Scanner;

public class BOJ_1463_1로만들기 {
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		solve(n, 0);
		System.out.println(ans);
	}

	private static void solve(int n, int cnt) {
		if(n == 1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(cnt >= ans)
			return;
		
		if(n % 3 == 0)
			solve(n / 3, cnt + 1);
		
		if(n % 2 == 0)
			solve(n / 2, cnt + 1);
		
		solve(n - 1, cnt + 1);
	}
}
