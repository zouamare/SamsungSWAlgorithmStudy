package coding_test.Algo20220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * [문제를 풀기 전에 생각할 것]
 * 구현 문제는 함정이나 답이 문제에 있으므로 문제를 잘 읽는 습관을 들이자.
 * 
 * 상어는 1 ~ M 까지의 자연수 번호가 붙어있다. --> 고유함
 * 상어의 숫자가 작을수록 강력함.
 * 
 * N = map의 크기
 * M = 상어의 마릿수
 * 
 * [전체적인 흐름] --> 1번 상어만 남을 때까지 반복
 * 1. 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
 * 2. 1초마다 모든 상어가 *동시에* 상하좌우로 인접한 칸 중 하나로 이동한다.
 * 
 * [상어의 이동 방향 결정 방법]
 * 1. 인접한 칸 중에 아무 냄새가 없는 칸 (선택은 우선순위를 따름)
 * 2. (1.)이 없다면 자신의 냄새가 있는 칸 (선택은 우선순위를 따름)
 * 3. 상어가 이동한 방향이 보고 있는 방향이 됨!! (계속 갱신해야 함)
 * 4. 한 칸에 여러마리의 상어가 있다면 제일 번호가 낮은 상어만남는다.
 * 
 * [한번 틀린 이유]
 * '1000초가 넘어도 상어가 1마리 이상 존재한다면` 을 1000초과로 봤는데 1000이상 이었다.
 * */

public class BOJ_19237_어른상어 {
	static int[] dx = {0, -1, 1, 0, 0};	// 상 하 좌 우
	static int[] dy = {0, 0, 0, -1, 1};
	static int N, M, K;
	static ArrayList<Shark> sharks;
	static Smell[][] smellMap;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		smellMap = new Smell[N][N];
		sharks = new ArrayList<>();
		for(int i = 0; i < N; i++) {	// map 설정
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0)	sharks.add(new Shark(i, j, map[i][j]));
			}
		}
		Collections.sort(sharks);
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++)	sharks.get(i).setDir(Integer.parseInt(st.nextToken()));	// 상어의 현재 방향을 설정
		for(int i = 0; i < M; i++) {	// 상어마다 우선순위를 정함
			int[][] priority = new int[5][4];
			for(int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());	// 상 하 좌 우 마다 우선순위
				for(int k = 0; k < 4; k++)	priority[j][k] = Integer.parseInt(st.nextToken());
			}
			sharks.get(i).setPriority(priority);
		}
		
		System.out.println(startMoving());
		
	}
	
	private static int startMoving() {
		/*
		 * [전체적인 흐름] --> 1번 상어만 남을 때까지 반복
		 * 1. 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
		 * 2. 1초마다 모든 상어가 *동시에* 상하좌우로 인접한 칸 중 하나로 이동한다.
		 */
		int time = 0;
		while(!OnlyOneShark()) {	// 상어 한마리가 남을 때까지 반복
			if(time >= 1000)	return -1;
			SpreadSmells();
			move();
			SmellsGoingAway();
			time++;
		}
		return time;
	}

	private static void SmellsGoingAway() {	// 냄새가 1씩 줄어듦
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(smellMap[i][j] != null) {	// 냄새가 존재한다면
					if(smellMap[i][j].cnt == 1)	smellMap[i][j] = null;
					else	smellMap[i][j].cnt--;
				}
			}
		}
	}

	private static void SpreadSmells() {	// 상어가 자신의 위치에 냄새를 풍김
		for(int i = 0, size = sharks.size(); i < size; i++) {
			Shark shark = sharks.get(i);
			smellMap[shark.x][shark.y] = new Smell(shark.num, K);
		}
	}

	private static void move() {	// 상어가 움직임
		/*
		 * [상어의 이동 방향 결정 방법]
		 * 1. 인접한 칸 중에 아무 냄새가 없는 칸 (선택은 우선순위를 따름)
		 * 2. (1.)이 없다면 자신의 냄새가 있는 칸 (선택은 우선순위를 따름)
		 * 3. 상어가 이동한 방향이 보고 있는 방향이 됨!! (계속 갱신해야 함)
		 * 4. 한 칸에 여러마리의 상어가 있다면 제일 번호가 낮은 상어만남는다.
		 * */
		int[][] newMap = new int[N][N];
		for(int i = 0, size = sharks.size(); i < size; i++) {
			Shark shark = sharks.get(i);
			// 현재 shark의 위치를 기준으로 탐색 시작.
			int x = shark.x, y = shark.y, num = shark.num, dir = shark.dir;
			int[] priority = shark.priority[dir];
			// 인접한 칸 중에 아무 냄새가 없는 칸
			boolean move = false;
			for(int j = 0; j < 4; j++) {
				int nx = x + dx[priority[j]];
				int ny = y + dy[priority[j]];
				if(isNotValid(nx, ny) || smellMap[nx][ny] != null)	continue;
				// 이동한다!!
				if(newMap[nx][ny] == 0) {
					shark.renewalVal(nx, ny, priority[j]);
					newMap[nx][ny] = num;
				}
				move = true;
				break;
			}
			if(move)	continue;
			// 주변에 아무 냄새가 없는 칸이 없음
			for(int j = 0; j < 4; j++) {
				int nx = x + dx[priority[j]];
				int ny = y + dy[priority[j]];
				if(isNotValid(nx, ny) || smellMap[nx][ny].num != shark.num)	continue;
				shark.renewalVal(nx, ny, priority[j]);
				newMap[nx][ny] = num;
				break;
			}
		}
		
		for(int i = sharks.size() - 1; i >= 0; i--) {
			Shark shark = sharks.get(i);
			if(newMap[shark.x][shark.y] != shark.num)	sharks.remove(i);	
		}
		map = newMap;
	}

	private static boolean isNotValid(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

	private static boolean OnlyOneShark() {
		return sharks.size() == 1;
	}

	static class Shark implements Comparable<Shark>{
		int x, y, num, dir;
		int[][] priority;
		
		public Shark(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
			priority = new int[5][4];	// 상하좌우마다 우선순위 (첫 줄은 공백)
		}
		
		public void setDir(int dir) {
			this.dir = dir;
		}
		
		public void setPriority(int[][] priority) {
			this.priority = priority;
		}
		
		public void renewalVal(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public int compareTo(Shark o) {	// 번호 순 오름차순 정렬
			// TODO Auto-generated method stub
			return this.num - o.num;
		}
	}
	
	static class Smell{
		int num, cnt;

		public Smell(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
	}
}
