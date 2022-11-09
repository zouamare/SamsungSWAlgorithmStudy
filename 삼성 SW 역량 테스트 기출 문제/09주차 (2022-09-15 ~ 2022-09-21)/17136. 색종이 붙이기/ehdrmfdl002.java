/*
 	큰 것부터 붙여본다
 	붙일 수 있으면 붙였다는 표시 > 시작점만 붙인 색종이의 크기로 두고 나머지는 0으로 바꾸기
 	방문체크 하고 진행하다 아니면 원래대로 돌리고 진행하는 방식
 */

package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_17136_색종이붙이기 {
	static int min = 100;
	static int[][] map;
	static int[] paper;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new int[10][10];
		paper = new int[6];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		solve(0, paper);
		
		if(min == 100)
			System.out.println(-1);
		else
			System.out.println(min);
	//	System.out.println(min == 100 ? -1 : min);
	}

	public static void solve(int index, int[] arr) {
		if (index == 100) {
			int sum = 0;
			for (int i = 1; i < 6; i++)
				sum += paper[i];
			if (min > sum)
				min = sum;
			return;
		}

		int x = index / 10;
		int y = index % 10; // 좌표 구하기
		if (map[x][y] == 0)
			solve(index + 1, paper);
		else {
			for (int i = 5; i > 0; i--) {
				if (isPossible(x, y, i) && paper[i] + 1 <= 5) {
					make(x, y, i, 0);
					paper[i]++;
					solve(index + 1, paper);

					paper[i]--;
					make(x, y, i, 1);
				}
			}
		}

	}

	public static boolean isPossible(int r, int c, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if ((r + i >= 10 || c + j >= 10) || map[r + i][c + j] == 0)
					return false;
			}
		}
		return true;
	}

	public static void make(int r, int c, int size, int state) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[r + i][c + j] = state;
			}
		}
	}
}
