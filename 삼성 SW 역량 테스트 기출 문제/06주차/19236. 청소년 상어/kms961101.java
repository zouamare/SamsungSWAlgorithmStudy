package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19236_청소년상어 {
	static int max, cnt;
	static int[][][] board;
	// 1 부터 8 까지 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 좌표, 번호, 방향
		board = new int[4][4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				board[i][j][0] = i;
				board[i][j][1] = j;
				board[i][j][2] = Integer.parseInt(st.nextToken());
				board[i][j][3] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<int[]> shark = new LinkedList<>();
		// 좌표, 방향
		shark.offer(new int[] {board[0][0][0], board[0][0][1], board[0][0][3]});
		cnt += board[0][0][2];
		board[0][0][2] = 99;
		dfs(cnt, board, shark);
		System.out.println(max);
		
	}
	
	private static void dfs(int cnt, int[][][] map, Queue<int[]> shark) {
		int num = 0;
		// 위치 바꿔줄거 담기
		int[] temp = new int[4];
		// 물고기 이동
		outer:while(num++ < 16) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(map[i][j][2] == num) {
						int count = 0;
						while(true) {
							int nx = map[i][j][0] + dx[map[i][j][3]];
							int ny = map[i][j][1] + dy[map[i][j][3]];
							if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny][2] == 99) {
								if(count++ == 8) continue outer;
								if(map[i][j][3] == 8) map[i][j][3] = 1;
								else map[i][j][3]++;
								continue;
							}
							// 번호랑 방향만 바꿔주면 됨
							for (int k = 2; k < 4; k++) {
								temp[k] = map[nx][ny][k];
								map[nx][ny][k] = map[i][j][k];
							}
							for (int k = 2; k < 4; k++) {
								map[i][j][k] = temp[k];
							}
							continue outer;
						}
						
					}
				}
			}
		}
		
		// 상어 이동
		int[] s = shark.poll();
		// 상어는 최대 3번 이동
		for (int i = 1; i < 4; i++) {
			int nx = s[0] + dx[s[2]] * i;
			int ny = s[1] + dy[s[2]] * i;
			// 범위를 벗어나거나, 빈곳이면 갈수없다
			if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || map[nx][ny][2] == 0) continue;
			Queue<int[]> tempShark = new LinkedList<>();
			tempShark.offer(new int[] {nx, ny, map[nx][ny][3]});
			int[][][] tempMap = copy(map);
			int tempCnt = cnt + tempMap[nx][ny][2];
			// 상어 움직이고 그전곳은 빈곳 만들어주기
			tempMap[nx][ny][2] = 99;
			tempMap[s[0]][s[1]][2] = 0;
			dfs(tempCnt, tempMap, tempShark);
		}
		max = Math.max(max, cnt);
		return;
	}
	
	private static int[][][] copy(int[][][] map) {
		int[][][] temp = new int[4][4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					temp[i][j][k] = map[i][j][k];
				}
			}
		}
		return temp;
	}
}	

