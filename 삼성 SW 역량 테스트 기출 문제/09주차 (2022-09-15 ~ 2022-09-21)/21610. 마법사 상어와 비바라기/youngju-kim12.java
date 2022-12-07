package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N21610 {
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N,M,d,s;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q;
	static Queue<Point> list;
	static int[] dx = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dy = {0,-1,-1,0,1,1,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		q = new LinkedList<>();
		list = new LinkedList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q.offer(new Point(N-1,0));
		q.offer(new Point(N-1,1));
		q.offer(new Point(N-2,0));
		q.offer(new Point(N-2,1));
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			moveClouds();
		}
		
		int tot=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				tot+=map[i][j];
			}
		}
		System.out.println(tot);
	}
	
	private static void moveClouds() {
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			int x = p.x;
			int y = p.y;
			
			int nx = (x+(dx[d]*(s%N))+N)%N;
			int ny = (y+(dy[d]*(s%N))+N)%N;
			
			visited[nx][ny]=true;
			map[nx][ny]++;
			
			list.add(new Point(nx,ny));
			
		}
		
		waterMagic();
	}
	
	private static void waterMagic() {
		
		while(!list.isEmpty()) {
			int cnt=0;
			Point p = list.poll();
			
			int x = p.x;
			int y = p.y;
			
			if(isTrue(x-1,y-1) && map[x-1][y-1]>0 ) cnt++;
			if(isTrue(x-1,y+1) && map[x-1][y+1]>0 ) cnt++;
			if(isTrue(x+1,y-1) && map[x+1][y-1]>0 ) cnt++;
			if(isTrue(x+1,y+1) && map[x+1][y+1]>0 ) cnt++;
			
			map[x][y]+=cnt;
			
		}
		
		makeNewClouds();
	}
	
	private static void makeNewClouds() {
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]) {
					if(map[i][j]>=2) {
						map[i][j]-=2;
						q.offer(new Point(i,j));
					}
				}
			}
		}
	}
	
	private static boolean isTrue(int x, int y) {
		
		if(x<0 || x>=N || y<0 || y>=N) return false;
		return true;
	}
}
