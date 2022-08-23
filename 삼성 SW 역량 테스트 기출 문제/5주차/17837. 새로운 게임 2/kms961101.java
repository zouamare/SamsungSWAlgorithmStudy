package ex.test.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class BOJ_17837_새로운게임2 {
	static class Horse{
		int r, c, d;

		public Horse(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	static int N, K;
	static boolean end = false;
	static int[][] map;
	static Vector<Integer>[][] vector;
	static Horse[] horse;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		// 리스트 크기를 자동으로 줄여주는 vector 생성
		vector = new Vector[N + 1][N + 1];
		horse = new Horse[K + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				vector[i][j] = new Vector<>();
			}
		}
		
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			horse[i] = new Horse(r, c, d);
			vector[r][c].add(i);
		}
		
		int ans = 0;
		while(ans++ < 1000) {
			move();
			if(end) break;
		}
		if(ans == 1001) System.out.println(-1);
		else System.out.println(ans);
	}
	
	private static void move() {
		for (int i = 1; i <= K; i++) {
			Horse h = horse[i];
			int x = h.r + dx[h.d];
			int y = h.c + dy[h.d];
			// 밖이고 파랑색이면 방향 바꿔주기, 또 범위 넘어가고 파랑색이면 움직이지 않으므로 한번만 실행
			if(x < 1 || y < 1 || x > N || y > N || map[x][y] == 2) {
				int d = changeDir(h.d);
				x = h.r + dx[d];
				y = h.c + dy[d];
				h.d = d;
			}
			// 흰색은 이동후 순서대로 넣어준다
			if(isIn(x,y) && map[x][y] == 0) {
				ArrayList<Integer> list = new ArrayList<>();
				boolean flag = false;
				//인덱스 앞쪽값은 맨 아래값
				for(Integer n : vector[h.r][h.c]) {
					//아래부터 지금 번호를 먼저 찾기
					if(n == i) flag = true;
					if(flag) {
						// 찾았다면 그 번호부터 위에까지 있는 수를 옮겨주기
						vector[x][y].add(n);
						list.add(n);
					}
				}
				
				for(Integer n : list) {
					vector[horse[n].r][horse[n].c].remove(n);
					horse[n].r = x;
					horse[n].c = y;
				}
			}
			// 빨간색은 이동후 순서를 뒤집어서 넣어준다
			else if(isIn(x,y) && map[x][y] == 1) {
				ArrayList<Integer> list = new ArrayList<>();
				// 맨위에서부터 그 번호까지 거꾸로 넣어주기
				for (int j = vector[h.r][h.c].size() - 1; j >= 0; j--) {
					Integer n = vector[h.r][h.c].get(j);

					vector[x][y].add(n);
					list.add(n);
					// 그 번호까지 찾으면 더 못넣어줌
					if (n == i)
						break;
				}

				for (Integer n : list) {
					vector[horse[n].r][horse[n].c].remove(n);
					horse[n].r = x;
					horse[n].c = y;
				}
			}
			// 진행되는 순간 4개이면 끝내줘야 되서 매번 체크
			// 턴이 끝나고 해주는줄 알고 처음에 해놔서 오류 찾는데 오래걸림
			for (int j = 1; j <= K; j++) {
				if(vector[horse[j].r][horse[j].c].size() >= 4) {
					end = true;
					break;
				}
			}
		}
	}
	
	private static boolean isIn(int x, int y) {
		if(x >= 1 && y >= 1 && x <= N && y <= N) return true;
		return false;
	}
	
	private static int changeDir(int d) {
		if(d == 1) return 2;
		else if(d== 2) return 1;
		else if(d == 3) return 4;
		else return 3;
	}
}
