package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N20058 {
	static int N,Q,L,n,totCnt,iceCnt,maxCnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Queue<Integer> q;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		n = (int) Math.pow(2, N); // 2의 n승
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		q = new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<Q;i++) {
			int num = (int) Math.pow(2,Integer.parseInt(st.nextToken()));
			q.add(num);
		}
		
		for(int i=0;i<Q;i++) {
			map = divideAndRotate(q.poll()); // 영역을 나누고 회전을 구하는 함수 
			bfs(); // 얼음 개수 파악 후 녹이기
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]>0) { 
					totCnt+=map[i][j]; // 0보다 큰 수들의 합
					if(!visited[i][j]) {
						iceCnt=1;
						dfs(i,j); // dfs를 통해 가장 큰 얼음의 영역 파악
						maxCnt = Math.max(maxCnt,iceCnt);
					}
				}
			}
		}
		
		System.out.println(totCnt);
		System.out.println(maxCnt);
		
	}
	
	private static int[][] divideAndRotate(int l) {
		int[][] temp = new int[n][n];
		
		for(int i=0;i<n;i+=l) {
			for(int j=0;j<n;j+=l) {
				for(int k=0;k<l;k++) {
					for(int m=0;m<l;m++){
						temp[i+m][j+l-k-1]  = map[i+k][j+m];
					}
				}
			}
		}
		return temp;
	}
	
	private static void bfs() { // 주변에 있는 얼음의 개수를 파악하여 녹이기
		
		boolean check[][] = new boolean[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]>0) {
					
					int cnt=0;
					for(int d=0;d<4;d++) {
						int nx = i+dx[d];
						int ny = j+dy[d];
						
						if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
						if(map[nx][ny]>0) cnt++; // 주변이 얼음일 경우 cnt++
					}
					if(cnt>=3) check[i][j]=true; // 주변에 있는 얼음의 개수가 3이상일 경우 true
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!check[i][j]) map[i][j]--; // false일 경우 얼음 녹이기
			}
		}
	}
	
	private static void dfs(int x, int y) {
		
		visited[x][y]=true;
				
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
			if(visited[nx][ny]) continue;
			
			if(map[nx][ny]>0) {
				iceCnt++; // 얼음의 개수 세기
				visited[nx][ny]=true;
				dfs(nx,ny);
			}
		}
	}
}
