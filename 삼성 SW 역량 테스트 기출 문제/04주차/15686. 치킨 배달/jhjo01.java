package day0812;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15686_치킨배달 {
	static int N, M, R, min=999999, idx, idx2;
	static int[][] map, arr, data, data2,numbers;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][N];
		data = new int[N*N][2];
		data2 = new int[N*N][2];
		numbers = new int[M][2];
		
		idx = 0;
		idx2 = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2)
				{
					data[idx][0] = i; // 치킨집
					data[idx][1] = j; // 치킨집
					idx++;
				}
				if(map[i][j] == 1)
				{
					data2[idx2][0] = i; // 집
					data2[idx2][1] = j; // 집
					idx2++;
				}
				
			}
		}
		
		comb(0, 0);
		System.out.println(min);
		
	}
	
	
	private static void comb(int cnt, int start) 
	{
		if(cnt == M)
		{
			int sum = 0;
			for(int i = 0; i < idx2; i++)
			{
				int hr = data2[i][0] + 1; // 집 r
				int hc = data2[i][1] + 1; // 집 c
				int a = 999999999;
				for(int j = 0; j < numbers.length; j++) // idx2는 집
				{
					int cr = numbers[j][0] + 1; // 치킨집 r
					int cc = numbers[j][1] + 1; // 치킨집 c
					a = Math.min(a, Math.abs(cr - hr) + Math.abs(cc - hc)); // 현재 집에서 최소거리 구하기
				}
				sum = sum + a; // 각 집들의 거리 합
			}
			
			min = Math.min(sum, min); // 조합마다 최소값  비교
			return;
		}
		// 가능한 모든 수에 대해 시도(input 배열의 가능한 수 시도)
		// start 부터 처리시 중복 수 추출 방지 및 순서가 다른 조합 추출 방지
		for (int i = start; i < idx; i++)
		{
			if(map[data[i][0]][data[i][1]] == 2) numbers[cnt] = data[i];
			comb(cnt + 1, i + 1); // start는 시작위치만 결정 i가 돌면서 조합 생성함
		}
	}
}
