/*
 * bfs를 이용
 * 위로 갈 수 있으면 진행
 * 아래로 갈 수 있으면 진행
 * 현재층이 목적지면 종료
 * 목적지에 도달 할 수 없으면 -1을 리턴하고 -1일 경우 use the stairs를 출력
 */

package solo.study;

import java.util.*;
import java.io.*;

public class BOJ_5014_스타트링크 {
	static int F, S, G, U, D;
	static boolean[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();
		
		check = new boolean[F + 1];
		
		int ans = move();
		
		if(ans >= 0)
			System.out.println(ans);
		else
			System.out.println("use the stairs");
	}
	
	private static int move() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(S);
		check[S] = true;
		
		int size = 0;
		int cnt = -1;
		while(!q.isEmpty()) {
			size = q.size();
			cnt++;
			for(int i = 0; i < size; i++) {
				int num = q.poll();
				
				if(num == G) // 목적지에 도착했으니까 누른 횟수 리턴
					return cnt;
				if(num + U <= F && !check[num + U]) { // 위로
					q.offer(num + U);
					check[num + U] = true;
				}
				if(num - D > 0 && !check[num -D]) { // 아래로
					q.offer(num -D);
					check[num -D] = true;
				}
			}
		}
		
		return -1;
	}
}
