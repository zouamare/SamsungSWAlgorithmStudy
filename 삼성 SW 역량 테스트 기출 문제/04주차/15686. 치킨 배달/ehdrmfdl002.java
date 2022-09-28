package solo.study;

import java.util.Scanner;

public class BOJ_15686_치킨배달 {
	static int[][] map, home, chick, point;
	static int index1, index2, len;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		point = new int[m][2];
		map = new int[n][n];
		home = new int[n * 2][2];
		chick = new int[13][2];
		index1 = 0;
		index2 = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0 ; j < n; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					home[index1][0] = i;
					home[index1][1] = j;
					index1++;
				}
				if(map[i][j] == 2) {
					chick[index2][0] = i;
					chick[index2][1] = j;
					index2++;
				}
			}
		}
		
		move(0, 0);
		System.out.println(ans);
	}
	
	private static void move(int cnt, int start) {
		if(cnt == point.length) {
			int sum = 0;
			for(int i = 0; i < index1; i++) {
				len = Integer.MAX_VALUE;
				for(int j = 0; j < point.length; j++) {
					len = Math.min(len, Math.abs(home[i][0] - point[j][0]) + Math.abs(home[i][1] - point[j][1]));
				}
				sum += len;
			}
			ans = Math.min(ans, sum);
			return;
		}
		
		if(start == index2)
			return;
		point[cnt][0] = chick[start][0];
		point[cnt][1] = chick[start][1];
		move(cnt + 1, start + 1);
		move(cnt, start + 1);
		
	}
}
