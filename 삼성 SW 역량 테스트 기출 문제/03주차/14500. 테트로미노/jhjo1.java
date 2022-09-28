import java.util.Scanner;

public class BOJ_14500 {
	static int[][] map;
	static boolean[][] visited;
	static int N, M, max=0;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				visited[i][j] = true;
				getMax(i, j, 0, 0);
				visited[i][j] = false;
			}
		}
		
		
		System.out.println(max);
	}
	
	private static void getMax(int r, int c, int cnt, int sum)
	{
		if(cnt == 4)
		{
			max = Math.max(max, sum);
			return;
		}
		
		
		for(int i = 0; i < 4; i++)
		{
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			
			if(nr >= N || nr < 0 || nc >= M || nc < 0) continue;
			
			if(!visited[nr][nc])
			{
				if(cnt == 1)
				{
					visited[nr][nc] = true;
					getMax(r, c, cnt+1, sum + map[nr][nc]);
					visited[nr][nc] = false;
				}
				visited[nr][nc] = true;
				getMax(nr, nc, cnt+1, sum + map[r][c]);
				visited[nr][nc] = false;
			}
			 
		}
	}
	

	
}
