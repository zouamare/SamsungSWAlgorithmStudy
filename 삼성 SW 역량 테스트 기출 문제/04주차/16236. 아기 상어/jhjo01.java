import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class BOJ_16236_아가상어_BFS {
	static int[] dr = {-1, 0, 0, 1}; // 상 좌 우 하
	static int[] dc = {0, -1, 1, 0}; // 상 좌 우 하
	static int[][] map;
	static int N, level, exp, cr, cc, day, minCnt = Integer.MAX_VALUE;
	static boolean[][] visited;
	static boolean find;
	
	static class Dist implements Comparable<Dist>
	{
		int r, c, dist=0;

		public Dist(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public int compareTo(Dist o) {
			
//			find = false;
//			visited = new boolean[N][N];
//			minCnt = Integer.MAX_VALUE;
//			pathfinder(cr, cc, this.r, this.c, 0);
//			int thisMinCnt = minCnt;
//			
//			
//			find = false;
//			visited = new boolean[N][N];
//			minCnt = Integer.MAX_VALUE;
//			pathfinder(cr, cc, o.r, o.c, 0);
//			int oMinCnt = minCnt;
//			
//			
//			this.dist = thisMinCnt;
//			o.dist = oMinCnt;
//			int a = Math.abs(this.dist - o.dist);
			
			if( (Math.abs(this.dist - o.dist) == 0 && this.r - o.r == 0) )
			{
				return this.c - o.c;
			}
			if(Math.abs(this.dist - o.dist) == 0)
			{
				return this.r - o.r;
			}
			
			return  this.dist - o.dist;
		}
		
		
		
//		if(Math.abs((Math.abs(this.r - cr) + Math.abs(this.c - cc)) - (Math.abs(o.r - cr) + Math.abs(o.c - cc))) == 0)
//		{
//			return this.r - o.r;
//		}
//		if(((Math.abs(this.r - cr) + Math.abs(this.c - cc)) - (Math.abs(o.r - cr) + Math.abs(o.c - cc)) == 0 && this.r - o.r == 0))
//		{
//			return this.c - o.c;
//		}
//		return (Math.abs(this.r - cr) + Math.abs(this.c - cc)) - (Math.abs(o.r - cr) + Math.abs(o.c - cc));
//	}

		@Override
		public String toString() {
			return "Dist [r=" + r + ", c=" + c + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		level = 2;
		exp = level;
		
		
		
		for(int r = 0; r < N; r++)
		{
			for(int c = 0; c < N; c++)
			{
				map[r][c] = sc.nextInt();
				if(map[r][c] == 9)
				{
					cr = r;
					cc = c;
				}
			}
		}
		
		find = true;
		while(find)
		{
			
		
		
			Queue<Dist> q = new ArrayDeque<>();
			Queue<Dist> compare = new LinkedList<>();
//			Queue<Dist> compare = new ArrayDeque<>();
			visited = new boolean[N][N];
			visited[cr][cc] = true;
			find = false;
			int limdist = 10000;
			q.offer(new Dist (cr,cc, 0));
			while(!q.isEmpty())
			{
				Dist dist = q.poll();
				int r = dist.r;
				int c = dist.c;
				int di = dist.dist;
				
				
				if((r != cr || c != cc) && map[r][c] < level && map[r][c] != 0)
				{
					compare.offer(new Dist(r, c, di));
					limdist = di;
				}
				
				
				
				for(int d = 0; d < 4; d++)
				{
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] <= level && di <= limdist)
					{
						visited[nr][nc] = true;
						q.offer(new Dist (nr, nc, di+1));
					}
				}
				
			}
			
			if(!compare.isEmpty())
			{
				Collections.sort((List<Dist>) compare);
				day += compare.peek().dist;
				map[cr][cc] = 0;
				cr = compare.peek().r;
				cc = compare.peek().c;
				map[cr][cc] = 9;
				exp--;
				if(exp==0) levelUp();
				find = true;
			
			
//			for(int r = 0; r < N; r++)
//			{
//				for(int c = 0; c < N; c++)
//				{
//					System.out.print(map[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println(day + ", lv: " + level + ", exp: " + exp);
//			System.out.println();
			}
		}
		
		System.out.println(day);
	}
	
	static void levelUp()
	{
		level++;
		exp = level;
	}
}



/*
 * 

4
1 0 1 0
6 0 5 0
1 0 0 0
9 0 2 2

ans = 12
 * 
 */
