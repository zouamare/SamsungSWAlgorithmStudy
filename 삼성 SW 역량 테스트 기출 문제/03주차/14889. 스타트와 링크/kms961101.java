package com.ssafy.recur;

import java.util.*;
import java.io.*;
public class BOJ_14889_스타트와링크 {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int res = -1;
	static int f = 0;
	static int link = 0;
	static int[][] map;
	static int[] people, mChange;
	static Integer[] members;
	static ArrayList<Integer> temp, tChange;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		people = new int[N];
		members = new Integer[N / 2];
		mChange = new int[N / 2];
		temp = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			people[i] = i;
			temp.add(i);
		}
		comb(0, 0);
		System.out.println(min);
	}
	
	private static void comb(int cnt, int start) {
		if(cnt == N / 2) {
			for(int i = 0; i < N / 2; i++) {
				mChange[i] = members[i];
			}
			for(int i = 0; i < N / 2; i++) {
				temp.remove(Integer.valueOf(mChange[i]));
				for(int j = i + 1; j < N / 2; j++) {
					f += map[members[i]][members[j]] + map[members[j]][members[i]];
				}
			}
			for(int i = 0; i < N / 2; i++) {
				for(int j = i + 1; j < N / 2; j++) {
					link += map[temp.get(i)][temp.get(j)] + map[temp.get(j)][temp.get(i)];
				}
			}
			for(int i = 0; i < N / 2; i++) {
				temp.add(members[i]);
			}
			Collections.sort(temp);
			if(f >= link) res = f - link;
			else res = link - f;
			min = Math.min(min, res);
			f = 0;
			link = 0;
			
			return;
		}
		for(int i = start; i < N; i++) {
			members[cnt] = people[i];
			comb(cnt + 1, i + 1);
		}
	}
}
