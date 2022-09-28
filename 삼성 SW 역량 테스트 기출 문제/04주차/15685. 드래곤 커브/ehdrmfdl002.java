package solo.study;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_15685_드래곤커브 {
	static boolean[][] check;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static int ans;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		check = new boolean[101][101];
		
		for(int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();
			list.add(d);
			move(x, y, g);
		}
		
		make();
		System.out.println(ans);
	}
	
	private static void make() {
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(check[i][j] && check[i][j + 1] && check[i + 1][j] && check[i + 1][j + 1])
					ans++;
			}
		}
		
	}

	private static void move(int x, int y, int g) {
		for (int i = 1; i <= g; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add((list.get(j) + 1) % 4);
            }
        }
		
		check[y][x] = true;

		for(int i = 0; i < list.size(); i++) {
			int d = list.get(i);
			x += dx[d];
			y += dy[d];
			check[y][x] = true;
		}
	}
}
