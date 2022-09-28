package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21611_마법사상어와블리자드 {
	static int N, M, d, s, one, two, three, score;
	static boolean outer = false;
	static int[][] map;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// M번 반복
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			// 얼음 파편 떨어트리기
			magic();
			// 빈곳으로 움직이고, 같은 구술이 4개 이상이면 파괴
			move();
			// 맵이 전부 비어있거나, 한번에 모든 구술이 부셔졌을때 끝내주기
			if(outer) break;
			// 구술 그룹으로 묶기
			number();
		}
		score += one + two * 2 + three * 3;
		System.out.println(score);
		}
	
	
	private static void magic() {
		int x = N / 2;
		int y = N / 2;
		for (int i = 0; i < s; i++) {
			x += dx[d];
			y += dy[d];
			map[x][y] = 99;
		}
	}
	
	private static void move() {
		/*
		 *	상 좌 하 우 순서로 1칸부터 2칸씩 늘어나면서 N만큼 돌게된다
		 *	상어부터 시작하면 좌 하 우 순으로 먼저 세번 가야하므로 상어 아래부터 시작해서 네번 맞추기
		 *	네번 돌면 그다음 가야할곳은 오른쪽으로 한칸 가야 위로 갈수 있어서 그것만 따로 지정
		 *	빈곳 빼고 리스트에 값 다 넣어주고
		 *	리스트 처음부터 숫자 같은값 찾고 파괴후 맵에 넣어주기
		 *	더이상 파괴할 구술이 없을때 까지 반복
		 */
		
		ArrayList<Integer> list = new ArrayList<>();
		int x = (N / 2) + 1;
		int y = N / 2;
		int cnt = 1;
		int[] nx = {-1, 0, 1, 0};
		int[] ny = {0, -1, 0, 1};
		while(true) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < cnt; j++) {
					int kx = 0;
					int ky = 0;
					// 네번 돌면 위로가기 위해 오른쪽으로 한칸씩 가야됨
					if(cnt != 1 && i == 0 && j == 0) {
						kx = x + nx[3];
						ky = y + ny[3];
					}else {
						kx = x + nx[i];
						ky = y + ny[i];
					}
					x = kx;
					y = ky;
					if(kx < 0 || ky < 0 || kx >= N || ky >= N) continue;
					if(map[kx][ky] != 99 && map[kx][ky] != 0) list.add(map[kx][ky]);
				}
			}
			if(cnt > N) break;
			cnt += 2;
		}
		
		boolean flag = false;
		int count = 1;
		if(list.size() == 0) {
			outer = true;
			return;
		}
		int number = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if(list.get(i) == number) count++;
			else {
				if(count >= 4) {
					flag = true;
					if(number == 1) one += count;
					else if(number == 2) two += count;
					else three += count;
					number = list.get(i);
					// 리스트에서 같은 번호 구술 없애고 인덱스 준만큼 맞춰주기
					for (int j = 0; j < count; j++) {
						list.remove(i-count);
					}
					i -= count;
					count = 1;
				}else {
					count = 1;
					number = list.get(i);
				}
			}
		}
		// 전체다 부셔야될 구술이면 부수고 나오기
		if(count == list.size()) {
			if(number == 1) one += count;
			else if(number == 2) two += count;
			else three += count;
			outer = true;
			return;
		}
		
		x = (N / 2) + 1;
		y = N / 2;
		cnt = 1;
		int[][] tempMap = new int[N][N];
		int idx = 0;
		while(true) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < cnt; j++) {
					if(idx == list.size()) break;
					int kx = 0;
					int ky = 0;
					if(cnt != 1 && i == 0 && j == 0) {
						kx = x + nx[3];
						ky = y + ny[3];
					}else {
						kx = x + nx[i];
						ky = y + ny[i];
					}
					x = kx;
					y = ky;
					if(kx < 0 || ky < 0 || kx >= N || ky >= N || map[kx][ky] == 0) continue;
					tempMap[kx][ky] = list.get(idx);
					idx++;
				}
			}
			if(cnt > N) break;
			cnt += 2;
		}
		map = copy(tempMap);
		
		if(flag) move();
	}
	
	private static void number() {
		// 리스트에 먼저 구술 그룹을 만들어서 넣어주고 그 다음에 맵에 넣어주기
		ArrayList<int[]> list = new ArrayList<>();
		boolean flag = false;
		int x = (N / 2) + 1;
		int y = N / 2;
		int cnt = 1;
		int num = map[N / 2][(N / 2) - 1];
		int count = 0;
		int[] nx = {-1, 0, 1, 0};
		int[] ny = {0, -1, 0, 1};
		while(true) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < cnt; j++) {
					int kx = 0;
					int ky = 0;
					if(cnt != 1 && i == 0 && j == 0) {
						kx = x + nx[3];
						ky = y + ny[3];
					}else {
						kx = x + nx[i];
						ky = y + ny[i];
					}
					if(kx == N / 2 && ky == N / 2) {
						x = kx;
						y = ky;
						continue;
					}
					if(kx < 0 || ky < 0 || kx >= N || ky >= N || flag) continue;
					if(map[kx][ky] == 0 && !flag) {
						list.add(new int[] {count, num});
						flag = true;
						break;
					}
					x = kx;
					y = ky;
					if(map[kx][ky] == num) count++;
					else {
						list.add(new int[] {count, num});
						num = map[kx][ky];
						count = 1;
					}
				}
			}
			if(cnt > N) break;
			cnt += 2;
		}
		
		x = (N / 2) + 1;
		y = N / 2;
		cnt = 1;
		int idx = 0;
		int change = 0;
		while(true) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < cnt; j++) {
					if(idx == list.size()) break;
					int kx = 0;
					int ky = 0;
					if(cnt != 1 && i == 0 && j == 0) {
						kx = x + nx[3];
						ky = y + ny[3];
					}else {
						kx = x + nx[i];
						ky = y + ny[i];
					}
					x = kx;
					y = ky;
					if(x == N / 2 && y == N / 2) {
						x = kx;
						y = ky;
						continue;
					}
					if(kx < 0 || ky < 0 || kx >= N || ky >= N) continue;
					if(change == 0) {
						map[kx][ky] = list.get(idx)[0];
						change = 1;
					}else if(change == 1){
						map[kx][ky] = list.get(idx)[1];
						change = 0;
						idx++;
					}
				}
			}
			if(cnt > N) break;
			cnt += 2;
		}
	}
	
	private static int[][] copy(int[][] tempMap) {
		int[][] temp = new int[N][];
		for(int i = 0; i < N; i++) temp[i] = tempMap[i].clone();
		return temp;
	}
	
}
