package ex.test.bj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17140_이차원배열과연산 {
	static class Point implements Comparable<Point>{
		int num, cnt;

		public Point(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		public int getCnt() {
			return cnt;
		}

		public void setCnt(int cnt) {
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			if(this.cnt == o.cnt) return this.num - o.num;
			else return this.cnt - o.cnt;
		}

	}
	
	static int r, c, k, x, y, ans, maxX, maxY;
	static int map[][];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt() - 1;
		c = sc.nextInt() - 1;
		k = sc.nextInt();
		x = 3;
		y = 3;
		
		map = new int[x][y];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int count = 101;
		while(count-- > 0) {
			if(r < x && c < y) {
				if(map[r][c] == k) break;
			}
			if(x >= y) Rcalculation();
			else Ccalculation();
			ans++;
		}
		
		if(ans == 101) System.out.println(-1);
		else System.out.println(ans);
	}
	
	private static void Rcalculation() {
		// 세로 크기 증가
		maxY = 0;
		ArrayList<Point>[] list = new ArrayList[x];
		// 각 줄마다 개수를 넣어주기 위해 생성
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < x; i++) {
			list[i] = new ArrayList<>();
			boolean[] visited = new boolean[101];
			
			for (int j = 0; j < y; j++) {
				if(map[i][j] == 0) continue;
				if(!visited[map[i][j]]) {
					visited[map[i][j]] = true;
					list[i].add(new Point(map[i][j] , 1));
				}else {
					for (int k = 0; k < list[i].size(); k++) {
						if(list[i].get(k).num == map[i][j]) {
							list[i].get(k).setCnt(list[i].get(k).getCnt() + 1);
						}
					}
				}
			}
			Collections.sort(list[i]);
			q.offer(list[i].size());
			maxY = Math.max(maxY, list[i].size() * 2);
			if(maxY > 100) maxY = 100;
		}
		if(maxY < 3)  maxY = 3;
		else y = maxY;
		RArrayCopy(list, q);
		
	}
	
	private static void Ccalculation() {
		// 세로 크기 증가
		maxX = 0;
		ArrayList<Point>[] list = new ArrayList[y];
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < y; i++) {
			list[i] = new ArrayList<>();
			boolean[] visited = new boolean[101];
			
			for (int j = 0; j < x; j++) {
				if(map[j][i] == 0) continue;
				if(!visited[map[j][i]]) {
					visited[map[j][i]] = true;
					list[i].add(new Point(map[j][i] , 1));
				}else {
					for (int k = 0; k < list[i].size(); k++) {
						if(list[i].get(k).num == map[j][i]) {
							list[i].get(k).setCnt(list[i].get(k).getCnt() + 1);
						}
					}
				}
			}	
			Collections.sort(list[i]);
			q.offer(list[i].size());
			maxX = Math.max(maxX, list[i].size() * 2);
			if(maxX > 100) maxX = 100;
		}
		if(maxX < 3) x = 3;
		else x = maxX;
		CArrayCopy(list, q);
		
	}
	
	private static void RArrayCopy(ArrayList<Point>[] list, Queue<Integer> q) {
		map = new int[x][y];
		outer : for (int i = 0; i < list.length; i++) {
			int idx = 0;
			int w = q.poll();
			for (int j = 0; j < y; j += 2) {
				if(j == w * 2) continue outer;
				map[i][j] = list[i].get(idx).num;
				map[i][j + 1] = list[i].get(idx).getCnt();
				idx++;
			}
		}
		
	}
	
	private static void CArrayCopy(ArrayList<Point>[] list, Queue<Integer> q) {
		map = new int[x][y];
		outer : for (int i = 0; i < list.length; i++) {
			int idx = 0;
			int w = q.poll();
			for (int j = 0; j < x; j += 2) {
				if(j == w * 2) continue outer;
				map[j][i] = list[i].get(idx).num;
				map[j + 1][i] = list[i].get(idx).getCnt();
				idx++;
			}
		}
		
	}

}
