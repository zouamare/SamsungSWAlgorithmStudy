package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609_상어중학교 {
	static class Block implements Comparable<Block>{
		int x, y, cnt, rainbow;

		public Block(int x, int y, int cnt, int rainbow) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.rainbow = rainbow;
		}
		
		@Override
		public String toString() {
			return "x=" + x + ", y=" + y + ", cnt=" + cnt + ", rainbow=" + rainbow;
		}

		@Override
		public int compareTo(Block o) {
			if(this.cnt == o.cnt && this.rainbow == o.rainbow) {
				if(this.x == o.x) return o.y - this.y;
				return o.x - this.x;
			}else if(this.cnt == o.cnt) return o.rainbow - this.rainbow;
			return o.cnt - this.cnt;
		}
	}
	
	static int N, M, score;
	static int[][] map;
	static int[] num;
	static boolean[][][] numVisited;
	static ArrayList<Block> list;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			// 블록 번호 담아줄거
			num = new int[M + 1];
			// 기준 블록 찾기위해 방문 안한곳만 방문
			numVisited = new boolean[N][N][M + 1];
			list = new ArrayList<>();
			boolean flag = false;
			// 99는 빈칸
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int n = map[i][j];
					if(n > 0  && n != 99 && !numVisited[i][j][map[i][j]]) {
						flag = true;
						bfs(i, j);
					}
				}
			}
			if(!flag) break;
			Collections.sort(list);
			Block temp = list.get(0);
			// 1이면 블록이 없으므로 종료
			if(temp.cnt == 1) break;
			score += temp.cnt * temp.cnt;
			delete(temp);
			
			for (int i = N - 2; i > -1; i--) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != -1 && map[i][j] != 99) gravity(i, j);
				}
			}
			
			move();
			for (int i = N - 2; i > -1; i--) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != -1 && map[i][j] != 99) gravity(i, j);
				}
			}
		}
		
		System.out.println(score);
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		numVisited[x][y][map[x][y]] = true;
		visited[x][y] = true;
		q.add(new int[] {x, y});
		int cnt = 1;
		int rainbow = 0;
		while(!q.isEmpty()){
			int[] temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + temp[0];
				int ny = dy[i] + temp[1];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(map[nx][ny] == map[x][y] || map[nx][ny] == 0) {
					visited[nx][ny] = true;
					numVisited[nx][ny][map[x][y]] = true;
					q.add(new int[] {nx, ny});
					cnt++;
					if(map[nx][ny] == 0) rainbow++;
				}
			}
		}
		if(num[map[x][y]] <= cnt) {
			list.add(new Block(x, y, cnt, rainbow));
			num[map[x][y]] = cnt;
		}
	}
	
	private static void delete(Block temp) {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		int x = temp.x;
		int y = temp.y;
		visited[x][y] = true;
		
		q.add(new int[] {x, y});
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = tmp[0] + dx[i];
				int ny = tmp[1] + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(map[nx][ny] == 0 || map[nx][ny] == map[x][y]) {
					visited[nx][ny] = true;
					
					map[nx][ny] = 99;
					q.add(new int[] {nx, ny});
				}
			}
		}
		map[x][y] = 99;
		
	}
	
	private static void gravity(int x, int y) {
		int nx = x;
		while(true) {
			if(nx + 1 < N && map[nx + 1][y] == 99) nx++;
			else break;
		}
		if(nx == x) return;
		map[nx][y] = map[x][y];
		map[x][y] = 99;
	}
	
	private static int[][] copy(int[][] map){
		int[][] tempMap = new int[N][N];
		for(int i = 0; i < N; i++) tempMap[i] = map[i].clone();
		return tempMap;
	}
	
	private static void move() {
		int[][] tempMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j > -1; j--) {
				tempMap[N - j - 1][i] = map[i][j];
			}
		}
		map = copy(tempMap);
	}
}
