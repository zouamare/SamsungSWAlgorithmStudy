package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_21609_상어중학교 {

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static List<int[]> blockGroup, biggestBlockGroup;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		int score = 0;
		while (findBiggestBlockGroup()) {
			score += removeBlockGroup(biggestBlockGroup);
			gravity();
			rotate();
			gravity();
		}

		System.out.println(score);
	}

	private static void rotate() {
		int[][] rotatemap = new int[n][n];

		for (int j = n - 1; j >= 0; j--) {
			for (int i = 0; i < n; i++) {
				rotatemap[n - j - 1][i] = map[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			map[i] = rotatemap[i].clone();
		}
	}

	private static void gravity() {
		for (int j = 0; j < n; j++) {
			int emptyCnt = 0;
			int blockCnt = 0;
			List<Integer> blocks = new ArrayList<>();
			int i = n - 1;
			for (; i >= 0; i--) {
				if (map[i][j] == -2) { // 빈 칸
					emptyCnt++;
					// blockCnt = 0;
				} else if (map[i][j] == -1) { // -1은 중력 영향 X
					if (emptyCnt != 0) {
						for (; blockCnt > 0; blockCnt--) {
							map[i + blockCnt + emptyCnt][j] = blocks.get(blocks.size() - blockCnt);
						}

						for (; emptyCnt > 0; emptyCnt--) {
							map[i + emptyCnt][j] = -2;
						}
					}
					blockCnt = 0;
					emptyCnt = 0;
					blocks.clear();
				} else {
					blocks.add(map[i][j]);
					blockCnt++;
				}
			}

			if (emptyCnt != 0) { // 빈 칸이 있으면
				for (; blockCnt > 0; blockCnt--) {
					map[i + blockCnt + emptyCnt][j] = blocks.get(blocks.size() - blockCnt);
					map[i + blockCnt][j] = -2;
				}

				for (; emptyCnt > 0; emptyCnt--) {
					map[i + emptyCnt][j] = -2;
				}
			}

		}
	}

	private static int removeBlockGroup(List<int[]> group) {
		for (int[] point : group) {
			map[point[0]][point[1]] = -2; // 빈 칸
		}
		return group.size() * group.size();
	}

	private static boolean findBiggestBlockGroup() {
		biggestBlockGroup = new ArrayList<>();
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] >= 1 && map[i][j] <= m && !visited[i][j]) {
					blockGroup = new ArrayList<>();

					dfs(i, j, map[i][j]);

					if (biggestBlockGroup.size() < blockGroup.size()) {
						biggestBlockGroup = blockGroup;
					} else if (biggestBlockGroup.size() == blockGroup.size()) {
						if (compareBlockGroup(biggestBlockGroup, blockGroup) == 2) {
							biggestBlockGroup = blockGroup;
						}
					}
					initVisited();
				}
			}
		}

		if (biggestBlockGroup.size() != 0 && biggestBlockGroup.size() != 1) {
			return true;
		} else {
			return false;
		}
	}

	private static int compareBlockGroup(List<int[]> group1, List<int[]> group2) {
		int[] standard1 = getStandard(group1);
		int[] standard2 = getStandard(group2);

		if (standard1[0] != standard2[0]) {
			if (standard1[0] > standard2[0]) {
				return 1;
			} else {
				return 2;
			}
		} else {
			if (standard1[1] != standard2[1]) {
				if (standard1[1] > standard2[1]) {
					return 1;
				} else {
					return 2;
				}
			} else {
				if (standard1[2] > standard2[2]) {
					return 1;
				} else {
					return 2;
				}
			}
		}
	}

	private static int[] getStandard(List<int[]> group) {
		int rainbowBlockCnt = 0;
		int row = Integer.MAX_VALUE;
		int col = Integer.MAX_VALUE;

		for (int[] point : group) {
			if (map[point[0]][point[1]] == 0) {
				rainbowBlockCnt++;
			} else {
				if (row > point[0]) {
					row = point[0];
					col = point[1];
				} else if (row == point[0]) {
					col = Math.min(col, point[1]);
				}
			}
		}
		return new int[] {rainbowBlockCnt, row, col};
	}

	private static void dfs(int x, int y, int color) {
		visited[x][y] = true;
		blockGroup.add(new int[] { x, y });

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isIn(nx, ny) && (map[nx][ny] == 0 || map[nx][ny] == color) && !visited[nx][ny]) {
				dfs(nx, ny, color);
			}
		}
	}

	private static void initVisited() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0 || map[i][j] == -1) {
					visited[i][j] = false;
				}
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
}
