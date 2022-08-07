import java.util.Scanner;

public class BOJ_14503 {
	static int r, c, d;
	static int[][] map;
	static boolean[][] sweep;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		
		map = new int[N][M];
		sweep = new boolean[N][M];
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
				map[i][j] = sc.nextInt();
		
		// [r][c], d 0상 1우 2하 3좌
		int ans = sweep();
		
		System.out.println(ans);
		
	}
	
	private static int sweep()
	{
		int cnt = 1;
		sweep[r][c] = true;
		int turnCnt = 0;
		int backCnt = 0;
		
		
		
		while(true)
		{
//			for(int i = 0; i < map.length; i++)
//			{
//				for(int j = 0; j < map[i].length; j++)
//				{
//					System.out.print(map[i][j]);
//				}
//				System.out.print("  ");
//				for(int j = 0; j < map[i].length; j++)
//				{
//					System.out.print(sweep[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			// 후진
			if(turnCnt == 4)
			{
				turn();
				turn();
				if(move())
				{
					backCnt = 0;
				}
				else backCnt++;
				turn();
				turn();
				turnCnt = 0;
			}
			
			if(backCnt == 4) break;
			
			
			if(checkLeft())
			{
				turn();
				move();
				cnt++;
				turnCnt = 0;
				continue;
			}
			turn();
			turnCnt++;
			
			
		}
		
		return cnt;
	}
	
	private static boolean move()
	{
		boolean result = false;
		switch(d)
		{
		case 0:
			if(map[r-1][c] != 1)
			{
				r--;
				sweep[r][c] = true;
				result = true;
			}
			break;
		case 1:
			if(map[r][c+1] != 1)
			{
				c++;
				sweep[r][c] = true;
				result = true;
			}
			break;
		case 2:
			if(map[r+1][c] != 1)
			{
				r++;
				sweep[r][c] = true;
				result = true;
			}
			break;
		case 3:
			if(map[r][c-1] != 1)
			{
				c--;
				sweep[r][c] = true;
				result = true;
			}
			break;
		}
		return result;
		
	}
	
	private static boolean checkLeft()
	{
		switch(d)
		{
		case 0:
			if(sweep[r][c-1] == false && map[r][c-1] != 1) return true;
			break;
		case 1:
			if(sweep[r-1][c] == false && map[r-1][c] != 1) return true;
			break;
		case 2:
			if(sweep[r][c+1] == false && map[r][c+1] != 1) return true;
			break;
		case 3:
			if(sweep[r+1][c] == false && map[r+1][c] != 1) return true;
			break;
		}
		
		return false;
	}
	
	
	private static void turn()
	{
		if(d == 0)
		{
			d = 3;
			return;
		}
		d = d - 1;
		return;
	}
	
}
