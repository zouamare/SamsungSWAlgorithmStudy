package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {
	static class Point{
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static class Change{
		int x, y, cnt;

		public Change(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
	}
	static int R, C, T, ans;
	static int[][] map;
	static ArrayList<Point> Aircleaner;
	static ArrayList<Point> FineDust;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		Aircleaner = new ArrayList<>();
		FineDust = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if(n == -1) Aircleaner.add(new Point(i, j));
				else if( n != 0) FineDust.add(new Point(i, j));
			}
		}
		
		
		while(T-- > 0) {
			//먼지 초기화
			FineDust.clear();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					//5보다 작은 먼지는 확산 안됨
					if(map[i][j] != -1 && map[i][j] >= 5) FineDust.add(new Point(i, j));
				}
			}
			diffusion(); //확산
			clean(); //청소
		}
		find();
		System.out.println(ans);
	}

	private static void diffusion() {
		ArrayList<Change> list = new ArrayList<>();
		for (int i = 0; i < FineDust.size(); i++) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				int x = FineDust.get(i).x + dx[j];
				int y = FineDust.get(i).y + dy[j];
				
				if(x < 0 || y < 0 || x >= R || y >= C) continue;
				if(map[x][y] != -1) {
					list.add(new Change(x, y, map[FineDust.get(i).x][FineDust.get(i).y] / 5));
					cnt++;
				}
			}
			list.add(new Change(FineDust.get(i).x, FineDust.get(i).y, (map[FineDust.get(i).x][FineDust.get(i).y] / 5) * cnt * -1));
		}
		//마지막에 확산
		for (int i = 0; i < list.size(); i++) {
			map[list.get(i).x][list.get(i).y] += list.get(i).cnt;
		}
		
	}
	static int[] temp = new int[3];
	private static void clean() {
		//위에 공기청정기 작동
		int upX = Aircleaner.get(0).x;
		int upY = Aircleaner.get(0).y;
		temp[0] = map[upX][C - 1];
		temp[1] = map[0][C - 1];
		temp[2] = map[0][0];
		//우
		for (int i = C - 1; i > 0; i--) {
			if(map[upX][i] == -1) continue;
			if(map[upX][i - 1] == -1) {
				map[upX][i] = 0;
			}else map[upX][i] = map[upX][i - 1];
		}
		//상
		for (int i = 0; i < upX - 1; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		map[upX - 1][C - 1] = temp[0];
		//좌
		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		map[0][C - 2] = temp[1];
		//하
		for (int i = upX; i > 0; i--) {
			if(map[i][0] == -1) continue;
			if(map[i - 1][0] == -1) map[i][0] = 0;
			else map[i][0] = map[i - 1][0];
		}
		if(temp[2] == -1) map[1][0] = 0;
		else map[1][0] = temp[2];
		
		//아래 공기청정기 작동
		int downX = Aircleaner.get(1).x;
		int downY = Aircleaner.get(1).y;
		temp[0] = map[downX][C - 1];
		temp[1] = map[R - 1][C - 1];
		temp[2] = map[R - 1][0];
		//우
		for (int i = C - 1; i > 0; i--) {
			if(map[downX][i] == -1) continue;
			if(map[downX][i - 1] == -1) map[downX][i] = 0;
			else map[downX][i] = map[downX][i - 1];
		}
		//하
		for (int i = R - 1; i > downX; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		map[downX + 1][C - 1] = temp[0];
		//좌
		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}
		map[R - 1][C - 2] = temp[1];
		//상
		for (int i = downX; i < R - 1; i++) {
			if(map[i][0] == -1) continue;
			if(map[i + 1][0] == -1) map[i][0] = 0;
			else map[i][0] = map[i + 1][0];
		}
		if(temp[2] == -1) map[R - 2][0] = 0;
		else map[R - 2][0] = temp[2];

	}
	
	private static void find() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != 0 && map[i][j] != -1) ans += map[i][j]; 
			}
		}
	}

}
