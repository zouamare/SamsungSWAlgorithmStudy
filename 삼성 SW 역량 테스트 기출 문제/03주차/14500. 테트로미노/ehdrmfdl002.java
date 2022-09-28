package solo.study;

import java.util.Scanner;

public class BOJ_14500_테트로미노 {
	static int[][][] box = {
			{{0, 0}, {0, 1}, {1, 0}, {1, 1}},
			{{0, 0}, {0, 1}, {0, 2}, {1, 1}}, {{0, 0}, {1, 0}, {2, 0}, {1, 1}}, {{1, 0}, {0, 1}, {1, 1}, {1, 2}}, {{1, 0}, {0, 1}, {1, 1}, {2, 1}},
			{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
			{{0, 0}, {0, 1}, {0, 2}, {1, 2}}, {{1, 0}, {1, 1}, {1, 2}, {0, 2}}, {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{2, 0}, {0, 1}, {1, 1}, {2, 1}}, {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, {{0, 0}, {1, 0}, {0, 1}, {2, 0}}, {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
			{{0, 0}, {1, 0}, {1, 1}, {2, 1}}, {{1, 0}, {1, 1}, {0, 1}, {0, 2}}, {{0, 0}, {0, 1}, {1, 1}, {1, 2}}, {{0, 1}, {1, 1}, {1, 0}, {2, 0}}
		};
	static int[][] map;
	static int n, m, max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				techno(i, j);
			}
		}
		
		System.out.println(max);
		
	}
	
	private static void techno(int i, int j) {
		for(int a = 0; a < box.length; a++) {
			int cnt = 0;
			for(int b = 0; b < 4; b++) {
				int di = i + box[a][b][0];
				int dj = j + box[a][b][1];
				if(di < n && dj < m) {
					cnt += map[di][dj];
				}
				else break;
			}
			
			max = Math.max(cnt, max);
		}
	}
}
