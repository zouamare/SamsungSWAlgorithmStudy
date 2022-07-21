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
		for(int i = 0; i < hole.length; i++) {
			System.out.println(hole[i]);
		}
		System.out.println(Rx);
		System.out.println(Ry);
		System.out.println(Bx);
		System.out.println(By);
	}

}
