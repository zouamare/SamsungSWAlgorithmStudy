package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기 {
	static int N, M, ans;
	static int[][] map, move;
	static boolean[][] visited;
	static ArrayList<int[]> list;
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		move = new int[M][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}
		list = new ArrayList<>();
		list.add(new int[] {N - 2,0});
		list.add(new int[] {N - 2, 1});
		list.add(new int[] {N - 1, 0});
		list.add(new int[] {N - 1, 1});
		
		for (int i = 0; i < M; i++) {
			visited = new boolean[N][N];
			int n = move[i][0];
			int cnt = move[i][1];
			move(n, cnt);
			copy();
			list.clear();
			make();
		}
		
		find();
		System.out.println(ans);
	}
	
	private static void find() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += map[i][j];
			}
		}
	}
	
	private static void make() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] >= 2 && !visited[i][j]) {
					list.add(new int[] {i, j});
					map[i][j] -= 2;
				}
			}
		}
	}
	
	private static void copy() {
		int[][] tempMap = new int[N][N];
		tempMap = copyMap(map);
		for (int i = 0; i < list.size(); i++) {
			int[] temp = list.get(i);
			for (int j = 2; j <= 8; j+=2) {
				int x = temp[0] + dx[j];
				int y = temp[1] + dy[j];
				if(x < 0 || y < 0 || x >= N || y >= N) continue;
				if(map[x][y] > 0) tempMap[temp[0]][temp[1]]++;
			}
		}
		map = copyMap(tempMap);
	}
	
	private static int[][] copyMap(int[][] map2) {
		int[][] tempMap = new int[N][N];
		for (int i = 0; i < N; i++) tempMap[i] = map2[i].clone();
		return tempMap;
	}
	
	private static void move(int n, int cnt) {
		ArrayList<int[]> tempList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int[] temp = list.get(i);
			int x = (N + temp[0] + dx[n] * cnt % N) % N;
			int y = (N + temp[1] + dy[n] * cnt % N) % N;
			visited[x][y] = true;
			map[x][y]++;
			tempList.add(new int[] {x, y});
		}
		list.clear();
		list = (ArrayList<int[]>) tempList.clone();
	}
}
