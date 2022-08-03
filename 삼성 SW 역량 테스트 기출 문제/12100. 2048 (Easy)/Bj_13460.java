package bj;

import java.util.*;
import java.io.*;

public class Bj_13460 {
	private static int dx[] = {0, 0, 1, -1};
	private static int dy[] = {1, -1, 0, 0};
	private static int[] idx;
	private static int n;
	private static int m;
	private static int idxy = 1;
	private static int idxx = 1;
	private static boolean[][] visited;
	private static char[][] map;
	private static int[] check;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		visited = new boolean[n][m];
		idx = new int[4];
		check = new int[2];
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'R') {
					idx[0] = i;
					idx[1] = j;
				}
				if(map[i][j] == 'B') {
					idx[2] = i;
					idx[3] = j;
				}
				if(map[i][j] == '#') {
					visited[i][j] = true;
				}
			}
		}
		idxx = idx[0];
		idxy = idx[1];
		dfs(idx[0], idx[1], 0);
		
		idxx = idx[2];
		idxy = idx[3];
		dfs(idx[0], idx[1], 1);
		
		if(check[0] > check[1]) {
			System.out.println(check[0] + 1);
		}else {
			System.out.println(-1);
		}
		
	}
	
	private static void dfs(int x, int y, int idx) {
		visited[x][y] = true;
		
		if(x == idxx || y == idxy) {
		}else {
			idxx = x;
			idxy = y;
			check[idx]++;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
				check(nx, ny);
				if(map[nx][ny] == '.' && !visited[nx][ny]) {
					dfs(nx, ny, idx);
				}
			}
			
		}
	}
	
	private static void check(int x, int y) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
				if(map[nx][ny] == '.') {
					break;
				}
			}
		}
	}

}
