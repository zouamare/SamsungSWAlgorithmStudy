package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21608_상어초등학교 {
	static int N;
	static int[] number;
	static int[][] student, map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int powN = (int) Math.pow(N, 2);
		student = new int[powN + 1][4];
		map = new int[N][N];
		number = new int[powN];
		visited = new boolean[N][N];
		for (int i = 0; i < powN; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			number[i] = n;
			for (int j = 0; j < 4; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				student[n][j] = tmp;
			}
		}
		
		int idx = -1;
		while(idx++ < powN - 1) {
			int adjacent = 0;
			int empty = 0;
			int x = 99;
			int y = 99;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) continue;
					int tempAdjacent = 0;
					int tempEmpty = 0;

					for (int k = 0; k < 4; k++) {
						int nx = dx[k] + i;
						int ny = dy[k] + j;
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						if(map[nx][ny] == 0) tempEmpty++;
						else {
							for (int l = 0; l < 4; l++) {
								if(map[nx][ny] == student[number[idx]][l]) tempAdjacent++;
							}
						}
					}
					// 1. 인접한 칸이 많은지 확인
					if(adjacent < tempAdjacent) {
						adjacent = tempAdjacent;
						empty = tempEmpty;
						x = i;
						y = j;
					// 2. 인접한 칸이 같으면 빈칸이 많은거 확인
					}else if(adjacent == tempAdjacent && empty < tempEmpty) {
						empty = tempEmpty;
						x = i;
						y = j;
					// 3. 인접한 칸이 없고 빈칸이 없으면 젤 왼쪽에 있는 칸을 선택
					}else if(x == 99 && y == 99) {
						x = i;
						y = j;
					}
				}
			}
			
			map[x][y] = number[idx];
			visited[x][y] = true;
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int temp = 0;
				for (int k = 0; k < 4; k++) {
					int nx = dx[k] + i;
					int ny = dy[k] + j;
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
					for (int l = 0; l < 4; l++) {
						if(map[nx][ny] == student[map[i][j]][l]) temp++;
					}
				}
				if(temp == 2) temp = 10;
				else if(temp == 3) temp = 100;
				else if(temp == 4) temp = 1000;
				ans += temp;
			}
		}
		
		System.out.println(ans);
		
	}
}
