package solo.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_17837_새로운게임2 {
	static class Point{
		int x, y, d;

		public Point(int x, int y, int d) { // 말의 좌표 + 이동방향
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
	}
	
	static int n, k, cnt = 0;;
	static int[][] map;
	static Point[] point;
	static ArrayList<Integer> list[][];
	static ArrayList<Integer> tmp = new ArrayList<>();
	static int dx[] = {0, 0, 0, -1, 1};
	static int dy[] = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		point = new Point[k + 1];
		map = new int[n + 1][n + 1];
		list = new ArrayList[n + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
				list[i][j] = new ArrayList<>();
			}
		}
				
		for(int i = 1; i <= k; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			list[r][c].add(i);
			point[i] = new Point(r, c, d);
		}
		
		
		while(cnt <= 1000) {
			cnt++;
			move();
		}
		
		System.out.println(-1);
	}

	private static void move() {  // 0 > 흰색, 1 > 빨간색, 2 > 파란색
		for(int i = 1; i <= k; i++) {
			Point p = point[i];
			int nx = p.x + dx[p.d];
			int ny = p.y + dy[p.d];
			if(!isIn(nx, ny) || map[nx][ny] == 2) { // 파란색 방향 반대로 하고 이동
				point[i].d = change(p.d);
				int tx = point[i].x + dx[point[i].d];
				int ty = point[i].y + dy[point[i].d];
				
				if(isIn(tx, ty) && map[tx][ty] != 2) {
					if(map[tx][ty] == 0) {
						delete(p.x, p.y, tx, ty, i, false);
					}
					else
						delete(p.x, p.y, tx, ty, i, true);
				}
			}
			
			else if(isIn(nx, ny) && map[nx][ny] == 0)
				delete(p.x, p.y, nx, ny, i, false);
			
			else if(isIn(nx, ny) && map[nx][ny] == 1)
				delete(p.x, p.y, nx, ny, i, true);
			
			for(int a = 1; a <= n; a++) {
				for(int b = 1; b <= n; b++) {
					if(list[a][b].size() >= 4) {
						System.out.println(cnt);
						System.exit(0);
					}
				}
			}
		}
		
	}
	
	private static void delete(int x, int y, int tx, int ty, int num, boolean check) {
		int size = list[x][y].size();
		boolean flag = true;
		tmp.clear();
		int dx = 0;
		
		for(int i = 0; i < size; i++) {
			if(list[x][y].get(i) == num) {
				dx = i;
				flag = false;
			}
			if(!flag) {
				tmp.add(list[x][y].get(i));
			}
		}
		
		if(check)
			Collections.reverse(tmp);
		
		for(int i = dx; i < size; i++)
			list[x][y].remove(dx);
		
		for(int i = 0; i < tmp.size(); i++) {
			point[tmp.get(i)].x = tx;
			point[tmp.get(i)].y = ty;
			list[tx][ty].add(tmp.get(i));
		}
		
	}

	private static int change(int d) {
		switch (d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return 5;
	}

	public static boolean isIn(int x, int y) {
        if(x >= 1 &&  x <= n && y >= 1 && y <= n)
            return true;
        return false;
    }
}
