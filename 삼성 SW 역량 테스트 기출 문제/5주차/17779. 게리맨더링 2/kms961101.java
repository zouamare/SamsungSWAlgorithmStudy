package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779_게리맨더링2 {
	static int N, totalCnt, min = Integer.MAX_VALUE;
	static int[] temp;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				totalCnt += n;
			}
		}
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if(x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N) continue;
						solve(x, y, d1, d2);
					}
				}
			}
		}
		
		System.out.println(min);
	}
	
	private static void solve(int x, int y, int d1, int d2) {
		boolean[][] visited = new boolean[N][N];
		temp = new int[5];
		
		for (int i = 0; i <= d1; i++) {
			visited[x + i][y - i] = true;
			visited[x + d2 + i][y + d2 - i] = true;
		}
		
		for (int i = 0; i <= d2; i++) {
			visited[x + i][y + i] = true;
			visited[x + d1 + i][y - d1 + i] = true;
		}
		
		// 1
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if(visited[i][j]) break;
				temp[0] += map[i][j];
				
			}
		}
		// 2
		for (int i = 0; i <= x + d2; i++) {
			for (int j = N - 1; j > y; j--) {
				if(visited[i][j]) break;
				temp[1] += map[i][j];
			}
		}
		// 3
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if(visited[i][j]) break;
				temp[2] += map[i][j];
			}
		}
		// 4
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = N - 1; j >= y - d1 + d2; j--) {
				if(visited[i][j]) break;
				temp[3] += map[i][j];
			}
		}
		
		temp[4] = totalCnt;
		for (int i = 0; i < 4; i++) {
			temp[4] -= temp[i];
		}
		
		Arrays.sort(temp);
		min = Math.min(min, temp[4] - temp[0]);
	}
}
