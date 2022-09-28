package solo.study;

import java.util.*;
import java.io.*;

public class BOJ_2580_스도쿠 {
	static int[][] sdoku = new int[9][9];
	static ArrayList<int[]> list = new ArrayList<>();
	static boolean check;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sdoku[i][j] = sc.nextInt();
				if (sdoku[i][j] == 0) { // 입력받은 번호가 0 이면 list에 추가시킨다.
					list.add(new int[] {i, j});
				}
			}
		}

		search(0, 0); // index와 count의 시작은 0,0 이다.
		// -> index : 비어있는 자리를 저장한 list를 하나씩 꺼낼때 사용한다.
		// -> count : list의 크기(size)만큼 count가 도달한다면, 비어있는 자리에 숫자를 모두 채웠다는 뜻이다.
		
	}

	public static void search(int index, int count) {
		if (count == list.size()) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(sdoku[i][j] + " ");
				}
				System.out.println();
			}
			check = true;
			return;
		}
		if(check)
			return;
		if(index >= list.size())
			return;

		int x = list.get(index)[0]; // 비어있는 자리의 x좌표
		int y = list.get(index)[1]; // 비어있는 자리의 y좌표

		for (int i = 1; i <= 9; i++) {
			if (check(x, y, i)) {
				sdoku[x][y] = i;
				search(index + 1, count + 1);
				sdoku[x][y] = 0;
			}

		}
	}

	public static boolean check(int x, int y, int n) {
		for (int i = 0; i < 9; i++) {
			if (sdoku[x][i] == n)
				return false;
			// 가로(행)은 그대로 두면서, 열을 이동시키며 이미 중복된 숫자(n) 이 있다면 false.

			if (sdoku[i][y] == n)
				return false;
			// 세로(열)은 그대로 두면서, 행을 이동시키며 이미 중복된 숫자(n)이 있다면 false.
		}

		int nx = x / 3 * 3;
		int ny = y / 3 * 3;
		// (x / 3 * 3) 과 (y / 3 * 3)을 구하게 되면 , 비어있는 칸의 시작점을 구할 수 있다.
		// 예를들어 비어있는 칸의 좌표가 (1,4) 라면, 이 좌표의 시작 좌표는 (0, 3) 이 된다.
		// 따라서 3x3 의 중복 여부는 nx와 ny를 시작점으로 +3씩 해주면 구할 수 있게 된다.

		for (int i = nx; i < nx + 3; i++) {
			for (int j = ny; j < ny + 3; j++) {
				if (sdoku[i][j] == n)
					return false;
			}
		}

		return true;
	}

}

