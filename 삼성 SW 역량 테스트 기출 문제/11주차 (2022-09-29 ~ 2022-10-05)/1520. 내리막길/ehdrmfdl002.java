package solo.study;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1520_내리막길 {
	static int n, m;
	static int map[][], dp[][];
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		dp = new int[n][m];
		
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				dp[i][j] = -1;
			}
		}
		
		move(0, 0);
		int ans = dp[0][0];
		System.out.println(ans);
	}
	
	private static int move(int x, int y) {
		if(x == n - 1 && y == m - 1) 
			return 1;
		
		else if(dp[x][y] != -1) 
			return dp[x][y];
		
		else {
			int count = 0;
			for(int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if(map[nx][ny] < map[x][y]) {
						count += move(nx, ny);
					}
				}
			}
			dp[x][y] = count;
			return dp[x][y];
		}
		
	}
	
}
