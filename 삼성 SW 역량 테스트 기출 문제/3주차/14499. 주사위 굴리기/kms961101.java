package ex.test.bj;

import java.util.*;
import java.io.*;

public class BOJ_14499_주사위굴리기 {
	static int[][] map;
	static int[] move, dice, temp;
	static int n, m, x, y, k;
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
		/*
		 * 			 up
		 * 		left top right
		 * 			 down
		 * 			 bottom
		 */
		dice = new int[6];
		temp = new int[6];
		move = new int[k];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < k; i++) {
			x += dx[move[i] - 1];
			y += dy[move[i] - 1];
			if(y < 0 || y >= m || x < 0 || x >= n) {
				x -= dx[move[i] - 1];
				y -= dy[move[i] - 1];
				continue;
			}
			move(x, y, move[i]);
			System.out.println(dice[2]);
		}
			
	}
	
	private static void move(int x, int y, int move) {
		for(int i = 0; i < 6; i++) {
			temp[i] = dice[i];
		}
		switch(move) {
		case 1: 
			if(map[x][y] == 0) {
				map[x][y] = dice[5];
				temp[3] = dice[2]; //t > r
				temp[5] = dice[3]; //r > b
				temp[1] = dice[5]; //b > l
				temp[2] = dice[1]; //l > t
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
			}else {
				temp[3] = dice[2]; 
				temp[5] = dice[3];
				temp[1] = dice[5];
				temp[2] = dice[1];
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
				dice[5] = map[x][y];
				map[x][y] = 0;
				
			}
			break;
		case 2: 
			if(map[x][y] == 0) {
				map[x][y] = dice[5];
				temp[1] = dice[2]; //t > l
				temp[5] = dice[1]; //l > b
				temp[3] = dice[5]; //b > r
				temp[2] = dice[3]; //r > t
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
			}else {
				temp[1] = dice[2];
				temp[5] = dice[1];
				temp[3] = dice[5];
				temp[2] = dice[3];
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
				dice[5] = map[x][y];
				map[x][y] = 0;
			}
			break;
		case 3: 
			if(map[x][y] == 0) {
				map[x][y] = dice[5];
				temp[0] = dice[2]; //t > u
				temp[5] = dice[0]; //u > b
				temp[4] = dice[5]; //b > d
				temp[2] = dice[4]; //d > t
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
			}else {
				temp[0] = dice[2];
				temp[5] = dice[0];
				temp[4] = dice[5];
				temp[2] = dice[4];
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
				dice[5] = map[x][y];
				map[x][y] = 0;
			}
			break;
		case 4: 
			if(map[x][y] == 0) {
				map[x][y] = dice[5];
				temp[4] = dice[2]; //t > d
				temp[5] = dice[4]; //d > b
				temp[0] = dice[5]; //b > u
				temp[2] = dice[0]; // u > t
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
			}else {
				temp[4] = dice[2];
				temp[5] = dice[4];
				temp[0] = dice[5];
				temp[2] = dice[0];
				for(int j = 0; j < 6; j++) {
					dice[j] = temp[j];
				}
				dice[5] = map[x][y];
				map[x][y] = 0;
			}
			break;
		}
	}
}
