package solo.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_14502_연구소 {
	
	static int n, m, sum, x, y;
	static int[][] map, tmpmap;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static List<Integer> xpoint = new ArrayList<>();
	static List<Integer> ypoint = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		tmpmap = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = tmpmap[i][j] = sc.nextInt();
				if(map[i][j] == 2) {
					xpoint.add(i);
					ypoint.add(j);
				}	
			}
		}
		
		wall(0, 0);
		System.out.println(sum);
	}
	
	private static void virus() {
		for(int i = 0; i < xpoint.size(); i++) {
			for(int j = 0; j < 4; j++) {
				int rdx = xpoint.get(i) + dx[j];
				int rdy = ypoint.get(i) + dy[j];
				
				if(rdx >= 0 && rdx < n && rdy >= 0 && rdy < n) {
					if(tmpmap[rdx][rdy] == 0) {
						tmpmap[rdx][rdy] = 2;
					}
				}
			}
		}
	}
	
	private static void wall(int start, int cnt) {
		int num = 0;
		if(cnt == 3) {
			virus();
			for(int i = 0; i < n; i++) {
				for(int j = 0; i < m; j++) {
					if(tmpmap[i][j] == 0) {
						num++;
					}
	 			}
			}
			sum = Math.max(sum, num);
			return;
		}
		
		for(int i = start; i < n * m; i++) {
			int x = i / m;
			int y = i % m;
			if(tmpmap[x][y] == 0) {
				tmpmap[x][y] = 1;
				wall(i + 1, cnt + 1);
				tmpmap[x][y] = 0;
			}	
		}
	}
}
