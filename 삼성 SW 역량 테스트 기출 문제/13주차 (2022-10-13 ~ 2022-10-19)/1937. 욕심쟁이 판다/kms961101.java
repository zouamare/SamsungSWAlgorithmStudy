package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1937_욕심쟁이판다 {
	static int N;
	static int[][] map;
	static int[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(visited[i], -1);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, dfs(i, j));
			}
		}
		System.out.println(ans);
	}
	
	private static int dfs(int x, int y) {
		if(visited[x][y] != -1) return visited[x][y];
		visited[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int kx = x + dx[i];
			int ky = y + dy[i];
			if(kx < 0 || ky < 0 || kx >= N || ky >= N) continue;
			if(map[kx][ky] > map[x][y]) {
				if(1 + dfs(kx, ky) > visited[x][y]) visited[x][y] = 1 + dfs(kx, ky);
			}
		}
		return visited[x][y];
	}
}
