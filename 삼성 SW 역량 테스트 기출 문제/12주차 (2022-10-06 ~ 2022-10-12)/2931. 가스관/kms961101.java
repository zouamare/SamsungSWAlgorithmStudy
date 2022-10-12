package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 
 	이 방향일때 가능한 블럭
	왼 1 2 + -
	오 3 4 + -
	위 1 4 + |
	아 2 3 + |

	이 방향일때 오면 안되는 블럭
	왼 3 4 |
	오 1 2 |
	위 2 3 -
	아 1 4 -

 */
public class BOJ_2931_가스관 {
	static class Block{
		int nx;
		int ny;
		int x;
		int y;
		int dir;
		int cnt;
		int totalCnt;
		char[][] map;
		public Block(int nx, int ny, int x, int y, int dir, int cnt, char[][] map, int totalCnt) {
			super();
			this.nx = nx;
			this.ny = ny;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
			this.map = map;
			this.totalCnt = totalCnt; 
		}
	}
	
	static int R, C, sx, sy, sn;
	static char[][] map;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, -1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'M') {
					sx = i;
					sy = j;
				}
				if(map[i][j] == '+') sn += 2;
				else if(map[i][j] != '.' && map[i][j] != 'M' && map[i][j] != 'Z') sn++;
			}
		}
		bfs();
	}
	
	private static void bfs() {
		Queue<Block> q = new LinkedList<>();
		for (int i = 1; i <= 4; i++) {
			int kx = sx + dx[i];
			int ky = sy + dy[i];
			if(kx < 0 || ky < 0 || kx >= R || ky >= C) continue;
			if(map[kx][ky] != '.' && map[kx][ky] != 'Z') q.add(new Block(kx, ky, 0, 0, i, 0, map, 1));
		}
		while(!q.isEmpty()) {
			Block b = q.poll();
			if(b.totalCnt > sn + 3) continue;
			if(b.map[b.nx][b.ny] == 'Z') {
				if(b.map[b.x][b.y] == '+' && b.totalCnt == sn + 3) {
					System.out.println((b.x + 1) + " " + (b.y + 1) + " " + b.map[b.x][b.y]);
					System.exit(0);
				}else if(b.map[b.x][b.y] != '+' && b.totalCnt == sn + 2) {
					System.out.println((b.x + 1) + " " + (b.y + 1) + " " + b.map[b.x][b.y]);
					System.exit(0);
				}
			}
			if(b.map[b.nx][b.ny] != '.' && check(b)) {
				b.dir = findDir(b);
				int kx = b.nx + dx[b.dir];
				int ky = b.ny + dy[b.dir];
				if(kx < 0 || ky < 0 || kx >= R || ky >= C) continue;
				q.add(new Block(kx, ky, b.x, b.y, b.dir, b.cnt, b.map, b.totalCnt + 1));
			}else if(map[b.nx][b.ny] == '.' && b.cnt == 0) {
					char[][] tempMap = new char[R][];
					tempMap = copy(b.map);
					switch(b.dir) {
					case 1:
						tempMap[b.nx][b.ny] = '1';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '2';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '+';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '-';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						break;
					case 2:
						tempMap[b.nx][b.ny] = '3';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '4';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '+';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '-';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						break;
					case 3:
						tempMap[b.nx][b.ny] = '1';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '4';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '+';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '|';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						break;
					case 4:
						tempMap[b.nx][b.ny] = '2';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '3';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '+';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						tempMap = copy(b.map);
						tempMap[b.nx][b.ny] = '|';
						q.add(new Block(b.nx, b.ny, b.nx, b.ny, b.dir, 1, tempMap, b.totalCnt));
						break;
					}
						
			}
		}
	}

	private static int findDir(Block b) {
		switch(b.map[b.nx][b.ny]) {
		case '|':
			return b.dir;
		case '-':
			return b.dir;
		case '+':
			return b.dir;
		case '1':
			if(b.dir == 3) return 2;
			else return 4;
		case '2':
			if(b.dir == 4) return 2;
			else return 3;
		case '3':
			if(b.dir == 4) return 1;
			else return 3;
		case '4':
			if(b.dir == 2) return 4;
			else return 1;
			
		}
		return 0;
	}

	private static char[][] copy(char[][] map2) {
		char[][] temp = new char[R][];
		for (int i = 0; i < R; i++) temp[i] = map2[i].clone();
		return temp;
	}

	private static boolean check(Block b) {
		switch(b.dir) {
		case 1:
			if(b.map[b.nx][b.ny] != '3' && b.map[b.nx][b.ny] != '4' && b.map[b.nx][b.ny] != '|') return true;
			break;
		case 2:
			if(b.map[b.nx][b.ny] != '1' && b.map[b.nx][b.ny] != '2' && b.map[b.nx][b.ny] != '|') return true;
			break;
		case 3:
			if(b.map[b.nx][b.ny] != '2' && b.map[b.nx][b.ny] != '3' && b.map[b.nx][b.ny] != '-') return true;
			break;
		case 4:
			if(b.map[b.nx][b.ny] != '1' && b.map[b.nx][b.ny] != '4' && b.map[b.nx][b.ny] != '-') return true;
			break;
		}
		return false;
	}
}
