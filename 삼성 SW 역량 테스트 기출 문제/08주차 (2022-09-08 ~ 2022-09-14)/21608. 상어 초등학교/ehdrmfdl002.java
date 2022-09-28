package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_19236_상어초등학교 {

	static int n;
	static int[][] map;
	static int[][] seats;
	static int[] order;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int likecnt, emptycnt, r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		order = new int[n * n + 1];
		map = new int[n * n + 1][4];

		for (int i = 1; i <= n * n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			order[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				map[order[i]][j] = Integer.parseInt(st.nextToken());
			}
		}

		seats = new int[n][n];

		for (int i = 1; i <= n * n; i++) {
			likecnt = -1;
			emptycnt = -1;
			r = Integer.MAX_VALUE;
			c = Integer.MAX_VALUE;
			seat(order[i]);
		}

		int score = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (isIn(nx, ny) && contains(seats[nx][ny], map[seats[i][j]])) {
						cnt++;
					}
				}
				score += Math.pow(10, cnt - 1);
			}
		}
		System.out.println(score);
	}

	
	private static void seat(int x) {
		int like = 0;
		int empty = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (seats[i][j] != 0)
					continue;
				like = 0;
				empty = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (isIn(nx, ny)) {
						if (contains(seats[nx][ny], map[x])) {
							like++;
						} else if (seats[nx][ny] == 0) {
							empty++;
						}
					}
				}

				if (like > likecnt) {
					likecnt = like;
					emptycnt = empty;
					r = i;
					c = j;
				} else if (like == likecnt) {
					if (empty > emptycnt) {
						emptycnt = empty;
						r = i;
						c = j;
					} else if (empty == emptycnt) {
						if (i < r) {
							r = i;
							c = j;
						} else if (i == r) {
							c = Math.min(c, j);
						}
					}

				}
			}
		}

		seats[r][c] = x;
	}

	private static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

	private static boolean contains(int x, int[] students) {
		for (int i : students) {
			if (i == x) {
				return true;
			}
		}
		return false;
	}

}
