package solo.study;


import java.io.*;
import java.util.*;

public class BOJ_23291_어항정리{
	private static int[][] map;
	private static int N, K;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[N + 1][N + 1];

		for (int i = 0; i < N; i++)
			map[0][i] = sc.nextInt();

		int ans = 0;

		while (!slove()) {
			addMin();
			move1();
			add();
			sort();
			fold();
			add();
			sort();
			ans += 1;
		}

		System.out.println(ans);
	}

	private static void addMin() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++)
			min = Math.min(min, map[0][i]);
		for (int i = 0; i < N; i++)
			if (map[0][i] == min)
				map[0][i] += 1;
	}

	private static boolean slove() {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}
		return max - min <= K;
	}


	private static void move1() {
		// 짝수일 때는 width가 늘어나고, 홀수일 땐 height가 늘어남
		int width = 1, height = 1, start = 1; 
		int index = 0; 

		while (start + height - 1 < N) {
			index += 1;
			for (int i = 0; i < height; i++) {
				for (int j = start - width; j < start; j++) {
					map[start - j][start + i] = map[i][j];
					map[i][j] = 0;
				}
			}
			start += height;

			if (index % 2 == 0)
				width += 1;
			else
				height += 1;
		}
	}

	private static void add() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				div(temp, i, j, i + 1, j);
				div(temp, i, j, i, j + 1);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += temp[i][j];
			}
		}
	}

	private static void sort() {
		int[] temp = new int[N];
		int idx = 0;

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (map[y][x] != 0) {
					temp[idx++] = map[y][x];
					map[y][x] = 0;
				}
			}
		}

		for (int i = 0; i < N; i++)
			map[0][i] = temp[i];
	}

	private static void fold() {
		for (int i = 0; i < N / 4; i++) {
			map[1][N - 1 - i] = map[0][i];
			map[0][i] = 0;
		}

		for (int i = 0; i < N / 4; i++) {
			map[2][N / 4 * 3 + i] = map[0][N / 4 + i];
			map[0][N / 4 + i] = 0;
		}

		for (int i = 0; i < N / 4; i++) {
			map[3][N - 1 - i] = map[0][N / 2 + i];
			map[0][N / 2 + i] = 0;
		}
	}

	private static void div(int[][] temp, int x1, int y1, int x2, int y2) {
		if (map[x1][y1] == 0)
			return;
		if (map[x2][y2] == 0)
			return;

		int value = Math.abs(map[x1][y1] - map[x2][y2]) / 5;
		if (map[x1][y1] > map[x2][y2]) {
			temp[x1][y1] -= value;
			temp[x2][y2] += value;
		} else {
			temp[x2][y2] -= value;
			temp[x1][y1] += value;
		}
	}
}
