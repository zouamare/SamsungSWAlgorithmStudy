package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class N20056 {
	static class fireBall{
		int r;
		int c;
		int m;
		int d;
		int s;
		
		public fireBall(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	static int N,M,K;
	static int dr[] = {-1,-1,0,1,1,1,0,-1};
	static int dc[] = {0,1,1,1,0,-1,-1,-1};
	static List<fireBall> list;
	static Queue<fireBall>[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list =new ArrayList<>();
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			list.add(new fireBall(r,c,m,d,s));
		}
		
		map = new Queue[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = new LinkedList<>(); 
			}
		}
		while(K-->0) {
			move();
			multiMove();
		}
		int sum=0;
		for (fireBall f : list) {
            sum += f.m;
        }
		System.out.println(sum);
	}
	
	private static void move() {
		for(fireBall f: list) {
			f.r = (N + f.r + dr[f.d] * (f.s % N)) % N;
	        f.c = (N + f.c + dc[f.d] * (f.s % N)) % N;
			
			map[f.r][f.c].add(f);
		}
	}
	
	private static void multiMove() {
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j].size()>=2) {
					int mSum=0;
					int sSum=0;
					boolean odd = true;
					boolean even = true;
					int totCnt=map[i][j].size();
					
					while(!map[i][j].isEmpty()) {
						fireBall f = map[i][j].poll();
						mSum+=f.m;
						sSum+=f.s;
						
						if(f.d%2==0) {
							odd=false;
						}else {
							even=false;
						}
						list.remove(f);
					}
					
					int nm = mSum/5;
					
					if(nm==0) continue;
					
					int ns = sSum/totCnt;
					
					if(odd|even) {
						for(int d=0;d<8;d+=2) {
							list.add(new fireBall(i,j,nm,d,ns));
						}
					}else {
						for(int d=1;d<8;d+=2) {
							list.add(new fireBall(i,j,nm,d,ns));
						}
					}
				}else {
					map[i][j].clear();
				}
			}
		}
	}
}

