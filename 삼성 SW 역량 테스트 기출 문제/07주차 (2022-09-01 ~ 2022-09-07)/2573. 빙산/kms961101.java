package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	static int N, M, cnt, ans;
	static int[][] map;
	static int[][] temp;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				temp[i][j] = map[i][j];
			}
		}
		
		while(true) {
			// 처음에 두개이상인거 체크
			if(ans == 0){
				boolean[][] temp = new boolean[N][M];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if(map[i][j] == 0 || temp[i][j]) continue;
						dfs(i, j, temp);
						cnt++;
					}
				}
				if(cnt >= 2) {
					break;
				}
			}
			
			cnt = 0;
			ans++;
			melt();
			boolean flag = false;
			map = copy(temp);
			boolean[][] visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(!flag && map[i][j] != 0) flag = true;
					if(map[i][j] == 0 || visited[i][j]) continue;
					dfs(i, j, visited);
					cnt++;
				}
			}
			if(cnt >= 2) break;
			if(!flag) {
				ans = 0;
				break;
			}
		}
		
		System.out.println(ans);
	}
	private static int[][] copy(int[][] map) {
		int[][] temp = new int[N][];
		for (int i = 0; i < N; i++) temp[i] = map[i].clone();
		return temp;
	}
	private static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 0) continue;
			dfs(nx, ny, visited);
		}
	}
	private static void melt() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
					if(map[nx][ny] == 0 && temp[i][j] > 0) temp[i][j]--;
				}
			}
		}
	}
	
}
