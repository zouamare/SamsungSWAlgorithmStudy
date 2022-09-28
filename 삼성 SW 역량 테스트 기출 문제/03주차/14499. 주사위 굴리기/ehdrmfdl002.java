package solo.study;

import java.util.Scanner;

public class BOJ_14499_주사위굴리기 {
	static int n, m, num;
	static int[][] map;
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {1, -1, 0, 0};
	static int dic[] = new int[6]; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		int row = sc.nextInt();
		int col = sc.nextInt();
		num = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0;  j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < num; i++) {
			int[] tmp = dic.clone();
			int dir = sc.nextInt();
			int rdx = row + dx[dir - 1];
			int rdy = col + dy[dir - 1];
			if(rdx >= 0 && rdx < n && rdy >= 0 && rdy < m) {
				switch (dir) {
				case 1:
					dic[0] = tmp[3];
					dic[2] = tmp[0];
					dic[3] = tmp[5];
					dic[5] = tmp[2];
					break;
				case 2:
					dic[0] = tmp[2];
			        dic[2] = tmp[5];
			        dic[3] = tmp[0];
			        dic[5] = tmp[3];
					break;
				case 3:
					dic[0] = tmp[4];
			        dic[1] = tmp[0];
			        dic[4] = tmp[5];
			        dic[5] = tmp[1];
					break;
				case 4:
					dic[0] = tmp[1];
			        dic[1] = tmp[5];
			        dic[4] = tmp[0];
			        dic[5] = tmp[4];
					break;
				}
				
				if(map[rdx][rdy] == 0) map[rdx][rdy] = dic[5];
				else {
					dic[5] = map[rdx][rdy];
					map[rdx][rdy] = 0;
				}
				
				row = rdx;
				col = rdy;
				
				System.out.println(dic[0]);
			}
		}
	}
}
