package solo.study;

import java.util.Scanner;

public class BOJ_14889_스타트와링크 {
	static int n, ans;
	static int[][] map;
	static boolean[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		check = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		ans = Integer.MAX_VALUE;
		football(0 , 0);
		System.out.println(ans);
	}
	
	private static void football(int index, int cnt) {
		if(cnt == n / 2) {
			int start = 0;
			int link = 0;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(check[i] && check[j])
						start += map[i][j];
					if(!check[i] && !check[j])
						link += map[i][j];
				}
			}
			
			ans = Math.min(Math.abs(start - link), ans);
			return;
		}
		
		for(int i = index; i < n; i++) {
			if(!check[i]) {
				check[i] = true;
				football(i + 1, cnt + 1);
				check[i] = false;
			}
		}	
	}
}
