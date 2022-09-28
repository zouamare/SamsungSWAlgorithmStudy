package solo.study;

import java.util.Scanner;

public class BOJ_14501_퇴사 {
	static int n, sum;
	static int[] day, money;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		day = new int[n + 1];
		money = new int[n + 1];
		
		for(int i = 0; i < n; i++) {
			day[i] = sc.nextInt();
			money[i] = sc.nextInt();
		}
		
		run(0, 0);
		System.out.println(sum);
	}

	private static void run(int d, int m) {
		if(d >= n) {
			sum = Math.max(sum, m);
			return;
		}
		
		if(d + day[d] <= n)
			run(d + day[d], m + money[d]);
		
		run(d + 1, m);
	}

}
