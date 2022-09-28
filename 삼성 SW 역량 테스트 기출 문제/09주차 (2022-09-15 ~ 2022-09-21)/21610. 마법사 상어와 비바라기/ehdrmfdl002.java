/* 
 1. ArrayList에 구름에 대한 정보를 넣는다.
 2. k번만큼 구름의 이동 처리를 하고 0번 행과 n-1행, 0번 열과 n-1행이 연결되어 있으므로 구름의 속력 s를 %n으로 나눈 나머지로 nx, ny의 이동 위치를 구한다. 
 3. nx, ny가 0보다 작다면 n을 더해주고 n이상이라면 n을 빼주면 된다.
 4. check라는 방문체크 배열을 통해 구름이 이동한 위치를 체크해준다.
 5. 만약 구름이 이동한 곳에서 대각선 방향으로 거리가 1인 물이 있는 부구니의 수를 구한 후 바구니에 더해주는데 동시에 일어나기 때문에 map_copy를 이용한다.
 6. 구름을 제거 한 뒤, 만약 바구니에 물의 양이 2이상인 곳에 구름을 생성해주고 해당 바구니의 물의 양을 다 -2 해준다.
 7. m번의 이동이 끝난 후 모든 바구니에 들어있는 물의 양의 합을 출력한다.
 */


package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_21610_마법사상어와비바라기 {

	public static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int n, m;
	public static ArrayList<Node> cloud;
	public static int[][] map;
	public static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ArrayList<Node> cloud = new ArrayList<>();
		cloud.add(new Node(n - 1, 0));
		cloud.add(new Node(n - 1, 1));
		cloud.add(new Node(n - 2, 0));
		cloud.add(new Node(n - 2, 1));

		for (int k = 0; k < m; k++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			boolean[][] check = new boolean[n][n];
			
			// 구름 이동
			for (Node node : cloud) {
				int nx = node.x + dx[dir] * (s % n);
				int ny = node.y + dy[dir] * (s % n);
				if (nx < 0) {
					nx = n - Math.abs(nx);
				} else if (nx >= n) {
					nx -= n;
				}

				if (ny < 0) {
					ny = n - Math.abs(ny);
				} else if (ny >= n) {
					ny -= n;
				}

				map[nx][ny] += 1;
				check[nx][ny] = true;

				node.x = nx;
				node.y = ny;
			}

			int[][] map_copy = new int[n][n];
			for (Node node : cloud) {
				int cnt = 0;

				for (int d = 2; d <= 8; d += 2) {
					int nx = node.x + dx[d];
					int ny = node.y + dy[d];
					if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
						continue;
					}
					if (map[nx][ny] > 0) {
						cnt++;
					}
				}
				map_copy[node.x][node.y] = cnt;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] += map_copy[i][j];
				}
			}
			cloud.clear();

			// 구름 생성
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] >= 2 && !check[i][j]) {
						cloud.add(new Node(i, j));
						map[i][j] -= 2;
					}
				}
			}

		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans += map[i][j];
			}
		}

		System.out.println(ans);
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
