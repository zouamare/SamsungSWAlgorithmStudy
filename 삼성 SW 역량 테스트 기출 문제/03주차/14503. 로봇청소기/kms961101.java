package ex.test.bj;

import java.util.*;
import java.io.*;

public class BOJ_14503_로봇청소기 {
	static int n, m, x, y, dir;
	static int cnt = 1;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(x, y, dir);		
		System.out.println(cnt);
	}
	
	private static void dfs(int x, int y, int dir) {
		map[x][y] = 2;
		for(int i = 0; i < 4; i++) {
			dir -= 1; //왼쪽으로 계속 돌기때문에 방향 -1
			if(dir == -1) dir = 3; //북쪽에서 서쪽으로 이동
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			if(map[nx][ny] == 0) {
				cnt++;
				dfs(nx, ny, dir);
				return;
			}
		}
		//후진
		int d = (dir + 2) % 4;
		int bx = x + dx[d];
		int by = y + dy[d];
		if(bx >=0 && by >= 0 && bx < n && by < m && map[bx][by] != 1) dfs(bx, by, dir);
		
	}
	
}
