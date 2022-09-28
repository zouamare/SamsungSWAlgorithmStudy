package solo.study;

import java.util.Scanner;

public class BOJ_14888_연산자끼워넣기 {
	static int n, max, min;
	static int[] arr, cal;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		cal = new int[4];
		for(int i = 0; i < 4; i++) {
			cal[i] = sc.nextInt();
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		make(arr[0], 1);
		System.out.println(max);
		System.out.println(min);
	}
	private static void make(int num, int cnt) {
		if(cnt == n) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(cal[i] > 0) {
				cal[i]--;
				switch (i) {
				case 0:
					make(num + arr[cnt], cnt + 1);
					break;
				case 1:
					make(num - arr[cnt], cnt + 1);
					break;
				case 2:
					make(num * arr[cnt], cnt + 1);
					break;
				case 3:
					make(num / arr[cnt], cnt + 1);
					break;
				}
				cal[i]++;
			}
		}
	}
}
