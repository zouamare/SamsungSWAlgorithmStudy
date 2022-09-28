import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_5373 {
	static char[][] U = new char[3][3];
	static char[][] D = new char[3][3];
	static char[][] L = new char[3][3];
	static char[][] R = new char[3][3];
	static char[][] F = new char[3][3];
	static char[][] B = new char[3][3];
	
	static List<char[][]> UD4 = new ArrayList<>();
	static List<char[][]> LR4 = new ArrayList<>();
	static List<char[][]> FB4 = new ArrayList<>();
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// T = 큐브 돌릴 횟수
		int T = sc.nextInt();
		
		for(int tc = 0; tc < T; tc++)
		{
			// 큐브 초기화
			// U=w, D=y, L=g, R=b, F=r, B=o 
			reset();
			
			int N = sc.nextInt();
			
			for(int i = 0; i < N; i++)
			{
				// side는 돌릴 큐브 면, d 는 방향
				String temp = sc.next();
				char side = temp.charAt(0);
				char d = temp.charAt(1);
				
				// 돌릴 큐브 면 입력 받아서 처리
				// 반시계는 시계 3번 돌린거랑 똑같음
				switch(side)
				{
				case 'U':
					if(d == '+') turn(U, 1, d);
					else turn(U, 3, d);
					break;
				case 'D':
					if(d == '+')
					{
						SideTrun(D, d);
						turn(D, 3, d);
					}
					else turn(D, 1, d);
					break;
				case 'L':
					if(d == '+') turn(L, 1, d);
					else turn(L, 3, d);
					break;
				case 'R':
					if(d == '+')
					{
						SideTrun(R, d);
						turn(R, 3, d);
					}
					else turn(R, 1, d);
					break;
				case 'F':
					if(d == '+') turn(F, 1, d);
					else turn(F, 3, d);
					break;
				case 'B':
					if(d == '+')
					{
						SideTrun(B, d);
						turn(B, 3, d);
					}
					else turn(B, 1, d);
					break;
				}
				
			}
			
			for(int r = 0; r < 3; r++)
			{
				for(int c = 0; c < 3; c++)
				{
					System.out.print(U[r][c]);
				}
				System.out.println();
			}
			
		}
	}
	
	static void SideTrun(char[][] arr, char d)
	{
		if(d == '+')
		{
			char temp = arr[0][0];
			arr[0][0] = arr[2][0];
			arr[2][0] = arr[2][2];
			arr[2][2] = arr[0][2];
			arr[0][2] = temp;
			temp = arr[0][1];
			arr[0][1] = arr[1][0];
			arr[1][0] = arr[2][1];
			arr[2][1] = arr[1][2];
			arr[1][2] = temp;
		}
		else if(d == '-')
		{
			char temp = arr[0][0];
			arr[0][0] = arr[0][2];
			arr[0][2] = arr[2][2];
			arr[2][2] = arr[2][0];
			arr[2][0] = temp;
			temp = arr[0][1];
			arr[0][1] = arr[1][2];
			arr[1][2] = arr[2][1];
			arr[2][1] = arr[1][0];
			arr[1][0] = temp;
		}
	}
	
	static void turn(char[][] arr, int cnt, char d)
	{
		if(cnt == 0) return;
		
		// 현재 면 돌리기
		if((arr[1][1] == 'y'  || arr[1][1] == 'b' || arr[1][1] == 'o') && d == '+')
		{
			
		}
		else if((arr[1][1] == 'y'  || arr[1][1] == 'b' || arr[1][1] == 'o') && d == '-')
		{
			SideTrun(arr, '+');
			SideTrun(arr, '+');
			SideTrun(arr, '+');
		}
		else SideTrun(arr, '+');
		
		// 사이드부분 돌리기
		int t = 0;
		char[] temp;
		switch(arr[1][1])
		{
		case 'w':
		case 'y':
			if(arr[1][1] == 'y') t = 2;
			// R b, B o, L g 순서
			temp = UD4.get(0)[t];
			UD4.get(0)[t] = UD4.get(3)[t];
			UD4.get(3)[t] = UD4.get(2)[t];
			UD4.get(2)[t] = UD4.get(1)[t];
			UD4.get(1)[t] = temp;
			break;
			
		case 'g':
		case 'b':
			// g : w +, r +, y, o -
			// b : w -, r -, y ++, o +
			
			// 회전 시키기
			if(arr[1][1] == 'g')
			{
				SideTrun(U, '+');
				SideTrun(F, '+');
				SideTrun(B, '-');
			}
			
			if(arr[1][1] == 'b')
			{
				SideTrun(U, '-');
				SideTrun(F, '-');
				SideTrun(D, '+');
				SideTrun(D, '+');
				SideTrun(B, '+');
			}
			
			temp = LR4.get(0)[t];
			LR4.get(0)[t] = LR4.get(3)[t];
			LR4.get(3)[t] = LR4.get(2)[t];
			LR4.get(2)[t] = LR4.get(1)[t];
			LR4.get(1)[t] = temp;
			
			
			// g : w +, r +, y, o -
			// b : w -, r -, y ++, o +
			
			// 회전시킨거 원복
			if(arr[1][1] == 'g')
			{
				SideTrun(U, '-');
				SideTrun(F, '-');
				SideTrun(B, '+');
			}
			
			if(arr[1][1] == 'b')
			{
				SideTrun(U, '+');
				SideTrun(F, '+');
				SideTrun(D, '-');
				SideTrun(D, '-');
				SideTrun(B, '-');
			}
			break;
			
		case 'r':
		case 'o':
			// r : w ++, b +, y -, g -
			// o : w , b -, y +, g +
			
			// 회전 시키기
			if(arr[1][1] == 'r')
			{
				SideTrun(U, '+');
				SideTrun(U, '+');
				SideTrun(R, '+');
				SideTrun(D, '-');
				SideTrun(L, '-');
			}
			
			if(arr[1][1] == 'o')
			{
				SideTrun(R, '-');
				SideTrun(D, '+');
				SideTrun(L, '+');
			}
			
			temp = FB4.get(0)[t];
			FB4.get(0)[t] = FB4.get(3)[t];
			FB4.get(3)[t] = FB4.get(2)[t];
			FB4.get(2)[t] = FB4.get(1)[t];
			FB4.get(1)[t] = temp;
			
			// r : w ++, b +, y -, g -
			// o : w , b -, y +, g +
			
			// 회전 시킨거 원복
			if(arr[1][1] == 'r')
			{
				SideTrun(U, '-');
				SideTrun(U, '-');
				SideTrun(R, '-');
				SideTrun(D, '+');
				SideTrun(L, '+');
			}
			if(arr[1][1] == 'o')
			{
				SideTrun(R, '+');
				SideTrun(D, '-');
				SideTrun(L, '-');
			}
			break;
		}
		
		turn(arr, cnt-1, d);
	}
	
	static void reset()
	{
		for(int i = 0; i < 3; i++)
		{
			Arrays.fill(U[i], 'w');
			Arrays.fill(D[i], 'y');
			Arrays.fill(L[i], 'g');
			Arrays.fill(R[i], 'b');
			Arrays.fill(F[i], 'r');
			Arrays.fill(B[i], 'o');
		}
		
		//  0 - 1 - 2 - 3 - 0
		// U=w, D=y, L=g, R=b, F=r, B=o 
		
		// U, D 기준
		UD4.add(F);
		UD4.add(L);
		UD4.add(B);
		UD4.add(R);
		
		// L, R 기준
		LR4.add(U);
		LR4.add(F);
		LR4.add(D);
		LR4.add(B);
		
		// F, B 기준
		FB4.add(U);
		FB4.add(R);
		FB4.add(D);
		FB4.add(L);
	}
}
