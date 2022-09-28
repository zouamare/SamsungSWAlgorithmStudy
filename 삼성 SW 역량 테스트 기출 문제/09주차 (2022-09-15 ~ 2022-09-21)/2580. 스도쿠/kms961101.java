package algo;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2580_스도쿠 {
	static int n = 9;
	static int[][] map = new int[n][n];
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		solve(0, 0);
	}
	private static void solve(int x, int y) {
		// 끝까지 갔으므로 출력 후 종료
		if(x == 9) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);;
		}
		// 가로 한줄 다 갔으니 다음줄로 넘기기
		if(y == 9) {
			solve(x + 1, 0);
			return;
		}
		// 가로 세로 네모 확인
		if(map[x][y] == 0) {
			for (int i = 1; i <= n; i++) {
				if(check(x, y, i)) {
					map[x][y] = i;
					solve(x, y + 1);
				}
			}
			map[x][y] = 0;
			return;
		}
		solve(x, y + 1);
	}
	
	private static boolean check(int x, int y, int num) {
		for (int i = 0; i < n; i++) {
			if(map[x][i] == num) return false;
		}
		
		for (int i = 0; i < n; i++) {
			if(map[i][y] == num) return false;
		}
		
		int dx = (x / 3) * 3;
		int dy = (y / 3) * 3;
		for (int i = dx; i < dx + 3; i++) {
			for (int j = dy; j < dy + 3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		return true;
	}
}
