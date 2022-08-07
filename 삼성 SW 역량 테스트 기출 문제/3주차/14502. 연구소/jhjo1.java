import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14502 {
	static int N, M, R, max=0;
	static int[] data, numbers;
	static int[][] map, arr;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		data = new int[N*M];
		numbers = new int[3];
		
		int idx = 0;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				map[i][j] = sc.nextInt();
				data[idx++] = i*10 + j;
			}
		}
		
		
		comb(0, 0);
		System.out.println(max);
		
	}
	
	
	private static void virus(int r, int c)
	{
//		for(int i = 0; i < N; i++)
//		{
//			for(int j = 0; j < M; j++)
//			{
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		arr[r][c] = 2;
		if(r+1 < N && arr[r+1][c] == 0)
		{
			virus(r+1, c);
		}
		if(r > 0 && arr[r-1][c] == 0)
		{
			virus(r-1, c);
		}
		if(c+1 < M && arr[r][c+1] == 0)
		{
			virus(r, c+1);
		}
		if(c > 0 && arr[r][c-1] == 0)
		{
			virus(r, c-1);
		}

		return;
		
		
	}
	
	
	private static void comb(int cnt, int start) // cnt : 직전까지 뽑은 조합에 포함된 수의 개수, start : 시도할 수의 시작 위치
	{
		if(cnt == 3)
		{
			arr = deepCopy(map);
			
			for(int i = 0; i < numbers.length; i++)
			{
				arr[numbers[i]/10][numbers[i]%10] = 1;
			}
			
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < M; j++)
				{
					if(arr[i][j] == 2) virus(i, j);
				}
			}
			
			int maxcnt = 0;
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < M; j++)
				{
					if(arr[i][j] == 0) maxcnt++;
				}
			}
			max = Math.max(maxcnt, max);
			return;
		}
		// 가능한 모든 수에 대해 시도(input 배열의 가능한 수 시도)
		// start 부터 처리시 중복 수 추출 방지 및 순서가 다른 조합 추출 방지
		for (int i = start; i < N*M; i++)
		{
			// start 위치부터 처리했으므로 중복체크 필요없음!!
			if(map[data[i]/10][data[i]%10] == 1 || map[data[i]/10][data[i]%10] == 2) continue;
			numbers[cnt] = data[i];
			
			// 다음 수 뽑으러 가기
			comb(cnt + 1, i + 1); // start는 시작위치만 결정 i가 돌면서 조합 생성함
		}
	}
	
	private static int[][] deepCopy(int[][] arr){
	    int[][] result = new int[arr.length][arr[0].length];
	    for (int i=0; i<arr.length; i++) {
	      result[i] = arr[i].clone();
	    }
	    return result;
	  }
}
