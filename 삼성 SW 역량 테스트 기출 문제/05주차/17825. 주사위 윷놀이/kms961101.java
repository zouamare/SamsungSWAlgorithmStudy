package ex.test.bj;

import java.util.Scanner;

public class BOJ_17825_주사위윷놀이 {
	static class Mal{
		int location, dir, lastLocation, lastDir, cnt;

		public Mal(int location, int dir, int lastLocation, int lastDir, int cnt) {
			super();
			this.location = location;
			this.dir = dir;
			this.lastLocation = lastLocation;
			this.lastDir = lastDir;
			this.cnt = cnt;
		}
	}
	
	static int max = Integer.MIN_VALUE;
	static Mal[] mal;
	static int[][] yut;
	static int[] move;
	static int[] ten;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		move = new int[10];
		for (int i = 0; i < 10; i++) {
			move[i] = sc.nextInt();
		}
		
		ten = new int[10];
		yut = new int[][] {
			// 시작부터 빨간색으로만 이동
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
			// 10 파란선
			{0, 13, 16, 19, 25, 30, 35, 40},
			// 20 파란선
			{0, 22, 24, 25, 30, 35, 40},
			// 30 파란선
			{0, 28, 27, 26, 25, 30, 35, 40}
			
		};
		perm(0);
		System.out.println(max);
	}
	
	private static void perm(int cnt) {
		if(cnt == 10) {
			mal = new Mal[5];
			for (int i = 0; i < 5; i++) {
				// 말은 처음 r,c 마지막 r,c 개수
				mal[i] = new Mal(0, 0, 0, 0, 0);
			}
			visited = new boolean[4][21];
			move();
			int totalCnt = 0;
			for(Mal m : mal) {
				totalCnt += m.cnt;
			}
			max = Math.max(max, totalCnt);
			return;
		}
		
		for(int i = 1; i < 5; i++) {
			ten[cnt] = i;
			perm(cnt + 1);
		}
	}
	
	private static void move() {
		for (int i = 0; i < ten.length; i++) {
			// 주사위 굴릴 말 선택
			Mal m = mal[ten[i]];
			// 주사위 만큼 이동
			int location = m.location + move[i];
			// 인덱스 방향 0은 빨강색만 이동, 1은 10 파란색, 2는 20 파란색, 3은 30 파란색 
			int dir = m.dir;
			// 빨간색 방향이고 마지막 40 넘어가면 꼴인
			// 이전 있던곳 나갔다 표시
			if(dir == 0 && location >= 21) {
				m.location = 21;
				visited[m.lastDir][m.lastLocation] = false;
				continue;
			}
			// 10 파란색 방향이고 마지막 40 넘어가면 꼴인
			// 이전 있던곳 나갔다 표시
			else if(dir == 1 && location >= 8) {
				m.location = 8;
				visited[m.lastDir][m.lastLocation] = false;
				continue;
			}
			// 20 파란색 방향이고 마지막 40 넘어가면 꼴인
			// 이전 있던곳 나갔다 표시
			else if(dir == 2 && location >= 7) {
				m.location = 7;
				visited[m.lastDir][m.lastLocation] = false;
				continue;
			}
			// 30 파란색 방향이고 마지막 40 넘어가면 꼴인
			// 이전 있던곳 나갔다 표시
			else if(dir == 3 && location >= 8) {
				m.location = 8;
				visited[m.lastDir][m.lastLocation] = false;
				continue;
			}
			// 가려는 곳에 말이 없다면
			if(visited[dir][location]) return;
			// 25, 30, 35, 40은 파란선으로 네번 겹쳐서 한곳이라도 말이 있다면 갈수가 없음
			// 30은 빨간색으로만 가는 30과 지름길 30 두개라서 구분
			if(yut[dir][location] == 25) {
				if(visited[1][4] || visited[2][3] || visited[3][4]) return;
			}
			else if(yut[dir][location] == 30 && location != 15) {
				if(visited[1][5] || visited[2][4] || visited[3][5]) return;
			}
			else if(yut[dir][location] == 35) {
				if(visited[1][6] || visited[2][5] || visited[3][6]) return;
			}
			else if(yut[dir][location] == 40) {
				if(visited[0][20] || visited[1][7] || visited[2][6] || visited[3][7]) return;
			}
			
			//움직일수 있는 곳까지 오면 지금 위치 빼주고 다음위치 올려놓기
			visited[m.lastDir][m.lastLocation] = false;
			visited[dir][location] = true;
			
			//주사위 굴리고 나갈 좌표 미리 놓아주기
			m.lastLocation = location;
			m.lastDir = dir;
			// 밟으면 더해주기
			m.cnt += yut[dir][location];
			//위치랑 좌표 바꿔주기
			// 10 20 30 은 인덱스 1, 2, 3에 해놨으니 방향 바꾸고 위치 초기화
			m.location = location;
			if(dir == 1 && location == 5) continue;
			else if(dir == 2 && location == 4) continue;
			else if(dir == 3 && location == 5) continue;
			if(yut[dir][location] % 10 == 0 && yut[dir][location] != 40) {
				m.dir = yut[dir][location] / 10;
				m.location = 0;
			}
	
		}
		
	}

}
