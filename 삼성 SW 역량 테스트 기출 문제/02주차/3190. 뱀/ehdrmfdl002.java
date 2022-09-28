package solo.study;

import java.util.Scanner;

public class 뱀 {
	static int n, k, l; // 보드의 크기, 사과의 갯수, 뱀의 방향 전환 횟수
	static int yhead, xhead, tail, dir;
	static int[] dy, dx; //시간별로 뱀의 몸통 위치를 저장
	static int[] cmd; //시간별로 명령을 저장
	static int[][] map;
	
	static int[] rdx = {1, 0, -1, 0};
	static int[] rdy = {0, 1, 0, -1};
	
	public static void move(int time){
		//뱀의 머리를 한칸 이동
		xhead += rdx[dir];
		yhead += rdy[dir];
		
		//범위를 벗어나거나 자신을 만나면 종료
		if (yhead < 1 || xhead < 1 || yhead > n || xhead > n || map[yhead][xhead] == 2) {
			System.out.println(time);
			return;
		}
		
		//현재 시간의 뱀의 위치를 저장
		dy[time] = yhead;
		dx[time] = xhead;

		//움직인 칸에 사과가 없으면 꼬리를 한 칸 줄임
		if (map[yhead][xhead] != 1) {
			map[dy[tail]][dx[tail]] = 0;
			tail++;
		} 

		//map에 뱀 표시
		map[yhead][xhead] = 2;

		//방향 전환
		if (cmd[time] != 0) {
			dir = (dir + cmd[time]) % 4;
		}
		
		move(time + 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();

		map = new int[n + 1][n + 1];
		for (int i = 0; i < k; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			map[y][x] = 1;
		}

		dy = new int[10001];
		dx = new int[10001];
		cmd = new int[10001];

		yhead = 1; xhead = 1;
		dy[0] = 1; dx[0] = 1;
		map[yhead][xhead] = 2;

		l = sc.nextInt();
		for (int i = 0; i < l; i++) {
			int t = sc.nextInt();
			char c = sc.next().charAt(0);
			
			if(c == 'D') 
				cmd[t] = 1;
			else
				cmd[t] = 3;
		}

		move(1);
	}
}
