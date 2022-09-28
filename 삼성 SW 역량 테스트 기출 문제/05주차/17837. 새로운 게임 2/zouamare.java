package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17837_새로운게임2 {
	static int N, K;
	static String[][] map;
	static int[][][] chess;
	static Piece[] pieces;
	static boolean flag;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		/*
		 * 파랑일 때 뒤를 봤을 때 빨강인 경우와 하양인 경우를 나눠서 진행했어야 했는데,
		 * 그냥 하양인 경우로 통일해서 진행하여 시간이 소요됨.
		 * 
		 * */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new String[N][];
		chess = new int[N][N][10];
		for(int i = 0; i < N; i++)	map[i] = br.readLine().split(" ");
		pieces = new Piece[K+1];	// 0번째는 번호로 적합하지 않으므로 생략
		for(int i = 1; i <= K; i++)	{
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			pieces[i] = new Piece(row, col, Integer.parseInt(st.nextToken()));
			chess[row][col][0] = i;
		}
		
		System.out.println(startNewGame2());
	}
	
	private static int startNewGame2() {
		int turn = 1;
		flag = false;
		while(true) {
			if(turn > 1000)	return -1;
			for(int i = 1; i <= K; i++) {
				movePiece(i);
				if(flag)	return turn;
			}
			turn++;
		}
	}

	private static void movePiece(int num) {
		Piece p = pieces[num];
		int x = p.x, y = p.y;
		int numH = 0;
		int totalH = 0;
		for(int i = 0; i < 10; i++) {
			if(chess[p.x][p.y][i] == num) {
				numH = i;
			}
			if(chess[p.x][p.y][i] == 0) {
				totalH = i - 1;
				break;
			}
		}
		// numH ~ totalH까지 옮겨야 함.
		int nx = x + dx[p.dir];
		int ny = y + dy[p.dir];
		if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny].equals("2")) {
			// blue
			changeDir(p);
			nx = x + dx[p.dir];
			ny = y + dy[p.dir];
			if(!(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny].equals("2"))) {
				// 이동한다.
				int moveH = getMoveH(nx, ny);
				if(map[nx][ny].equals("1")) {
					// red 일 때
					for(int i = totalH; i >= numH; i--) {
						// 지금 이동하려는 애부터 위에 있는 애까지.
						int n = chess[x][y][i];
						pieces[n].x = nx;
						pieces[n].y = ny;
						chess[x][y][i] = 0;
						chess[nx][ny][moveH++] = n;
					}
				}else {
					// white 일 때
					for(int i = numH; i <= totalH; i++) {
						// 지금 이동하려는 애부터 위에 있는 애까지.
						int n = chess[x][y][i];
						pieces[n].x = nx;
						pieces[n].y = ny;
						chess[x][y][i] = 0;
						chess[nx][ny][moveH++] = n;
					}
				}
				if(moveH >= 4)	flag = true;
			}
		}else if(map[nx][ny].equals("1")) {
			// red
			int moveH = getMoveH(nx, ny);
			for(int i = totalH; i >= numH; i--) {
				// 지금 이동하려는 애부터 위에 있는 애까지.
				int n = chess[x][y][i];
				pieces[n].x = nx;
				pieces[n].y = ny;
				chess[x][y][i] = 0;
				chess[nx][ny][moveH++] = n;
			}
			if(moveH >= 4)	flag = true;
		}else if(map[nx][ny].equals("0")) {
			// white
			int moveH = getMoveH(nx, ny);
			for(int i = numH; i <= totalH; i++) {
				// 지금 이동하려는 애부터 위에 있는 애까지.
				int n = chess[x][y][i];
				pieces[n].x = nx;
				pieces[n].y = ny;
				chess[x][y][i] = 0;
				chess[nx][ny][moveH++] = n;
			}
			if(moveH >= 4)	flag = true;
		}
		
	}
	
	private static int getMoveH(int x, int y) {
		for(int i = 0; i < 10; i++) {
			if(chess[x][y][i] == 0) {
				return i;
			}
		}
		return 0;	// 하나도 없다.
	}

	private static void changeDir(Piece p) {
		if(p.dir == 1 || p.dir == 3)	p.dir++;
		else p.dir--;
	}

	static class Piece{
		int x, y, dir;

		public Piece(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
