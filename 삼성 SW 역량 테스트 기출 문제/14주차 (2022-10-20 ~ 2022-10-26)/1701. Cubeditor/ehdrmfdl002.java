package solo.study;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_5558_치즈 {
	static class Point {
		int x, y, cheese;

		public Point(int x, int y, int cheese) {
			super();
			this.x = x;
			this.y = y;
			this.cheese = cheese;
		}
	}

	static int h, w, n, time;
	static char map[][];
	static boolean check[][][];
	static boolean cheeseCheck[];
	static Queue<Point> q = new ArrayDeque<>();
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		n = sc.nextInt();

		map = new char[h][w];
		check = new boolean[h][w][n + 2];
		cheeseCheck = new boolean[n + 1];

		for (int i = 0; i < h; i++) {
			String str = sc.next();
			for (int j = 0; j < w; j++) {
				map[i][j] = str.charAt(j);
				if (str.charAt(j) == 'S')
					q.add(new Point(i, j, 1));
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point tmp = q.poll();
				if (tmp.cheese == n + 1)
					return time; 
				for (int d = 0; d < 4; d++) {
					int nx = tmp.x + dx[d];
					int ny = tmp.y + dy[d];
					
					if (isIn(nx, ny) && !check[nx][ny][tmp.cheese] && map[nx][ny] != 'X') { 
						if (Character.isDigit(map[nx][ny]) && map[nx][ny] - '0' <= tmp.cheese && !cheeseCheck[map[nx][ny] - '0']) { 
							cheeseCheck[map[nx][ny] - '0'] = true; 
							q.offer(new Point(nx, ny, tmp.cheese + 1)); 
						} 
						else
							q.offer(new Point(nx, ny, tmp.cheese)); 
						
						check[nx][ny][tmp.cheese] = true;
					}
				}
			}
			time++;
		}
		return 0;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && x < h && y >= 0 && y < w;
	}

}
