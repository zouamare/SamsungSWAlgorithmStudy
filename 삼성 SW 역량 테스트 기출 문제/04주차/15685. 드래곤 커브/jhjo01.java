import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_15685 {
	static int cr, cc;
	static int[][] map;
	static Deque<Integer> stack;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		map = new int[101][101];
		
		for(int i = 0; i < N; i++)
		{
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();
			stack = new ArrayDeque<>();
			stack.push(d); // 시작할때 d를 stack에 넣어주고 시작
			cr = y;
			cc = x;
			
			// 초기값 세팅 0세대는 직접 바꿔준다
			map[cr][cc] = 1;
			// 가야할 방향이 0이면 c+1, 1이면 r-1, 2이면 c-1, 3이면 r+1
			// 여기서는 초기상태인 출발점에서 시작하니까 입력값이 가야할 방향
			switch(d)
			{
			case 0:
				cc++;
				map[cr][cc] = 1;
				break;
			case 1:
				cr--;
				map[cr][cc] = 1;
				break;
			case 2:
				cc--;
				map[cr][cc] = 1;
				break;
			case 3:
				cr++;
				map[cr][cc] = 1;
				break;
			}
			
			// 1세대부터 재귀호출을 이용해 stack에 값 추가
			dragon(cr, cc, d, g);
		}
		
		// 모든 커브를 돌았으면 정사각형을 카운트한다
		int ans = 0;
		for(int r = 0; r < 100; r++)
		{
			for(int c = 0; c < 100; c++)
			{
				// 크기가 1x1인 정사각형이니까 왼쪽 위 부터 각 칸의 오른쪽,아래, 오른쪽아래가 모두 1이면 정사각형
				if(map[r][c] == 1 &&
					map[r][c+1] == 1 &&
					map[r+1][c] == 1 &&
					map[r+1][c+1] == 1)
				{
					ans++;
				}
			}
		}
		System.out.println(ans);
		
//		for(int r = 0; r < 10; r++)
//		{
//			for(int c = 0; c < 10; c++)
//			{
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}
	}
	
	static void dragon (int x, int y, int d, int g)
	{
		// 기저조건 입력된 세대를 모두 다 돌면 return
		if(g == 0) return;
		
		//스택 deepcopy하는법 몰라서 하나는 계산용 하나는 복사용으로 두개 만들었음
		Deque<Integer> tmp = new ArrayDeque<>(); // 계산용
		Deque<Integer> tmp2 = new ArrayDeque<>(); // 복사용
		
		// 반복 돌면서 Stack에서 pop한걸 deque에 add해서 넣어줌 Deque 짱이다
		for(int i = stack.size()-1; i >= 0; i--)
		{
			int temp = stack.pop();
			tmp.add(temp);
			tmp2.add(temp);
		}
		// tmp2는 복사만 하는 용도
		stack = tmp2;
		
		// Stack의 크기만큼 반복을 돌아주면서 Stack에 Curve 추가?
		// Curve할 때 마다 스택의 길이는 2배가 된다.
		for(int i = tmp.size()-1; i >= 0; i--)
		{
			curve(tmp.pop());
		}
		
		// 재귀호출
		dragon(cr, cc, d, g-1);
	}
	
	// 
	static void curve(int d)
	{
		// 가야할 방향이 0이면 c+1, 1이면 r-1, 2이면 c-1, 3이면 r+1
		// 여기서는 출발점에서 가야할 방향으로 이미 이동을 한 상태니까
		// 가야할 방향은 시계방향으로 돌린 방향
		switch(d)
		{
		case 0:
			cr--;
			map[cr][cc] = 1;
			stack.push(1);
			break;
		case 1:
			cc--;
			map[cr][cc] = 1;
			stack.push(2);
			break;
		case 2:
			cr++;
			map[cr][cc] = 1;
			stack.push(3);
			break;
		case 3:
			cc++;
			map[cr][cc] = 1;
			stack.push(0);
			break;
		}
	}
	
}
