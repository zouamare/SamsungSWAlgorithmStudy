import java.io.*;
import java.util.*;

public class BOJ_16234 {
	static int[] dr = {1, 0}; // 하 우
	static int[] dc = {0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int L = sc.nextInt();
		int R = sc.nextInt();
		
		int[][] map = new int[N][N];
		
		
		for(int r = 0; r < N; r++)
		{
			for(int c = 0; c < N; c++)
			{
				map[r][c] = sc.nextInt();
			}
		}
		
		int day = 0;
		while(true)
		{
			
			int[][] alliance = new int[N][N];
			int aIdx = 1;
			// 각 위치마다 사방탐색
			for(int r = 0; r < N; r++)
			{
				for(int c = 0; c < N; c++)
				{
					// 상 하 좌 우
					for(int i = 0; i < 2; i++)
					{
						int nr = r + dr[i];
						int nc = c + dc[i];
						if(nr >= 0 && nr < N && nc >= 0 && nc < N)
						{
							int gap = Math.abs(map[nr][nc] - map[r][c]);
							
							// map[r][c]와 map[nr][nc]의 차이가 L이상 R이하일 때
							if(gap >= L && gap <= R)
							{
								// 둘이 같은 연합이면 continue
								// 둘중 하나가 0이 아니면서 서로 같으면
								if(alliance[r][c] != 0 && alliance[r][c] == alliance[nr][nc]) continue;
								
								// 둘다 연합이 없으면 새로운 연합 생성
								// 둘다 0임
								if(alliance[r][c] == 0 && alliance[nr][nc] == 0)
								{
									alliance[r][c] = aIdx;
									alliance[nr][nc] = aIdx;
									aIdx++;
									continue;
								}
								
								// 둘중에 하나만 연합이 있는 경우
									// alliance[r][c]가 0인 경우
								if(alliance[r][c] == 0 && alliance[nr][nc] != 0)
								{
									alliance[r][c] = alliance[nr][nc];
									continue;
								}
									// alliance[nr][nc]가 0인 경우
								else if(alliance[nr][nc] == 0 && alliance[r][c] != 0)
								{
									alliance[nr][nc] = alliance[r][c];
									continue;
								}
								
								// 둘다 연합이 있는 경우
								if(alliance[r][c] != alliance[nr][nc])
								{
									//먼저 생성된 연합으로 병합시킴
										// alliance[r][c]가 먼저 생성됨
									if(alliance[r][c] < alliance[nr][nc])
									{
										int target = alliance[nr][nc];
										for(int j = 0; j < N; j++)
										{
											for(int k = 0; k < N; k++)
											{
												if(alliance[j][k] == target) alliance[j][k] = alliance[r][c];
											}
										}
									}
										// alliance[nr][nc]가 먼저 생성됨
									else if(alliance[r][c] > alliance[nr][nc])
									{
										int target = alliance[r][c];
										for(int j = 0; j < N; j++)
										{
											for(int k = 0; k < N; k++)
											{
												if(alliance[j][k] == target) alliance[j][k] = alliance[nr][nc];
											}
										}
									}
								}
							}
						}
					}
				}
			} // 각 연합 구성 끝
			
			// 인구 이동 시작
			int[] cnt = new int[aIdx];
			int[] allianceSum  = new int[aIdx];
			
			// 생성된 연합 갯수만큼 반복
			for(int i = 1; i <= aIdx; i++)
			{
				// 전체 배열 돌면서 각 연합의 합과 연합 수 계산
				for(int r = 0; r < N; r++)
				{
					for(int c = 0; c < N; c++)
					{
						// 연합이 0이면 연합 없는거니까 continue
						if(alliance[r][c] == 0) continue;
						// 현재 체크하는 연합이랑 같으면 연합 합계에 더하고 카운트
						if(alliance[r][c] == i)
						{
							allianceSum[i] += map[r][c];
							cnt[i]++;
						}
					}
				}
			} // 연합에 속한 나라 갯수와 연합의 합 다 구함
			
			// 생성된 연합 갯수만큼 반복
			for(int i = 1; i <= aIdx; i++)
			{
				// 전체 배열 돌면서 각 연합의 인구 갱신
				for(int r = 0; r < N; r++)
				{
					for(int c = 0; c < N; c++)
					{
						// 연합이 0이면 연합 없는거니까 continue
						if(alliance[r][c] == 0) continue;
						// 현재 체크하는 연합이랑 같으면 연합 인구 갱신
						if(alliance[r][c] == i)
						{
							map[r][c] = allianceSum[i] / cnt[i];
						}
					}
				}
			} // 연합에 속한 나라의 인구 이동 완료
			
			if(aIdx == 1) break; 
			day++;
			
//			for(int r = 0; r < N; r++)
//			{
//				for(int c = 0; c < N; c++)
//				{
//					System.out.print(map[r][c] + " ");
//				}
//				System.out.println();
//			}
			
		}
		
		System.out.println(day);
	}
}
