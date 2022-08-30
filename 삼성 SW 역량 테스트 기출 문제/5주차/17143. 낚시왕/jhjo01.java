import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_17143 {
	
	static class Shark
	{
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	static Shark[][] shark;
	static int R, C, M, cc, ans=0, lastShark;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		shark = new Shark[R+1][C+1];
		lastShark = M;
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()),
					s = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), z = Integer.parseInt(st.nextToken());
			shark[r][c] = (new Shark(r, c, s, d, z));
		}
		
		cc = 0;
		
		for(int i = 0; i < C; i++)
		{
			// 낚시꾼 위치 이동
			cc++;
			
			// 상어잡기
			fishing();
			
			//상어이동
			moveShark();
		}
		
		System.out.println(ans);
	}
	
	static void moveShark()
	{
		Shark[][] nextShark = new Shark[R+1][C+1];
		
		for(int i = 1; i <= R; i++)
		{
			for(int j = 1; j <= C; j++)
			{
				if(shark[i][j] == null) continue;
				
				// 상어 이동
				int r = shark[i][j].r, c = shark[i][j].c, s = shark[i][j].s, d = shark[i][j].d, z = shark[i][j].z;
				int ns;
				switch(d)
				{
				case 1: // 상
					if(r == 1) d = 2;
					ns = s % (R * 2 - 2);
					
					if(ns == r-1)
					{
						r = r - ns;
						break;
					}
					// 남은 이동거리가 r-1보다 작다
					if( ns < r-1)
					{
						r = r - ns;
						break;
					}
					// 남은 이동거리가 r-1보다 클때
					if( ns > r-1 )
					{
						d = 2;
						ns = ns - (r - 1);
						r = r - (r - 1);
						// 남은 이동거리가 R보다 클때
						if( ns > R-1)
						{
							d = 1;
							ns = ns - (R-1);
							r = r + (R-1);
						}
						else
						{
							r = r + ns;
							break;
						}
						r = r - ns;
					}
					break;
					
				case 2: // 하
					if(r == R) d = 1;
					ns = s % (R * 2 - 2);
					
					if(R-1 == r + ns)
					{
						r = r + ns;
						break;
					}
					// 남은 이동거리가 R-1보다 작다
					if( R-1 > r + ns)
					{
						r = r + ns;
						break;
					}
					// 남은 이동거리가 R-1보다 클때
					if( R-1 < r + ns )
					{
						d = 1;
						ns = ns - (R - r);
						r = r + (R - r);
						// 남은 이동거리가 R보다 클때
						if( ns > r-1 )
						{
							d = 2;
							ns = ns - (R-1);
							r = r - (R-1);
						}
						else
						{
							r = r - ns;
							break;
						}
						
						r = r + ns;
					}
					break;
					
				case 3: // 우 
					if(c == C) d = 4;
					ns = s % (C * 2 - 2);
					
					
					if(C-1 == c + ns)
					{
						c = c + ns;
						break;
					}
					// 남은 이동거리가 R-1보다 작다
					if( C-1 > c + ns)
					{
						c = c + ns;
						break;
					}
					// 남은 이동거리가 R-1보다 클때
					if( C-1 < c + ns )
					{
						d = 4;
						ns = ns - (C - c);
						c = c + (C - c);
						// 남은 이동거리가 R보다 클때
						if( ns > c-1 )
						{
							d = 3;
							ns = ns - (C-1);
							c = c - (C-1);
						}
						else
						{
							c = c - ns;
							break;
						}
						
						c = c + ns;
					}
					break;
					
				case 4: // 좌
					int td = d, tc = c; 
					if(c == 1) d = 3;
					ns = s % (C * 2 - 2);
					
					if(ns == c-1)
					{
						c = c - ns;
						break;
					}
					// 남은 이동거리가 c-1보다 작다
					if( ns < c-1)
					{
						c = c - ns;
						break;
					}
					// 남은 이동거리가 c-1보다 클때
					if( ns > c-1 )
					{
						d = 3;
						ns = ns - (c - 1);
						c = c - (c - 1);
						// 남은 이동거리가 C보다 클때
						if( ns > C-1)
						{
							d = 4;
							ns = ns - (C-1);
							c = c + (C-1);
						}
						else
						{
							c = c + ns;
							break;
						}
						
						c = c - ns;
					}

					break;
				}
				
				if(nextShark[r][c] == null)
				{
					nextShark[r][c] = new Shark(r, c, s, d, z);
				}
				else
				{
					if(nextShark[r][c].z < z)
					{
						nextShark[r][c].r = r; nextShark[r][c].c = c; nextShark[r][c].d = d; nextShark[r][c].s = s; nextShark[r][c].z = z;
					}
				}
			}
		}
		
		shark = nextShark;
	}
	
	static void fishing()
	{
		for(int i = 1; i <= R; i++)
		{
			if(shark[i][cc] != null)
			{
				ans += shark[i][cc].z;
				shark[i][cc] = null;
				break;
			}
		}
	}
	
	
}
