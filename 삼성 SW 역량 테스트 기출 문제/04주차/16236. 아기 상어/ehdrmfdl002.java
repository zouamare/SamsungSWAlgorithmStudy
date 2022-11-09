package solo.study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16236_아기상어 {
	
	static int n, sx, sy, minx, miny, minlen, eat, time;
	static int size = 2;
	static int[][] map, len;
	static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) {
					sx = i;
					sy = j;
					map[i][j] = 0;
				}
			}
		}
		
		
		while(true) {
			len = new int[n][n];
			minx = Integer.MAX_VALUE;
			miny = Integer.MAX_VALUE;
			minlen = Integer.MAX_VALUE;
			
			checkLen(sx, sy);
			if(eat())
				break;
		}
		
		System.out.println(time);
		
	}

	private static boolean eat() {
		if(minx != Integer.MAX_VALUE && miny != Integer.MAX_VALUE) {
			eat++;
			map[minx][miny] = 0;
			sx = minx;
			sy = miny;
			time += len[minx][miny];
			
			if(eat == size) {
				size++;
				eat = 0;
			}
			
			return false;
		}
		
		return true;
	}

	private static void checkLen(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] point = q.remove();
			
			for(int i = 0; i < 4; i++) {
				int nx = point[0] + dx[i];
				int ny = point[1] + dy[i];
				
				if(nx < 0 || ny < 0 || n <= nx || n <= ny || map[nx][ny] > size || len[nx][ny] != 0) continue;
				
				len[nx][ny] = len[point[0]][point[1]] + 1;
				
				if(map[nx][ny] != 0 && map[nx][ny] < size) {
					if(minlen > len[nx][ny]) {
						minlen = len[nx][ny];
						minx = nx;
						miny = ny;
					}
					else if(minlen == len[nx][ny]) {
						if(minx == nx)
							miny = Math.min(miny, ny);
						else if(minx > nx) {
							minx = nx;
							miny = ny;
						}
					}
				}
				
				q.add(new int[] {nx, ny});
			}
		}
		
	}
}
