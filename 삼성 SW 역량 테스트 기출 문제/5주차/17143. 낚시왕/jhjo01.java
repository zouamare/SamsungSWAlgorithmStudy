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

public class BOJ_17143_V2 {
	
	static class Shark implements Comparable<Shark>
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

		@Override
		public int compareTo(Shark o) {
			if(o.c == cc)
			{
				return 1;
			}
			return -1;
		}
//		@Override
//		public int compareTo(Shark o) {
//			return this.r - o.r;
//		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Shark other = (Shark) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}	
	}
	


	static ArrayList<Shark> shark, cSharkList;
	static int R, C, M, cc, ans=0;
	
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		R = sc.nextInt();
//		C = sc.nextInt();
//		M = sc.nextInt();
		
		
		shark = new ArrayList<Shark>();
		cSharkList = new ArrayList<Shark>(); // 낚시꾼과 같은 열에 있는 상어 리스트
		
		for(int i = 0; i < M; i++)
		{
//			int r = sc.nextInt(), c = sc.nextInt(), s = sc.nextInt(), d = sc.nextInt(), z = sc.nextInt();
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()),
					s = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), z = Integer.parseInt(st.nextToken());
			shark.add(new Shark(r, c, s, d, z));
		}
		
		cc = 0;
		
		for(int i = 0; i < C; i++)
		{
			// 낚시꾼 위치 이동
			cc++;
			
			// 낚시꾼과 같은 열에 있는 상어 리스트 구하기
			Collections.sort(shark);
//			cSharkList = new ArrayList<Shark>(); // 낚시꾼과 같은 열에 있는 상어 리스트
//			for(int j = 0; j < shark.size(); j++) if(shark.get(j).c == cc) cSharkList.add(shark.get(j));
			
			// 상어잡기
			fishing();
			
			//상어이동
			moveShark();
		}
		
		System.out.println(ans);
		
		
		
	}
	
	static void moveShark()
	{
		ArrayList<Shark> nextShark = new ArrayList<Shark>();
		
		while(!shark.isEmpty())
		{
			int last = shark.size() - 1;
			// 상어 이동
			int r = shark.get(last).r, c = shark.get(last).c, s = shark.get(last).s, d = shark.get(last).d;
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
//				// 남은 이동거리가 r-1보다 작다
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
					
//					d = 2;
					r = r - ns;
				}
				
				
				
//				for(int i = 0; i < ns; i++)
//				{
//					if(r == 1) d = 2;
//					if(r == R) d = 1;
//					
//					if(d == 1) r--;
//					else r++;
//				}
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
				
//				for(int i = 0; i < ns; i++)
//				{
//					if(r == 1) d = 2;
//					if(r == R) d = 1;
//					
//					if(d == 1) r--;
//					else r++;
//				}
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
				
//				for(int i = 0; i < ns; i++)
//				{
//					if(c == C) d = 4;
//					if(c == 1) d = 3;
//					
//					if(d == 3) c++;
//					else c--;
//				}
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
					
//					d = 3;
					c = c - ns;
				}
				
				
//				for(int i = 0; i < ns; i++)
//				{
//					if(c == C) d = 4;
//					if(c == 1) d = 3;
//					
//					if(d == 3) c++;
//					else c--;
//				}
				
//				System.out.println(r);
//				int x = tc + s;
//				int div = x / (M-1);
//				int mod = x % (M-1);
//				if(div%2 == 1)
//				{
//					tc = M - 1 - mod;
//					td = 4;
//				}
//				else tc = mod;
//				System.out.println(tc);
				break;
			}
			
			shark.get(last).r = r; shark.get(last).c = c; shark.get(last).d = d;
			
			// 자리 겹치는 상어 처리
			// 비어있으면 첫 상어니까 그냥 추가
			if(nextShark.isEmpty())
			{
				nextShark.add(shark.get(last));
				shark.remove(last);
			}
			else
			{
				// 현재 상어랑 같은 위치에 있는 상어 찾기
				int overlapIdx = nextShark.indexOf(shark.get(last));
				// 상어를 찾은 경우
				if(overlapIdx >= 0)
				{
					if (shark.get(last).z > nextShark.get(overlapIdx).z)
					{
						nextShark.remove(overlapIdx);
						nextShark.add(shark.get(last));
					}
				}
				// 상어를 못찾은 경우
				else
				{
					nextShark.add(shark.get(last));
				}
				shark.remove(last);
			}
			
		}
		
		shark = nextShark;
	}
	
	static void fishing()
	{
		if(shark.size() == 0) return;
		if(shark.get(0).c != cc) return;
		
		int min = 0;
		for(int i = 1, size = shark.size(); i < size; i++)
		{
			if(shark.get(i).c != cc) break;
			if(shark.get(min).r > shark.get(i).r)
			{
				min = i;
			}
		}
		ans = ans + shark.get(min).z;
		shark.remove(min);
		
//		if(!cSharkList.isEmpty())
//		{
//			Collections.sort(cSharkList);
//			if(shark.indexOf(cSharkList.get(0)) >= 0)
//			{
//				ans = ans + shark.get(shark.indexOf(cSharkList.get(0))).z;
//				shark.remove(shark.indexOf(cSharkList.get(0)));
//			}
//		}
	}
	
	
}
