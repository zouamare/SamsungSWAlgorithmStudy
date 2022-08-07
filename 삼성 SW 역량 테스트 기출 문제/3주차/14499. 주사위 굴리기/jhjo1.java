import java.util.Scanner;

public class BOJ_14499 {
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};
	//static int[][] dice = {{1, 6}, {2, 5}, {4, 3}};
	static int[][] dice = {{0, 0}, {0, 0}, {0, 0}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int map[][] = new int[N][M];
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		int O = sc.nextInt();
		int[] order = new int[O];
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < O; i++)
		{
			order[i] = sc.nextInt();
		}
		
		// 1우 2좌 3상 4하
		for(int i = 0; i < O; i++)
		{
			
			
			int nr = r + dr[order[i]];
			int nc = c + dc[order[i]];
			
			if(nr >= N || nr < 0 || nc >= M || nc < 0) continue;
			dice(order[i]);
			if(map[nr][nc] == 0) map[nr][nc] = dice[0][1]; 
			else
			{
				dice[0][1] = map[nr][nc];
				map[nr][nc] = 0;
			}
			
			r = r + dr[order[i]];
			c = c + dc[order[i]];
				
			
			
			System.out.println(dice[0][0]);
		}
		
		
		
		
		
		
	}
	
	private static void dice(int d)
	{
		int tmp1;
		int tmp2;
		switch(d)
		{
		case 1: // 오른쪽
			tmp1 = dice[0][0];
			tmp2 = dice[0][1];
			dice[0][0] = dice[2][0];
			dice[0][1] = dice[2][1];
			dice[2][0] = tmp2;
			dice[2][1] = tmp1;
			break;
		case 2: // 왼쪽
			tmp1 = dice[0][0];
			tmp2 = dice[0][1];
			dice[0][0] = dice[2][1];
			dice[0][1] = dice[2][0];
			dice[2][0] = tmp1;
			dice[2][1] = tmp2;
			break;
		case 3: // 위쪽
			tmp1 = dice[0][0];
			tmp2 = dice[0][1];
			dice[0][0] = dice[1][1];
			dice[0][1] = dice[1][0];
			dice[1][0] = tmp1;
			dice[1][1] = tmp2;
			break;
		case 4: // 아래쪽
			tmp1 = dice[0][0];
			tmp2 = dice[0][1];
			dice[0][0] = dice[1][0];
			dice[0][1] = dice[1][1];
			dice[1][0] = tmp2;
			dice[1][1] = tmp1;
			break;
		}
	}
	
	
}
