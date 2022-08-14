package solo.study;

import java.util.*;
import java.util.Scanner;

public class BOJ_16234_인구이동 {
	static int n, l, r, ans;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		l = sc.nextInt();
		r = sc.nextInt();
		
		map = new int[n][n];
		check = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		while(true) {
			boolean team = false; 
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!check[i][j] && find(i, j)) // 방문 X, 연합 O
						team = true;
				}
			}
			
			if(!team) break;
			
			ans++;
			
			for(int i = 0; i < n; i++) { // 방문 초기화
				for(int j = 0; j < n; j++) {
					check[i][j] = false;
				}
			}
		}
		
		System.out.println(ans);
	}

	private static boolean find(int i, int j) {
		Queue<Point> q = new LinkedList<>(); // 방문할 좌표
		List<Point> list = new ArrayList<>(); // 같은 연합
		int sum = map[i][j]; // 인구수 합계
		check[i][j] = true;
		
		q.add(new Point(i, j));
		list.add(new Point(i, j));
		
		while(!q.isEmpty()) {
			Point num = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int rdx = num.x + dx[d];
				int rdy = num.y + dy[d];
				
				if(rdx < 0 || rdx >= n || rdy < 0 || rdy >= n || check[rdx][rdy])
					continue;
				
				int isOk = Math.abs(map[num.x][num.y] - map[rdx][rdy]);
				
				if(isOk >= l && isOk <= r) {
					check[rdx][rdy] = true;
					q.add(new Point(rdx, rdy));
					list.add(new Point(rdx, rdy));
					sum += map[rdx][rdy];
				}
			}
		}
		
		if(list.size() == 1) // 연합이 없으면 리턴
			return false;
		
		for(int k = 0; k < list.size(); k++) { // 연합인 나라 인구수 평균으로 만들기
			Point p = list.get(k);
			map[p.x][p.y] = sum/list.size();
		}
		
		return true;
	}
}
