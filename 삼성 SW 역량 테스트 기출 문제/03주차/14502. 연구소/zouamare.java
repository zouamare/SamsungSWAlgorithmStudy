package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int N;	// row
	static int M;	// col
	static int[][] map;
	static ArrayList<Virus> vList;
	static int zeroCnt;
	static int minZeroCnt = Integer.MIN_VALUE;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					vList.add(new Virus(i, j));
				}else if(map[i][j] == 0) {
					zeroCnt++;
				}
			}
		}
		// 흠
		perm(0, new int[6]);
		System.out.println(minZeroCnt);
	}
	private static void perm(int cnt, int[] output) {
		if(cnt == 6) {
			// 좌표가 같은게 있으면 안됨
			if((output[0] == output[2]) && (output[1] == output[3])
					|| (output[0] == output[4]) && (output[1] == output[5])
					|| (output[2] == output[4]) && (output[3] == output[5]))
				return;
			// 여기서 output 가지고 결과 내삐리기
			BuildWallAndSpreadVirus(output);
			return;
		}
		if(cnt%2 == 0) {	// x좌표
			for(int i = 0; i < N; i++) {
				output[cnt] = i;
				perm(cnt+1, output);
			}
		}else {	// y좌표
			for(int i = 0; i < M; i++) {
				output[cnt] = i;
				// 여기서 cnt랑 cnt -1 이랑 확인하면 되겠네
				if(map[output[cnt-1]][output[cnt]] != 0)	continue;
				perm(cnt+1, output);
			}
		}
	}
	private static void BuildWallAndSpreadVirus(int[] locations) {
		// 바이러스를 퍼트린다.
		minZeroCnt = Math.max(minZeroCnt, zeroCnt - spreadVirus(BuildWall(map, locations)));
	}
	private static int spreadVirus(int[][] newMap) {
		int deletedZeroCnt = 0;
		Queue<Virus> queue = new LinkedList<>();
		for(Virus v : vList) {
			queue.add(v);
		}
		while(!queue.isEmpty()) {
			Virus deletedVirus = queue.poll();
			for(int d = 0; d < 4; d++) {
				int nx = deletedVirus.x + dx[d];
				int ny = deletedVirus.y + dy[d];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || newMap[nx][ny] != 0)	continue;
				deletedZeroCnt++;	// 0이면 바이러스 퍼트릴 수 있다.
				newMap[nx][ny] = 2;	// visited 역할
				queue.add(new Virus(nx, ny));
			}
		}
		return deletedZeroCnt;
	}
	private static int[][] BuildWall(int[][] map, int[] locations) {
		int[][] newMap = new int[N][M];
		// map을 deepcopy 한다.
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				newMap[i][j] = map[i][j];
		// 벽을 세운다.
		newMap[locations[0]][locations[1]] = newMap[locations[2]][locations[3]] = newMap[locations[4]][locations[5]] = 1;
		return newMap;
	}
	static class Virus{
		int x;
		int y;
		
		public Virus(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
