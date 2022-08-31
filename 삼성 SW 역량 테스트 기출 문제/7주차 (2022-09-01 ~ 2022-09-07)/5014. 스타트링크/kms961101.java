package ex.test.bj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_5014_스타트링크 {
	static int F, S, G, U, D, min;
	static boolean flag = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		U = sc.nextInt();
		D = sc.nextInt();
		if(S == G) {
			System.out.println(0);
		}else {
			bfs();
			System.out.println(flag ? min + 1 : "use the stairs");
		}
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[1000001];
		visited[S] = true;
		if(S + U <= F) {
			q.offer(S + U);
			visited[S + U] = true;
		}
		if(S - D >= 1) {
			q.offer(S - D);
			visited[S - D] = true;
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int n = q.poll();
				if(n == G) {
					flag = true;
					return;
				}
				int up = n + U;
				int down = n - D;
				if(up <= F && !visited[up]) {
					visited[up] = true;
					q.offer(up);
					
				}
				if(down >= 1 && !visited[down]) {
					visited[down] = true;
					q.offer(down);
				}
				
			}
			min++;
		}
	}
}
