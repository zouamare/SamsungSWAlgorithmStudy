import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int n, k;
	static int[][] port, incdec;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		port = new int[n][n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			port[0][i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 1;
		while (true) {
			simulation();
			int check = checkMinMax();
			if (check <= k) {
				System.out.println(cnt);
				return;
			}
			cnt++;
		}
	}
	
	static void simulation() {
		minPlus();
		change1();
		distribute();
		rearrange();
		change2();
		distribute();
		rearrange();
	}

	static void minPlus() {
		int min = port[0][0];
		for (int i = 1; i < n; i++) {
			min = Math.min(port[0][i], min);
		}
		
		for (int i = 0; i < n; i++) {
			if (port[0][i] == min)
				port[0][i]++;
		}
	}
	
	static void change1() {
		// st : 시작위치 -> W 만큼 증가
		int st = 1;
		int h = 1;
		int w = 1;
		
		// false : h 증가, true : w 증가
		boolean flag = false;	
		while (st + h - 1 < n) {
			for (int i = 0; i < h; i++) {
				for (int j = st - w; j < st; j++) {
					port[st - j][st + i] = port[i][j];
					port[i][j] = 0;					
				}
			}
			
			st += h;
			
			if (!flag) h++;
			else w++;
			
			flag = !flag;
		}
	}
	
	static void distribute() {
		boolean[][] visited = new boolean[n][n];
		incdec = new int[n][n];
		
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(0, n - 1));
		visited[0][n - 1] = true;
		
		while(!q.isEmpty()) {
			int y = q.peek().y;
			int x = q.poll().x;
			
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
				if (port[ny][nx] == 0) continue;
				
				int d = Math.abs(port[ny][nx] - port[y][x]) / 5;
				if (d > 0) {
					if (port[ny][nx] > port[y][x]) {
						incdec[ny][nx] -= d;
						incdec[y][x] += d;
					} else {
						incdec[ny][nx] += d;
						incdec[y][x] -= d;
					}
				}
				
				if (visited[ny][nx]) continue;
				visited[ny][nx] = true;
				q.add(new Point(ny, nx));
			}
		}
		
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				if (incdec[i][j] != 0)
					port[i][j] += incdec[i][j] / 2;
			}
		}
		
	}

	static void rearrange() {
		int idx = 0;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				if (port[i][j] != 0) {
					port[0][idx++] = port[i][j];
					if (i != 0)
						port[i][j] = 0;
				}
			}
		}
	}
	
	static void change2() {
		for (int j = 0; j < n / 2; j++) {
			port[1][j + n / 2] = port[0][n / 2 - j - 1];
			port[0][n / 2 - j - 1] = 0;
		}

		for (int j = 0; j < n / 4; j++) {
			port[2][j + 3 * n / 4] = port[1][3 * n / 4 - j - 1];
			port[1][3 * n / 4 - j - 1] = 0;
			
			port[3][j + 3 * n / 4] = port[0][3 * n / 4 - j - 1];
			port[0][3 * n / 4 - j - 1] = 0;
		}
	}
	
	static int checkMinMax() {
		int min = port[0][0];
		int max = port[0][0];
		
		for (int i = 1; i < n; i++) {
			max = Math.max(max, port[0][i]);
			min = Math.min(min, port[0][i]);
		}
		return max - min;
	}
}