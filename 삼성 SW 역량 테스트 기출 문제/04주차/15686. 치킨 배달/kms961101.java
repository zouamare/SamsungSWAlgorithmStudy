package ex.test.bj;

import java.util.Scanner;

public class BOJ_15686_치킨배달 {
	static int N, M, hCnt, cCnt, sum;
	static int min = Integer.MAX_VALUE;
	static int[][] map, house, chiken, res, temp;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int n = sc.nextInt();
				map[i][j] = n;
				if(n == 1) hCnt++;		
				else if(n == 2)cCnt++;
			}
		}
		house = new int[hCnt][2];
		chiken = new int[cCnt][2];
		
		isSelected = new boolean[cCnt];
		int hIdx = 0;
		int cIdx = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					house[hIdx][0] = i;
					house[hIdx][1] = j;	
					hIdx++;
				}else if(map[i][j] == 2){
					chiken[cIdx][0] = i;
					chiken[cIdx][1] = j;
					cIdx++;
				}
			}
		}
		res = new int[hCnt][cCnt];
		temp = new int[hCnt][cCnt];
		distance();
		comb(0, 0);
		System.out.println(min);
	}
	private static void comb(int cnt, int start) {
		if(cnt == M) {
			for(int i = 0; i < hCnt; i++) {
				for(int j = 0; j < cCnt; j++) {
					temp[i][j] = res[i][j];
				}
			}
			for(int i = 0; i < hCnt; i++) {
				if(!isSelected[i]) {
					for(int j = 0; j < hCnt; j++) {
						temp[j][i] = 99;
					}
				}
			}
			sum = 0;
			for(int i = 0; i < hCnt; i++) {
				int num = temp[i][0];
				for(int j = 1; j < cCnt; j++) {
					if(temp[i][j] < num) num = temp[i][j];
				}
				sum += num;
			}
			min = Math.min(min, sum);
			return;
		}
		for(int i = start; i < cCnt; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			comb(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	}
	
	private static void distance() {
		for(int i = 0; i < hCnt; i++) {
			for(int j = 0; j < cCnt; j++) {
				res[i][j] = Math.abs(house[i][0] - chiken[j][0]) + Math.abs(house[i][1] - chiken[j][1]);
			}
		}
	}
}
