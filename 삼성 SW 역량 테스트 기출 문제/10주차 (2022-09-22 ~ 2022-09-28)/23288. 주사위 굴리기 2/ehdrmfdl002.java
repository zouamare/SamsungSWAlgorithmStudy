package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_23288_주사위굴리기2 {
	
	// 위 - 뒤 - 우 - 좌 - 앞 - 아래
	public static int[] dice = { 1, 2, 3, 4, 5, 6 };

	public static int[] dx = { 0, 1, 0, -1 };
	public static int[] dy = { 1, 0, -1, 0 };
	public static int[][] map;
	public static int N, M, K, dir;
	public static int diceX, diceY;

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		map = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dir = 0;
		int score = 0;
		int rotate_cnt = 1;
		diceX = 1;
		diceY = 1;
		while (rotate_cnt++ <= K) {
			// 이동
			diceX += dx[dir];
			diceY += dy[dir];
			if (diceX < 1 || diceX > N || diceY < 1 || diceY > M) {
				diceX -= dx[dir];
				diceY -= dy[dir];
				// 반대 방향으로
				dir = (dir + 2) % 4;
				diceX += dx[dir];
				diceY += dy[dir];
			}

			change_dice();
			int cnt = bfs();
			score += (cnt * map[diceX][diceY]);

			// 이동방향 변경
			if (dice[5] > map[diceX][diceY]) {
				dir = (dir + 1) % 4;
			} else if (dice[5] < map[diceX][diceY]) {
				dir = (dir == 0 ? 3 : dir - 1);
			}
		}

		System.out.println(score);
	}

	public static void change_dice() {
		if (dir == 0) {
			int temp = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = temp;

		} else if (dir == 1) {
			int temp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = temp;
		} else if (dir == 2) {
			int temp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = temp;
		} else if (dir == 3) {
			int temp = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = temp;
		}
	}

	public static int bfs() {
		boolean[][] visited = new boolean[N + 1][M + 1];
		int cnt = 1;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(diceX, diceY));
		visited[diceX][diceY] = true;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];

				if (1 <= nx && nx <= N && 1 <= ny && ny <= M) {
					if (!visited[nx][ny] && map[nx][ny] == map[diceX][diceY]) {
						visited[nx][ny] = true;
						cnt++;
						q.offer(new Node(nx, ny));
					}
				}
			}
		}
		return cnt;
	}

}
