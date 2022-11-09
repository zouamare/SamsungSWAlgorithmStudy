package ex.test.bj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_15685_드래곤커브 {
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	static int N;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[101][101];
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();
			ArrayList<Integer> list = new ArrayList<>();
			list.add(d);
			
			for(int j = 0; j < g; j++) {
				int size = list.size();
				for(int k = size - 1; k >= 0; k--) {
					list.add((list.get(k) + 1) % 4);
				}
			}
			
			map[y][x] = 1;
			for(int j = 0; j < list.size(); j++) {
				int dir = list.get(j);
				x = x + dx[dir];
				y = y + dy[dir];
				
				if(x >= 0 && y >= 0 && x < 101 && y < 101) {	
					map[y][x] = 1;
				}
			}
		}
		
		int res = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) res++;
			}
		}
		System.out.println(res);
	}
}
