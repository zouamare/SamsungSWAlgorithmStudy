package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23288_주사위굴리기2 {
	static int N, M, K, dir, x, y, score, cnt, num;
	static int[][] map;
	static boolean[][] visited;
	// 윗면, 바닥면, 앞면, 뒷면, 왼쪽면, 오른쪽면
	static int[] dice = {1, 6, 5, 2, 4, 3};
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dir = 0;
		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		x = 1;
		y = 1;
		while(K-- > 0) {
			// dir = 0 동 1 남 2 서 3 북
			switch(dir) {
			case 0:
				if(y + 1 == M + 1) {
					dir = 2;
					y--;
				}else y++;
				if(dir == 0) move_right();
				else if(dir == 2) move_left();
				break;
			case 1:
				if(x + 1 == N + 1) {
					dir = 3;
					x--;
				} else x++;
				if(dir == 1) move_down();
				else if(dir == 3) move_up();
				break;
			case 2:
				if(y - 1 == 0) {
					dir = 0;
					y++;
				}else y--;
				if(dir == 2) move_left();
				else if(dir == 0) move_right();
				break;
			case 3:
				if(x - 1 == 0) {
					dir = 1;
					x++;
				} else x--;
				if(dir == 3) move_up();
				else if(dir == 1) move_down();
				break;
			}
			num = dice[1];
			if(map[x][y] < num) {
				dir = (dir + 1) % 4;
			}else if(map[x][y] > num) {
				dir = (dir - 1) < 0 ? 3: dir - 1;
			}
			cnt = 1;
			visited = new boolean[N + 1][M + 1];
			visited[x][y] = true;
			dfs(x, y);
			score += map[x][y] * cnt;
		}
		System.out.println(score);
	}
	
	private static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nx = r + dx[i];
			int ny = c + dy[i];
			if(nx < 1 || ny < 1 || nx > N || ny > M) continue;
			if(map[nx][ny] == map[x][y] && !visited[nx][ny]) {
				cnt++;
				visited[nx][ny] = true;
				dfs(nx, ny);
			}
		}
	}

	public static void move_right() {
		// 윗 -> 오 -> 바닥 -> 왼 -> 윗
		int temp = dice[0];
		dice[0] = dice[4];
		dice[4] = dice[1];
		dice[1] = dice[5];
		dice[5] = temp;
		
	}
	
	public static void move_left() {
		// 윗 -> 왼 -> 바 -> 오 -> 윗
		int temp = dice[0];
		dice[0] = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[4];
		dice[4] = temp;
		
	}
	
	public static void move_up() {
		// 윗 -> 뒷 -> 바 -> 앞 -> 윗
		int temp = dice[0];
		dice[0] = dice[2];
		dice[2] = dice[1];
		dice[1] = dice[3];
		dice[3] = temp;
		
	}
	
	public static void move_down() {
		// 윗 -> 앞 -> 바 -> 뒷 -> 윗
		int temp = dice[0];
		dice[0] = dice[3];
		dice[3] = dice[1];
		dice[1] = dice[2];
		dice[2] = temp;
		
	}
}
