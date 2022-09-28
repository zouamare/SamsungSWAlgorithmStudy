package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 
 *
 */
public class BOJ_23289_온풍기안녕 {
	static int N, M, K, W;
	static int[][] map;
	static boolean[][] visited;
	static int[][][] wall;
	static ArrayList<int[]> check;
	static ArrayList<int[]> hotair;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		wall = new int[N][M][5];
		check = new ArrayList<>();
		hotair = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 5) check.add(new int[] {i, j});
				else if(n != 0) hotair.add(new int[] {i, j, n});
			}
		}
		W = Integer.parseInt(br.readLine());
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			visited[n][m] = true;
			if(d == 0) {
				visited[n - 1][m] = true;
				wall[n][m][3] = 1;
				wall[n - 1][m][4] = 1;
			}
			else if(d == 1) {
				visited[n][m + 1] = true;
				wall[n][m][1] = 1;
				wall[n][m + 1][2] = 1;
			}
		}
		int choco = 0;
		while(choco++ < 100) {
			on();
			move();
			delete();
			boolean flag = check();
			if(flag) break;
		}
		System.out.println(choco);
	}
	
	private static void on() {
		for (int i = 0; i < hotair.size(); i++) {
			int x = hotair.get(i)[0];
			int y = hotair.get(i)[1];
			int dir = hotair.get(i)[2];
			// 1 우 2 좌 3 상 4 하
			switch(dir) {
			case 1:
				move_right(x, y, dir);
				break;
			case 2:
				move_left(x, y, dir);
				break;
			case 3:
				move_up(x, y, dir);
				break;
			case 4:
				move_down(x, y, dir);
				break;
			}
		}
	}
	
	private static void move_right(int x, int y, int dir) {
		boolean[][] tempVisited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		int n = 5;
		q.add(new int[] {x, y + 1});
		map[x][y + 1] += 5;
		while(n-- > 1) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] z = q.poll();
				int r = z[0];
				int c = z[1];
				// 벽이 없고 안넣어 줬으면 넣어주기
				if((c + 1) < M && !visited[r][c + 1] && !tempVisited[r][c +1]) {
					map[r][c + 1] += n;
					q.add(new int[] {r, c + 1});
					tempVisited[r][c + 1] = true;
				// 벽이 있으면 도착해야할 곳의 왼쪽에 벽이 없어야 넣어줄수 있음
				} else if((c + 1) < M && wall[r][c + 1][2] != 1 && !tempVisited[r][c +1]) {
					map[r][c + 1] += n;
					q.add(new int[] {r, c + 1});
					tempVisited[r][c + 1] = true;
				}
				if((r - 1) > -1 && (c + 1) < M && !visited[r - 1][c] && !visited[r - 1][c + 1] && !tempVisited[r - 1][c +1]) {
					map[r - 1][c + 1] += n; 
					q.add(new int[] {r - 1, c + 1});
					tempVisited[r - 1][c + 1] = true;
				// 두방향 체크
				} else if((r - 1) > -1 && (c + 1) < M && wall[r-1][c][4] != 1 && wall[r - 1][c + 1][2] != 1 && !tempVisited[r - 1][c +1]) {
					map[r - 1][c + 1] += n; 
					q.add(new int[] {r - 1, c + 1});
					tempVisited[r - 1][c + 1] = true;
				}
				if((r + 1) < N && (c + 1) < M && !visited[r + 1][c] && !visited[r + 1][c + 1] && !tempVisited[r + 1][c +1]) {
					map[r + 1][c + 1] += n;
					q.add(new int[] {r + 1, c + 1});
					tempVisited[r + 1][c + 1] = true;
				} else if((r + 1) < N && (c + 1) < M && wall[r+1][c][3] != 1 && wall[r + 1][c + 1][2] != 1 && !tempVisited[r + 1][c +1]) {
					map[r + 1][c + 1] += n;
					q.add(new int[] {r + 1, c + 1});
					tempVisited[r + 1][c + 1] = true;
				}
				
			}
		}
		
	}
	
	private static void move_left(int x, int y, int dir) {
		boolean[][] tempVisited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		int n = 5;
		q.add(new int[] {x, y - 1});
		map[x][y - 1] += 5;
		while(n-- > 1) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] z = q.poll();
				int r = z[0];
				int c = z[1];
				if((c - 1) > -1 && !visited[r][c - 1] && !tempVisited[r][c - 1]) {
					map[r][c - 1] += n;
					q.add(new int[] {r, c - 1});
					tempVisited[r][c - 1] = true;
				}else if((c - 1) > -1 && wall[r][c - 1][1] != 1 && !tempVisited[r][c - 1]) {
					map[r][c - 1] += n;
					q.add(new int[] {r, c - 1});
					tempVisited[r][c - 1] = true;
				}
				if((r - 1) > -1 && (c - 1) > -1 && !visited[r - 1][c] && !visited[r - 1][c - 1] && !tempVisited[r - 1][c - 1]) {
					map[r - 1][c - 1] += n; 
					q.add(new int[] {r - 1, c - 1});
					tempVisited[r - 1][c - 1] = true;
				}else if((r - 1) > -1 && (c - 1) > -1 && wall[r - 1][c][4] != 1 && wall[r - 1][c - 1][1] != 1 && !tempVisited[r - 1][c - 1]) {
					map[r - 1][c - 1] += n; 
					q.add(new int[] {r - 1, c - 1});
					tempVisited[r - 1][c - 1] = true;
				}
				if((r + 1) < N && (c - 1) > -1 && !visited[r + 1][c] && !visited[r + 1][c - 1] && !tempVisited[r + 1][c - 1]) {
					map[r + 1][c - 1] += n;
					q.add(new int[] {r + 1, c - 1});
					tempVisited[r + 1][c - 1] = true;
				}else if((c - 1) > -1 && (r + 1) < N && wall[r + 1][c][3] != 1 && wall[r + 1][c - 1][1] != 1 && !tempVisited[r + 1][c - 1]) {
					map[r + 1][c - 1] += n;
					q.add(new int[] {r + 1, c - 1});
					tempVisited[r + 1][c - 1] = true;
				}
			}
		}
	}
	
	private static void move_up(int x, int y, int dir) {
		boolean[][] tempVisited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		int n = 5;
		q.add(new int[] {x - 1, y});
		map[x - 1][y] += 5;
		while(n-- > 1) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] z = q.poll();
				int r = z[0];
				int c = z[1];
				if((r - 1) > - 1 &&!visited[r - 1][c] && !tempVisited[r - 1][c]) {
					map[r - 1][c] += n;
					q.add(new int[] {r - 1, c});
					tempVisited[r - 1][c] = true;
				} else if((r - 1) > -1 && wall[r - 1][c][4] != 1 && !tempVisited[r - 1][c]) {
					map[r - 1][c] += n;
					q.add(new int[] {r - 1, c});
					tempVisited[r - 1][c] = true;
				}
				if((r - 1) > -1 && (c - 1) > -1 && !visited[r][c - 1] && !visited[r - 1][c - 1] && (r - 1) > -1 && (c - 1) > -1 && !tempVisited[r - 1][c - 1]) {
					map[r - 1][c - 1] += n; 
					q.add(new int[] {r - 1, c - 1});
					tempVisited[r - 1][c - 1] = true;
				} else if((r - 1) > -1 && (c - 1) > -1  && wall[r][c - 1][1] != 1 && wall[r - 1][c - 1][4] != 1 && !tempVisited[r - 1][c - 1]) {
					map[r - 1][c - 1] += n; 
					q.add(new int[] {r - 1, c - 1});
					tempVisited[r - 1][c - 1] = true;
				}
				if((r - 1) > -1 && (c + 1) < M && !visited[r][c + 1] && !visited[r - 1][c + 1] && !tempVisited[r - 1][c + 1]) {
					map[r - 1][c + 1] += n;
					q.add(new int[] {r - 1, c + 1});
					tempVisited[r - 1][c + 1] = true;
				} else if((r - 1) > -1 && (c + 1) < M &&wall[r][c + 1][2] != 1 && wall[r - 1][c + 1][4] != 1 && !tempVisited[r - 1][c + 1]) {
					map[r - 1][c + 1] += n;
					q.add(new int[] {r - 1, c + 1});
					tempVisited[r - 1][c + 1] = true;
				}
			}
		}
	}
	
	private static void move_down(int x, int y, int dir) {
		boolean[][] tempVisited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		int n = 5;
		q.add(new int[] {x + 1, y});
		map[x + 1][y] += 5;
		while(n-- > 1) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] z = q.poll();
				int r = z[0];
				int c = z[1];
				if((r + 1) < N && !visited[r + 1][c] && !tempVisited[r + 1][c]) {
					map[r + 1][c] += n;
					q.add(new int[] {r + 1, c});
					tempVisited[r + 1][c] = true;
				} else if((r + 1) < N && wall[r + 1][c][3] != 1 && !tempVisited[r + 1][c]) {
					map[r + 1][c] += n;
					q.add(new int[] {r + 1, c});
					tempVisited[r + 1][c] = true;
				}
				if((r + 1) < N && (c - 1) > -1 && !visited[r][c - 1] && !visited[r + 1][c - 1] && !tempVisited[r + 1][c - 1]) {
					map[r + 1][c - 1] += n; 
					q.add(new int[] {r + 1, c - 1});
					tempVisited[r + 1][c - 1] = true;
				} else if((r - 1) > -1 && (c - 1) > -1 && (r + 1) < N && wall[r][c - 1][1] != 1 && wall[r + 1][c - 1][3] != 1 && !tempVisited[r + 1][c - 1]) {
					map[r + 1][c - 1] += n; 
					q.add(new int[] {r + 1, c - 1});
					tempVisited[r + 1][c - 1] = true;
				}
				if((r + 1) < N && (c + 1) < M && !visited[r][c + 1] && !visited[r + 1][c + 1] && !tempVisited[r + 1][c + 1]) {
					map[r + 1][c + 1] += n;
					q.add(new int[] {r + 1, c + 1});
					tempVisited[r + 1][c + 1] = true;
				} else if((r + 1) < N && (c + 1) < M && wall[r][c + 1][2] != 1 && wall[r + 1][c + 1][3] != 1 && !tempVisited[r + 1][c + 1]) {
					map[r + 1][c + 1] += n;
					q.add(new int[] {r + 1, c + 1});
					tempVisited[r + 1][c + 1] = true;
				}
			}
		}
	}
	
	private static void move() {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] check = new boolean[N][M];
		int[][] tempMap = new int[N][M];
		tempMap = copy(map);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				check[i][j] = true;
				for (int j2 = 0; j2 < 4; j2++) {
					int nx = i + dx[j2];
					int ny = j + dy[j2];
					if(nx < 0 || ny < 0 || nx >= N || ny >= M || check[nx][ny]) continue;
					if(j2 == 0 && wall[nx][ny][2] != 1) {
						int num = Math.abs(map[i][j] - map[nx][ny]) / 4;
						if(num == 0) continue;
						if(map[i][j] > map[nx][ny]) {
							tempMap[i][j] -= num;
							tempMap[nx][ny] += num;
						}else if(map[i][j] < map[nx][ny]){
							tempMap[nx][ny] -= num;
							tempMap[i][j] += num;
						}
					}else if(j2 == 1 && wall[nx][ny][1] != 1) {
						int num = Math.abs(map[i][j] - map[nx][ny]) / 4;
						if(num == 0) continue;
						if(map[i][j] > map[nx][ny]) {
							tempMap[i][j] -= num;
							tempMap[nx][ny] += num;
						}else if(map[i][j] < map[nx][ny]){
							tempMap[nx][ny] -= num;
							tempMap[i][j] += num;
						}
					}else if(j2 == 2 && wall[nx][ny][4] != 1) {
						int num = Math.abs(map[i][j] - map[nx][ny]) / 4;
						if(num == 0) continue;
						if(map[i][j] > map[nx][ny]) {
							tempMap[i][j] -= num;
							tempMap[nx][ny] += num;
						}else if(map[i][j] < map[nx][ny]){
							tempMap[nx][ny] -= num;
							tempMap[i][j] += num;
						}
					}else if(j2 == 3 && wall[nx][ny][3] != 1) {
						int num = Math.abs(map[i][j] - map[nx][ny]) / 4;
						if(num == 0) continue;
						if(map[i][j] > map[nx][ny]) {
							tempMap[i][j] -= num;
							tempMap[nx][ny] += num;
						}else if(map[i][j] < map[nx][ny]){
							tempMap[nx][ny] -= num;
							tempMap[i][j] += num;
						}
					}
				}
			} 
		}
		
		map = copy(tempMap);
	}
	
	private static void delete() {
		for (int i = 0; i < M; i++) {
			if(map[0][i] != 0) map[0][i]--;
		}
		for (int i = 0; i < M; i++) {
			if(map[N - 1][i] != 0) map[N - 1][i]--;
		}
		for (int i = 1; i < N - 1; i++) {
			if(map[i][0] != 0) map[i][0]--;
		}
		for (int i = 1; i < N - 1; i++) {
			if(map[i][M - 1] != 0) map[i][M - 1]--;
		}
	}
	
	private static boolean check() {
		boolean temp = true;
		for (int i = 0; i < check.size(); i++) {
			int x = check.get(i)[0];
			int y = check.get(i)[1];
			if(map[x][y] < K) temp = false;
		}
		return temp;
	}
	
	private static int[][] copy(int[][] map){
		int[][] tempMap = new int[N][M];
		for(int i = 0; i < N; i++) tempMap[i] = map[i].clone();
		return tempMap;
	}
}