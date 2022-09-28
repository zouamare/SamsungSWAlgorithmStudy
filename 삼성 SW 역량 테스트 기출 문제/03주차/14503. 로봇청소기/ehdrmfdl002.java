package solo.study;

import java.util.Scanner;

public class BOJ_14503_로봇청소기 {
	static int n, m, r, c, d, cnt;
	static int[][] map;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		cnt = 1;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		clean(r, c, d);
		System.out.println(cnt);
	}
	
	private static void clean(int x, int y, int dir) {
		map[x][y] = 2;
		
		for(int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4;
			int rdx = x + dx[dir];
			int rdy = y + dy[dir];
			
			if(rdx >= 0 && rdx < n && rdy < m && rdy >= 0 && map[rdx][rdy] == 0) {
				cnt++;
				clean(rdx, rdy, dir);
				return;
			}
		}
			
		int back = (dir + 2) % 4;
		int bdx = x + dx[back];
		int bdy = y + dy[back];
		
		if(bdx >= 0 && bdy >= 0 && bdx < n && bdy < m && map[bdx][bdy] != 1) {
			clean(bdx, bdy, dir);
		}
	}
}
