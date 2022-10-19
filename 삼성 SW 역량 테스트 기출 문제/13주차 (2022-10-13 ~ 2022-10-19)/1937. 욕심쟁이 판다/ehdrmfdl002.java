package solo.study;

import java.util.Scanner;

public class BOJ_1937_판다 {
	static int n, ans;
	static int[][] map, copy;
	static boolean[][] check;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		map = new int[n][n];
		copy = new int[n][n];
		check = new boolean[n][n];
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				dfs(i, j, map[i][j]);
			}
		}
		
		System.out.println(ans);
	}
	
	
	private static void dfs(int x, int y, int cnt) {
		int max = 0;
		check[x][y] = true;
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= n || ny >= n)
				continue;
			if(!check[nx][ny] && map[nx][ny] > cnt)
				dfs(nx, ny, map[nx][ny]);
			
			if(cnt < map[nx][ny])
				max = Math.max(max, copy[nx][ny]);
		}
		
		copy[x][y] = max + 1;
		ans = Math.max(ans, copy[x][y]);
	}
}	
