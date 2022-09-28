package ex.test.bj;

import java.util.*;
import java.io.*;

public class BOJ_14891_톱니바퀴{
	static int[][] arr = new int[4][8];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < 4; i++) {
			String[] s = br.readLine().split("");
			for(int j = 0; j < 8; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			check(idx - 1, dir);
		}
		int sum = 0;
		if(arr[0][0] == 1) sum += 1;
		if(arr[1][0] == 1) sum += 2;
		if(arr[2][0] == 1) sum += 4;
		if(arr[3][0] == 1) sum += 8;
		System.out.println(sum);


	}
	private static void check(int idx, int dir) {
		left(idx - 1, -dir);
		right(idx + 1, -dir);
		rotate(idx , dir);
	}
	private static void left(int idx, int dir) {
		if(idx < 0) return;
		if(arr[idx][2] != arr[idx + 1][6]) {
			left(idx - 1, -dir);
			rotate(idx, dir);
		}
		
	}
	private static void right(int idx, int dir) {
		if(idx > 3) return;
		if(arr[idx][6] != arr[idx - 1][2]) {
			right(idx + 1, -dir);
			rotate(idx, dir);
		}
		
	}
	private static void rotate(int idx, int dir) {
		if(dir == 1) {
			int temp = arr[idx][7];
			for(int i = 6; i >= 0; i--) {
				arr[idx][i + 1] = arr[idx][i];
			}
			arr[idx][0] = temp;
		}else {
			int temp = arr[idx][0];
			for(int i = 0; i < 7; i++) {
				arr[idx][i] = arr[idx][i + 1];
			}
			arr[idx][7] = temp;
		}
		
	}
}
