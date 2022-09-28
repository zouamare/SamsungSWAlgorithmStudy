package solo.study;

import java.util.*;
import java.io.*;

public class BOJ_17140_이차원배열과연산 {
	static int r, c, k, time;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[100][100];
		int R = 3, C = 3;
		
		for(int i = 0; i < 3; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(map[r][c] != k && time <= 100) {
			if(R >= C) {
				int max = 0;
				for(int i = 0; i < R; i++) {
					int[] tmp = new int[101];
					
					for(int j = 0; j < C; j++) 
						tmp[map[i][j]]++;
					
					int num = 0;
					
					for(int j = 1; j <= C && num < 100; j++) {
						for(int t = 1; t < 101; t++) {
							if(tmp[t] == j) {
								map[i][num++] = t;
								map[i][num++] = j;
							}
						}
					}
					
					for(int j = num; j <= C && j < 100; j++)
						map[i][j] = 0;
					
					max = Math.max(max, num);
				}
				
				C = max;
			}
			else {
				int max = 0;
				for(int i = 0; i < C; i++) {
					int tmp[] = new int[101];
					
					for(int j = 0; j < R; j++) 
						tmp[map[j][i]]++;
					
					int num = 0;
					
					for(int j = 1; j <= R; j++) {
						for(int k = 1; k < 101; k++) {
							if(tmp[k] == j) {
								map[num++][i] = k;
								map[num++][i] = j;
							}
						}
					}
					
					for(int j = num; j <= R && j < 100; j++)
						map[j][i] = 0;
					
					max = Math.max(max, num);
				}
				
				R = max;
			}
			
			time++;
		}
		
		if(time > 100)
			System.out.println(-1);
		else
			System.out.println(time);
	}
}
