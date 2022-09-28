import java.util.Scanner;

public class BOJ_14890 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int L = sc.nextInt();
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				map[i][j] = sc.nextInt();
			}
		}
				
		int ans = 0;
		
		outer : for(int i = 0; i < N; i++)
		{
			boolean[] checkR = new boolean[N];
			boolean[] checkC = new boolean[N];
			boolean[] setLR = new boolean[N];
			boolean[] setLC = new boolean[N];
			boolean[] next = new boolean[2];
			boolean[] nextReverse = new boolean[2];
			
			
			
			// 순방향
			for(int j = 0; j < N-1; j++)
			{	
				///////// R
				if(!next[0])
				{
					// 차이가 2 이상이면 다음으로
					if(next[0] && next[1]) continue outer;
					if(Math.abs(map[i][j+1] - map[i][j]) > 1) next[0] = true;
					
					// 차이가 0이면 지나감
					if(Math.abs(map[i][j] - map[i][j+1]) == 0)
					{
						checkR[j] = true;
						checkR[j+1] = true;
					}
					
					// 차이가 -1이면 현재자리 false
					if(map[i][j+1] - map[i][j] == -1) checkR[j] = false;
					
					// 차이가 1이면 경사로 놓을 수 있는지 확인
					if(map[i][j+1] - map[i][j] == 1)
					{
						int lCnt = 0;
						for(int l = j; l > j-L; l--)
						{
							if(l < 0) continue;
							if(map[i][j] == map[i][l])
							{
								lCnt++;
							}
							else next[0] = true;
						}
						if(lCnt == L)
						{
							checkR[j] = true;
							checkR[j+1] = true;
							for(int l = j; l > j-L; l--)
							{
								setLR[l] = true;
							}
						}
					}
				}
				
				/////// C
				if(!next[1])
				{
					// 차이가 2 이상이면 다음으로
					if(next[0] && next[1]) continue outer;
					if(Math.abs(map[j+1][i] - map[j][i]) > 1) next[1] = true;
					
					// 차이가 0이면 지나감
					if(Math.abs(map[j][i] - map[j+1][i]) == 0)
					{
						checkC[j] = true;
						checkC[j+1] = true;
					}
					
					// 차이가 -1이면 현재자리 false
					if(map[j+1][i] - map[j][i] == -1) checkC[j] = false;
					
					// 차이가 1이면 경사로 놓을 수 있는지 확인
					if(map[j+1][i] - map[j][i] == 1)
					{
						int lCnt = 0;
						for(int l = j; l > j-L; l--)
						{
							if(l < 0) continue;
							if(map[j][i] == map[l][i])
							{
								lCnt++;
							}
							else
							{
								checkC[j] = false;
								next[1] = true;
							}
						}
						if(lCnt == L)
						{
							checkC[j] = true;
							checkC[j+1] = true;
							for(int l = j; l > j-L; l--)
							{
								setLC[l] = true;
							}
						}
					}
				}
			}
			
			// 역방향
			
			for(int j = N-1; j > 0; j--)
			{
				/////////////// R
				if(!nextReverse[0])
				{
					// 차이가 2 이상이면 다음으로
					if(nextReverse[0] && nextReverse[1]) continue outer;
					if(Math.abs(map[i][j-1] - map[i][j]) > 1) nextReverse[0] = true;
					
					// 차이가 0이면 지나감
					if(Math.abs(map[i][j] - map[i][j-1]) == 0)
					{
						checkR[j] = true;
							
						if(map[i][j-1] - map[i][j] != -1)
							checkR[j-1] = true;
					}
					
					// 차이가 1이면 경사로 놓을 수 있는지 확인
					if(map[i][j-1] - map[i][j] == 1)
					{
						int lCnt = 0;
						for(int l = j; l < j+L; l++)
						{
							if(l > N-1) continue;
							if(map[i][j] == map[i][l])
							{
								if(setLR[l] == true)
								{
									nextReverse[0] = true;
									break;
								}
								lCnt++;
							}
							else nextReverse[0] = true;
						}
						if(lCnt == L)
						{
							checkR[j] = true;
							checkR[j-1] = true;
							for(int l = j; l < j+L; l++)
							{
								setLR[l] = true;
							}
						}
//						else if(!checkR[j-1]) continue outer;
						else if(!checkR[j-1]) nextReverse[0] = true;
					}
				}
				
				
				/////////////// C
				if(!nextReverse[1])
				{
					// 차이가 2 이상이면 다음으로
					if(nextReverse[0] && nextReverse[1]) continue outer;
					if(Math.abs(map[j-1][i] - map[j][i]) > 1) nextReverse[1] = true;
					
					// 차이가 0이면 지나감
					if(Math.abs(map[j][i] - map[j-1][i]) == 0)
					{
						checkC[j] = true;
							
						if(map[j-1][i] - map[j][i] != -1)
							checkC[j-1] = true;
					}
					
					// 차이가 1이면 경사로 놓을 수 있는지 확인
					if(map[j-1][i] - map[j][i] == 1)
					{
						int lCnt = 0;
						for(int l = j; l < j+L; l++)
						{
							if(l > N-1) continue;
							if(map[j][i] == map[l][i])
							{
								if(setLC[l] == true)
								{
									nextReverse[1] = true;
									break;
								}
								lCnt++;
							}
							else nextReverse[1] = true;
						}
						if(lCnt == L)
						{
							checkC[j] = true;
							checkC[j-1] = true;
							for(int l = j; l < j+L; l++)
							{
								setLC[l] = true;
							}
						}
//						else if(!checkC[j-1]) continue outer;
						else if(!checkC[j-1]) nextReverse[1] = true;
					}
				}
			}
			
			
			
			
			
			
			boolean ansR = true;
			boolean ansC = true;
			for(int j = 0; j < N; j++)
			{
				if(checkR[j]) continue;
				ansR = false;
				break;
			}
			for(int j = 0; j < N; j++)
			{
				if(checkC[j]) continue;
				ansC = false;
				break;
			}
			if(ansR) ans++;
			if(ansC) ans++;
			
			
			
		}
		
		System.out.println(ans);
	
	}
}
