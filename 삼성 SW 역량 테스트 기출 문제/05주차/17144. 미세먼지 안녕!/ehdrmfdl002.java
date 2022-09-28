package solo.study;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_17144_미세먼지안녕 {
	static int n, m, t;
	static int[][] map, temp;
	static ArrayList<Integer> machine = new ArrayList<>();
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 n = sc.nextInt();
		 m = sc.nextInt();
		 t = sc.nextInt();
		 
		 map = new int[n][m];
		 temp = new int[n][m];
		 for(int i = 0; i < n; i++)
			 for(int j = 0; j < m; j++) {
				 int num = sc.nextInt();
				 map[i][j] = num;
				 temp[i][j] = num;
				 if(num == -1)
					 machine.add(i);
			 }
				 
		 for(int i = 0; i < t; i++) {
			 spread();
			 workUp();
			 workDown();
			 copy(temp, map);
		 }
		 
		 int sum = 0;
		 for(int i = 0; i < n; i++)
			 for(int j = 0; j < m; j++)
				 sum += map[i][j];
		 
		 
		 System.out.println(sum + 2);
	}
	
	private static void copy(int[][] arr1, int[][] arr2) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}

	private static void workDown() {
		int num = machine.get(1);
		for(int i = num + 1; i <= n - 2; i++)
			map[i][0] = map[i + 1][0];
		for(int i = 0; i <= m - 2; i++)
			map[n - 1][i] = map[n - 1][i + 1];
		for(int i = n - 1; i >= num + 1; i--)
			map[i][m - 1] = map[i - 1][m - 1];
		for(int i = m - 1; i >= 2; i--)
			map[num][i] = map[num][i - 1];
		
		map[num][1] = 0;
		
	}

	private static void workUp() {
		int num = machine.get(0);
		for(int i = num - 1; i >= 1; i--)
			map[i][0] = map[i - 1][0];
		for(int i = 0; i <= m - 2; i++)
			map[0][i] = map[0][i + 1];
		for(int i = 0; i <= num - 1; i++)
			map[i][m - 1] = map[i + 1][m - 1];
		for(int i = m - 1; i >= 2; i--)
			map[num][i] = map[num][i - 1];
		
		map[num][1] = 0;
	}

	private static void spread() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] > 0) {
					int num = map[i][j] / 5;
					
					for(int k = 0; k < 4; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];
						
						if(ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] != -1) {
							temp[ni][nj] += num;
							temp[i][j] -= num;
						}
					}
				}
			}
		}
		
		copy(map, temp);
	}
}
