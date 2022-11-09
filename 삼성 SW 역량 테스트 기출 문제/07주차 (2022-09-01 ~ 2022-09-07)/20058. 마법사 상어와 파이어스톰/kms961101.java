package ex.test.bj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_20058_마법사상어와파이어볼 {
	static int N, Q, L;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Q = sc.nextInt();
		N = (int) Math.pow(2, N);
		map = new int[N][N];
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] != 0) flag = true;
			}
		}
		
		if(!flag) {
			System.out.println(0);
		}else {
			for (int k = 0; k < Q; k++) {
				L = (int) Math.pow(2, sc.nextInt());
				for (int i = 0; i < N; i += L) {
					for (int j = 0; j < N; j += L) {
						// i ~ i+L-1 , j ~ j+L-1 회전하기
						move(i, j);
					}
				}
				melt();
			}
			
			int sum = 0;
			int max = Integer.MIN_VALUE;
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += map[i][j];
					if(!visited[i][j] && map[i][j] > 0) {
						max = Math.max(max, bfs(i, j));
					}
				}
			}
			
			System.out.println(sum);
			System.out.println(max);
		}
	}
	
	private static void move(int x, int y) {
		int[][] temp = new int[L][L];
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				temp[i][j] = map[x + L -1 -j][y + i];
			}
		}
		
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				map[x + i][y + j] = temp[i][j];
			}
		}
	}

	private static void melt() {
		int[][] temp = copy(map);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(temp[i][j] == 0) continue;
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					if(x < 0 || y < 0 || x >= N || y >= N ) continue;
					if(map[x][y] > 0) cnt++;
				}
				
				if(cnt < 3) temp[i][j]--;
			}
		}
		map = temp;
	}

	private static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = tmp[0] + dx[i];
				int ny = tmp[1] + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(!visited[nx][ny] && map[nx][ny] > 0) {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					cnt++;
					
				}
			}
		}
		return cnt;
	}
	
	private static int[][] copy(int[][] map) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) temp[i] = map[i].clone();
		return temp;
	}
}
