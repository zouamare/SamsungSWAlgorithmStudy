package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N9019_1 {
	static int A,B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			Queue<Integer> q = new LinkedList<>();
			boolean visited[] = new boolean[10000];
			String ans[] = new String[10000];

			Arrays.fill(ans,"");
			
			q.offer(A);
			visited[A]=true;
			
			while(!q.isEmpty() && !visited[B]) {
				int x = q.poll();
				
				int D = (2*x)%10000;
				int S = (x==0)?9999:x-1;
				int L = (x%1000)*10+x/1000;
				int R = (x%10)*1000+x/10;
				
				if(!visited[D]) {
					visited[D]=true;
					q.offer(D);
					ans[D] = ans[x]+"D";
				}
				if(!visited[S]) {
					visited[S]=true;
					q.offer(S);
					ans[S] = ans[x]+"S";
				}
				if(!visited[L]) {
					visited[L]=true;
					q.offer(L);
					ans[L] = ans[x]+"L";
				}
				if(!visited[R]) {
					visited[R]=true;
					q.offer(R);
					ans[R] = ans[x]+"R";
				}
			}
			System.out.println(ans[B]);
		}
	}
}
