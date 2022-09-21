package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N2580 {
	static int[][] map = new int[9][9];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0;i<9;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<9;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sudoku(0,0);
	}
	
	public static void sudoku(int x, int y) {
		if(y==9) {
			sudoku(x+1,0);
			return;
		}
		
		if (x==9) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]).append(' ');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		if(map[x][y]==0) {
			for(int i=1;i<=9;i++) {
				if(check(x,y,i)) {
					map[x][y]=i;
					sudoku(x,y+1);
				}
			}
			map[x][y]=0;
			return;
		}
		sudoku(x,y+1);
	}
	
	public static boolean check(int x, int y, int val) {
		
		for(int i=0;i<9;i++) {
			if(map[x][i]==val) {
				return false;
			}
		}
		
		for(int i=0;i<9;i++) {
			if(map[i][y]==val) {
				return false;
			}
		}
		int r = (x/3)*3;
		int c = (y/3)*3;
		
		for(int i=r;i<r+3;i++) {
			for(int j=c;j<c+3;j++) {
				if(map[i][j]==val) {
					return false;
				}
			}
		}
		return true;
	}
}
