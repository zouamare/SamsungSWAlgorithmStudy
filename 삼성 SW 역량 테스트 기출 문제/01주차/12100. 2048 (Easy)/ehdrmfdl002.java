package solo.study;

import java.util.Scanner;

public class ehdrmfdl002 {
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static int ans = 0;
	
	static void move(int[][] map, boolean[][]check, int dir, int a, int b) {
		int x = a, 
			y = b;
		int rdx = x + dx[dir], 
			rdy = y + dy[dir];
		if(rdx < 0 || rdx >= map.length || rdy < 0 || rdy >= map.length) {
			return;
		}
		
		boolean end = false;
		
		while(!end) {
			if(map[rdx][rdy] == 0) {
				map[rdx][rdy] = map[x][y];
				map[x][y] = 0;
				x = rdx;
				y = rdy;
				rdx = x + dx[dir];
				rdy = y + dy[dir];
				if(rdx < 0 || rdx >= map.length || rdy < 0 || rdy >= map.length){
					end = true;
				}
			}
			else if(map[rdx][rdy] == map[x][y]) {
				if(!check[rdx][rdy]) {
					map[rdx][rdy] = map[rdx][rdy] * 2;
					map[x][y] = 0;
					check[rdx][rdy] = true;
				}
				end = true;
			}
			else
				end = true;
		}
	}

	
	static void check(int index, int finish, int[][] map) {
		if(index == finish) {
			int tmp = 0;
			
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map.length; j++) {
					tmp = Math.max(tmp, map[i][j]);
				}
			}
			ans = Math.max(ans, tmp);
			return;
		}
		
		for(int dir = 0; dir < 4; dir++) {
			boolean[][] check = new boolean[map.length][map.length];
			
			int[][] tmpMap = new int [map.length][map.length];
			for(int a = 0; a < map.length; a++) {
				for(int b = 0; b < map.length; b++) {
					tmpMap[a][b] = map[a][b];
				}
			}
			
			if(dir == 0 || dir == 2) { //좌상단
				for(int a = 0; a < tmpMap.length; a++) {
					for(int b = 0; b < tmpMap[a].length; b++) {
						move(tmpMap, check, dir, a, b);
					}
				}
			} else if(dir == 1 | dir == 3) { //우하단
				for(int a = tmpMap.length - 1; a >= 0; a--) {
					for(int b = tmpMap[a].length; b >= 0; b--) {
						move(tmpMap, check, dir, a, b);
					}
				}
			}
			check(index+1, finish, tmpMap);
		}
	}
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int [][] map = new int[t][t];

		for(int i = 0; i < t; i++) {
			for(int j = 0; j < t; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		check(0, 5, map);
		System.out.println(ans);
	}
}
