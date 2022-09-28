package ex.test.bj;

import java.util.*;
import java.io.*;

public class BOJ_14500_테트로미노 {
	static int n, m, max;
	static int[][] map;
	static boolean[][] visited;
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {-1, 1, 0, 0};
	private static int ex[][] = {{0, 0, 0, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, -1, 0, 1}};
	private static int ey[][] = {{0, 1, 2, 1}, {0, 0, 0, 1}, {0, 1, 2, 1}, {0, 1, 1, 1}};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		map = new int[n][m];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visited[i][j] = true;
				dfs(i, j, map[i][j], 1);
				visited[i][j] = false;
				check(i, j);
			}
		}
		
		System.out.println(max);
	}
	
	static void dfs(int x, int y, int sum, int cnt) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}else {
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				dfs(nx, ny, sum + map[nx][ny], cnt + 1);
				visited[nx][ny] = false;
			
			}
		}
	}
	// ㅏ ㅓ ㅗ ㅜ 네번 검사
	static void check(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int sum_v = 0;
			for(int j = 0; j < 4; j++) {
				int nx = x + ex[i][j];
				int ny = y + ey[i][j];
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				sum_v += map[nx][ny];
			}
			max = Math.max(sum_v, max);
		}
	}
}
