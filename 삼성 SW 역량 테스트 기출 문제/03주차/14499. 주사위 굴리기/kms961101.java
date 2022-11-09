package ex.test.bj;

import java.util.*;
import java.io.*;

public class BOJ_14499_주사위굴리기 {
	static int[][] map;
	static int n, m, x, y, k;
	// 윗면, 바닥면, 앞면, 뒷면, 왼쪽면, 오른쪽면
	static int[] dice = {0, 0, 0, 0, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int move = Integer.parseInt(st.nextToken()) - 1;
			x += dx[move];
			y += dy[move];
			if(y < 0 || y >= m || x < 0 || x >= n) {
				x -= dx[move];
				y -= dy[move];
				continue;
			}
			switch(move) {
			case 0:
				move_right();
				break;
			case 1:
				move_left();
				break;
			case 2:
				move_up();
				break;
			case 3:
				move_down();
				break;
			}
			// 바닥면 복사
			if(map[x][y] == 0) {
				map[x][y] = dice[1];
			} else {
				dice[1] = map[x][y];
				map[x][y] = 0;
			}
			System.out.println(dice[0]);
		}
			
	}
	
	public static void move_right() {
		// 윗 -> 오 -> 바닥 -> 왼 -> 윗
		int temp = dice[0];
		dice[0] = dice[4];
		dice[4] = dice[1];
		dice[1] = dice[5];
		dice[5] = temp;
		
	}
	
	public static void move_left() {
		// 윗 -> 왼 -> 바 -> 오 -> 윗
		int temp = dice[0];
		dice[0] = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[4];
		dice[4] = temp;
		
	}
	
	public static void move_up() {
		// 윗 -> 뒷 -> 바 -> 앞 -> 윗
		int temp = dice[0];
		dice[0] = dice[2];
		dice[2] = dice[1];
		dice[1] = dice[3];
		dice[3] = temp;
		
	}
	
	public static void move_down() {
		// 윗 -> 앞 -> 바 -> 뒷 -> 윗
		int temp = dice[0];
		dice[0] = dice[3];
		dice[3] = dice[1];
		dice[1] = dice[2];
		dice[2] = temp;
		
	}
	


	
}
