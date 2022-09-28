package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {
	static int[][] distance;
	static int minChickenDistance;
	static int houseNum;
	static int storeNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<House> houseList = new ArrayList<>();
		List<House> chickenStoreList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if(value == 1)
					houseList.add(new House(i,j));
				else if(value == 2)
					chickenStoreList.add(new House(i,j));
			}
		}
		
		houseNum = houseList.size();
		storeNum = chickenStoreList.size();
		
		// 모든 경우의 수를 구합니다.
		distance = new int[houseNum][storeNum];
		for(int i = 0; i < houseNum; i++) {
			House house = houseList.get(i);
			for(int j = 0; j < storeNum; j++) {
				House store = chickenStoreList.get(j);
				distance[i][j] = Math.abs(house.x - store.x) + Math.abs(house.y - store.y);
			}
		}
		
		// 이제 조합을 구해봅니다.
		minChickenDistance = Integer.MAX_VALUE;
		comb(0,0, M, 0);
		System.out.println(minChickenDistance);
	}
	
	private static void comb(int start, int depth, int target, int flag) {
		// TODO Auto-generated method stub
		if(depth == target) {
			int sum = 0;
			for(int i = 0; i < houseNum; i++) {
				int min = Integer.MAX_VALUE;
				for(int j = 0; j < storeNum; j++) {
					if((flag & 1<<j) != 0) {
						min = Math.min(min, distance[i][j]);
					}
				}
				sum += min;
			}
			minChickenDistance = Math.min(minChickenDistance, sum);
			return;
		}
		for(int i = start; i < storeNum; i++) {
			if((flag & 1<<i) != 0)	continue;
			comb(i+1, depth + 1, target, flag | 1 << i);
		}
	}

	static class House{
		int x;
		int y;
		
		public House(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
