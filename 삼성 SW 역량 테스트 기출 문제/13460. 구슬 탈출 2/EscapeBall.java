package solo.study;

import java.util.Arrays;
import java.util.Scanner;

public class EscapeBall {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		char[][] map = new char[x][y];
		int [] hole = new int[2];
		int Bx = 0,
			By = 0,
			Rx = 0,
			Ry = 0;
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		// 입력
		for(int i = 0; i < x; i++) {
			String str = sc.next();
			for(int j = 0; j < y; j++) {
				char word = str.charAt(j);
				map[i][j] = word;
				if(word == 'O') {
					hole[0] = i;
					hole[1] = j;
				}	
				if(word == 'R') {
					Rx = i;
					Ry = j;
				}
					
				if(word == 'B') {
					Bx = i;
					By = j;
				}	
			}
		}
		
		// 탐색
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; y++) {
				
			}
		}
	}
}
