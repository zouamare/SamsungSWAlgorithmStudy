package ex.test.bj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class location{
	int x;
	int y;
	location(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class BOJ_16234_인구이동 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, L, R, cnt;
	static int[][] map;
	static boolean[][] visited;
	static boolean isMove = false;
	static ArrayList<location> list = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		move();
		System.out.println(cnt);
	}
	
	private static void move() {
		while(true) {
			isMove = false;
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			if(!isMove) break;
			else cnt++;
			
		}
		
	}

	private static void bfs(int x, int y) {
		Queue<location> q = new LinkedList<>();
		q.offer(new location(x, y));
		visited[x][y] = true;
		list.add(new location(x, y));
		
		while(!q.isEmpty()) {
			location l = q.poll();
			x = l.x;
			y = l.y;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(!visited[nx][ny] && L <= Math.abs(map[x][y] - map[nx][ny]) &&  Math.abs(map[x][y] - map[nx][ny]) <= R ) {
					isMove = true;
					visited[nx][ny] = true;
					list.add(new location(nx, ny));
					q.offer(new location(nx, ny));
					
				}
			}
		}
		//연합 한군데 끝
		int sum = 0;
		for(int i = 0; i < list.size(); i++) {
			location l = list.get(i);
			sum += map[l.x][l.y];
		}
		for(int i = 0; i < list.size(); i++) {
			location l = list.get(i);
			map[l.x][l.y] = sum / list.size(); 
		}
		list.removeAll(list);
	}
}
