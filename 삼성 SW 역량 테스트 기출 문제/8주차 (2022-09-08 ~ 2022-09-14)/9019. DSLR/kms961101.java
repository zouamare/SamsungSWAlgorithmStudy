package ex.test.bj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_9019_DSLR {
	static class DSLR{
		int from;
		String path;
		
		public DSLR(int from, String path) {
			super();
			this.from = from;
			this.path = path;
		}
	}
	
	static int N, from, to;
	static String ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			ans = "";
			bfs();
			System.out.println(ans);
		}
	}
	
	private static void bfs() {
		Queue<DSLR> q = new LinkedList<>();
		boolean[] visited = new boolean[10001];
		String path = "";
		q.add(new DSLR(from, path));
		visited[from] = true;
		
		while(!q.isEmpty()) {
			DSLR temp = q.poll();
			int n = temp.from;
			String newPath = temp.path;
			if(n == to) {
				ans = newPath;
				return;
			}
			
			// D
			int num = (n * 2) % 10000;
			if(!visited[num]) {
				visited[num] = true;
				q.add(new DSLR(num, newPath + "D"));
			}
			
			// S
			num = (n == 0) ? 9999 : n - 1;
			if(!visited[num]) {
				visited[num] = true;
				q.add(new DSLR(num, newPath + "S"));
			}
			
			int d4 = n % 10; 
			int d3 = (n / 10) % 10;
			int d2 = (n / 100) % 10;
			int d1 = (n / 1000) % 10;
			
			// L
			num = (((d2 * 10 + d3) * 10) + d4) * 10 + d1;
			if(!visited[num]){
				visited[num] = true;
				q.offer(new DSLR(num, newPath + "L"));
			}
			
			//R 
			num = (((d4 * 10 + d1) * 10) + d2) * 10 + d3;
			if(!visited[num]){
				visited[num] = true;
				q.offer(new DSLR(num, newPath + "R"));
			}
		}
	}
}
