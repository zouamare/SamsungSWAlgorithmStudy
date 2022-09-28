package solo.study;

import java.util.Scanner;

public class BOJ_14890_경사로 {
	static int n, l, cnt;
	static boolean[][] check;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		l = sc.nextInt();
		
		map = new int[n][n];
		check = new boolean[n][n];
		
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(checked(0, i, 0))
				cnt += 1;
			if(checked(1, 0, i))
				cnt += 1;
		}
		System.out.println(cnt);
	}
	private static boolean checked(int cnt, int x, int y) {
		int[] arr = new int[n];
		boolean[] visited = new boolean[n];
		if(cnt == 0) { // 가로
			for(int i = 0; i < n; i++) {
				arr[i] = map[i][x];
			}
		}
		else { // 세로
			for(int i = 0; i < n; i++) {
				arr[i] = map[y][i];
			}
		}
		
		for(int i = 0; i < n - 1; i++) {
			if(arr[i] == arr[i + 1]) continue; // 같은 경우 경사로X
			if(Math.abs(arr[i] - arr[i + 1]) != 1) return false; // 경사로의 최대길이 1 해당 안 하면 X
			
			if(arr[i] > arr[i + 1]) { // 내려가는 경사로
				for(int j = i + 1; j <= i + l; j++) {
					if(j > n - 1) return false; // 경사로 범위 out
					if(visited[j] || arr[j] != arr[i] - 1) return false; // 경사로 겹침
					visited[j] = true;
				}
			}
			else if(arr[i] < arr[i + 1]) { // 올라가는 경사로
				for(int j = i; j > i - l; j--) {
					if(j < 0) return false; // 경사로 범위
					if(visited[j] || arr[j] != arr[i + 1] - 1) return false; // 겹침
					visited[j] = true;
				}
			}
		}
		
		return true;
	}
}
