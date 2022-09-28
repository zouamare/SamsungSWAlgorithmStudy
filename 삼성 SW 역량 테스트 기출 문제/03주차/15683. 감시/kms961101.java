package ex.test.bj;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {
	static class Point{
		int x, y;
		int val;
		
		Point(int x, int y, int val){
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	
	static int N, M, ans = Integer.MAX_VALUE;
	static ArrayList<Point> point = new ArrayList<>();
	static int[][] map, temp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) {
					point.add(new Point(i, j, map[i][j]));
				}
			}
		}
		
		BlindSpot(0, map);
		System.out.println(ans);
		
	}
	private static void BlindSpot(int idx, int[][] map) {
		if(idx == point.size()) {
			ans = Math.min(ans, findBlindSpot(map));
			return;
		}
		Point pnt = point.get(idx);
		switch(pnt.val) {
		case 1:
			one(pnt.x, pnt.y, idx, map);
			break;
		case 2:
			two(pnt.x, pnt.y, idx, map);
			break;
		case 3:
			three(pnt.x, pnt.y, idx, map);
			break;
		case 4:
			four(pnt.x, pnt.y, idx, map);
			break;
		case 5:
			five(pnt.x, pnt.y, idx, map);
			break;
		}
	}
	private static void one(int x, int y, int idx, int[][] map) {
		int[][] temp1 = arrayCopy(map);
		BlindSpot(idx + 1, Left(x, y, temp1));
		int[][] temp2 = arrayCopy(map);
		BlindSpot(idx + 1, Right(x, y, temp2));
		int[][] temp3 = arrayCopy(map);
		BlindSpot(idx + 1, Up(x, y, temp3));
		int[][] temp4 = arrayCopy(map);
		BlindSpot(idx + 1, Down(x, y, temp4));
	}
	private static void two(int x, int y, int idx, int[][] map) {
		int[][] temp1 = arrayCopy(map);
		Left(x, y, temp1);
		Right(x, y, temp1);
		BlindSpot(idx + 1, temp1);
		
		int[][] temp2 = arrayCopy(map);
		Up(x, y, temp2);
		Down(x, y, temp2);
		BlindSpot(idx + 1, temp2);
		
	}
	private static void three(int x, int y, int idx, int[][] map) {
		int[][] temp1 = arrayCopy(map);
		Left(x, y, temp1);
		Up(x, y, temp1);
		BlindSpot(idx + 1, temp1);
		
		int[][] temp2 = arrayCopy(map);
		Up(x, y, temp2);
		Right(x, y, temp2);
		BlindSpot(idx + 1, temp2);
		
		int[][] temp3 = arrayCopy(map);
		Right(x, y, temp3);
		Down(x, y, temp3);
		BlindSpot(idx + 1, temp3);
		
		int[][] temp4 = arrayCopy(map);
		Down(x, y, temp4);
		Left(x, y, temp4);
		BlindSpot(idx + 1, temp4);
		
	}
	private static void four(int x, int y, int idx, int[][] map) {
		int[][] temp1 = arrayCopy(map);
		Left(x, y, temp1);
		Up(x, y, temp1);
		Right(x, y, temp1);
		BlindSpot(idx + 1, temp1);
		
		int[][] temp2 = arrayCopy(map);
		Up(x, y, temp2);
		Right(x, y, temp2);
		Down(x, y, temp2);
		BlindSpot(idx + 1, temp2);
		
		int[][] temp3 = arrayCopy(map);
		Right(x, y, temp3);
		Down(x, y, temp3);
		Left(x, y, temp3);
		BlindSpot(idx + 1, temp3);
		
		int[][] temp4 = arrayCopy(map);
		Down(x, y, temp4);
		Left(x, y, temp4);
		Up(x, y, temp4);
		BlindSpot(idx + 1, temp4);
		
	}
	private static void five(int x, int y, int idx, int[][] map) {
		int[][] temp1 = arrayCopy(map);
		Left(x, y, temp1);
		Right(x, y, temp1);
		Up(x, y, temp1);
		Down(x, y, temp1);
		BlindSpot(idx + 1, temp1);
		
	}
	private static int[][] Left(int x, int y, int[][] map) {
		for(int i = y - 1; i >= 0; i--) {
			if(map[x][i] == 6) return map;
			else map[x][i] = 7;
		}
		return map;
	}
	private static int[][] Right(int x, int y, int[][] map) {
		for(int i = y + 1; i < M; i++) {
			if(map[x][i] == 6) return map;
			else map[x][i] = 7;
		}
		return map;
	}
	private static int[][] Up(int x, int y, int[][] map) {
		for(int i = x - 1; i >= 0; i--) {
			if(map[i][y] == 6) return map;
			else map[i][y] = 7;
		}
		return map;
	}
	private static int[][] Down(int x, int y, int[][] map) {
		for(int i = x + 1; i < N; i++) {
			if(map[i][y] == 6) return map;
			else map[i][y] = 7;
		}
		return map;
	}
	private static int[][] arrayCopy(int[][] map){
		int[][] copy = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}
	
	private static int findBlindSpot(int[][] map) {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

}
