import java.util.Scanner;

public class BOJ_12100 {
	
	static int[][] deepCopy(int[][] arr){
	    int[][] result = new int[arr.length][arr[0].length];
	    for (int i=0; i<arr.length; i++) {
	      result[i] = arr[i].clone();
	    }
	    return result;
	  }
	
	
	static int moveUp(int[][] arr, int recu)
	{
		int max = 0;
		
		// 위부터 아래로 탐색
		for(int i = 0; i < arr.length; i++)
		{
			int k = 0;
			int move = arr.length;
			int[] mergeAble = new int[arr.length]; // 병합 했는지 안했는지
			
			// 배열 내 이동이 없을 때 까지 while loop
			while(true)
			{
				max = Math.max(max, arr[k][i]);
				// 현재칸이 0이면 아래꺼 한칸 위로 올림
				if(arr[k][i] == 0 && arr[k+1][i] != 0)
				{
					int temp;
					temp = arr[k][i];
					arr[k][i] = arr[k+1][i];
					arr[k+1][i] = temp;
					if(mergeAble[k+1] == 1)
					{
						mergeAble[k+1] = 0;
						mergeAble[k] = 1;
					}
				}
				// 아래칸이랑 현재 칸이랑 같은 값이면 아래칸 0으로 만들고 현재칸 2x
				else if(arr[k+1][i] == arr[k][i] && arr[k][i] != 0 && mergeAble[k] == 0 && mergeAble[k+1] == 0)
				{
					arr[k][i] *= 2;
					arr[k+1][i] = 0;
					mergeAble[k] = 1;
				}
				else move--;
	
				k++;
				if(k == arr.length-1)
				{
					k = 0;
					if(move == 1) break;
					move = arr.length;
				}
				
			}
		}
		recu--;
		if(recu == 1) return max;
		return Math.max(max, merge(deepCopy(arr) , recu));
		
	}
	
	static int moveDown(int[][] arr, int recu)
	{
		int max = 0;
		
		// 아래부터 위로 탐색
		for(int i = 0; i < arr.length; i++)
		{
			int k = arr.length - 1;
			int move = arr.length;
			int[] mergeAble = new int[arr.length];
			
			// 배열 내 이동이 없을 때 까지 while loop
			while(true)
			{
				max = Math.max(max, arr[k][i]);
				// 현재칸이 0이면 위에꺼 한칸 아래로 내림
				if(arr[k][i] == 0 && arr[k-1][i] != 0)
				{
					int temp;
					temp = arr[k][i];
					arr[k][i] = arr[k-1][i];
					arr[k-1][i] = temp;
					if(mergeAble[k-1] == 1)
					{
						mergeAble[k-1] = 0;
						mergeAble[k] = 1;
					}
				}
				// 윗칸이랑 현재 칸이랑 같은 값이면 현재칸 0으로 만들고 현재칸 2x
				else if(arr[k-1][i] == arr[k][i] && arr[k][i] != 0 && mergeAble[k] == 0 && mergeAble[k-1] == 0)
				{
					arr[k][i] *= 2;
					arr[k-1][i] = 0;
					mergeAble[k] = 1;
				}
				else move--;
	
				k--;
				if(k == 0)
				{
					k = arr.length - 1;
					if(move == 1) break;
					move = arr.length;
				}
				
			}
		}
		recu--;
		if(recu == 1) return max;
		return Math.max(max, merge(deepCopy(arr) , recu));
	}
	
	static int moveLeft(int[][] arr, int recu)
	{
		int max = 0;
		
		// 왼쪽부터 오른쪽으로 탐색
		for(int k = 0; k < arr.length; k++)
		{
			int i = 0;
			int move = arr.length;
			int[] mergeAble = new int[arr.length];
			
			// 배열 내 이동이 없을 때 까지 while loop
			while(true)
			{
				max = Math.max(max, arr[k][i]);
				// 현재칸이 0이면 오른쪽꺼 한칸 왼쪽으로 이동
				if(arr[k][i] == 0 && arr[k][i+1] != 0)
				{
					int temp;
					temp = arr[k][i];
					arr[k][i] = arr[k][i+1];
					arr[k][i+1] = temp;
					if(mergeAble[i+1] == 1)
					{
						mergeAble[i+1] = 0;
						mergeAble[i] = 1;
					}
				}
				// 오른쪽칸이랑 현재 칸이랑 같은 값이면 오른쪽칸 0으로 만들고 현재칸 2x
				else if(arr[k][i+1] == arr[k][i] && arr[k][i] != 0 && mergeAble[i] == 0 && mergeAble[i+1] == 0)
				{
					arr[k][i] *= 2;
					arr[k][i+1] = 0;
					mergeAble[i] = 1;
				}
				else move--;
	
				i++;
				if(i == arr.length-1)
				{
					i = 0;
					if(move == 1) break;
					move = arr.length;
				}
				
			}
		}
		recu--;
		if(recu == 1) return max;
		return Math.max(max, merge(deepCopy(arr) , recu));
	}
	
	static int moveRight(int[][] arr, int recu)
	{
		int max = 0;
		// 오른쪽부터 왼쪽으로 탐색
		for(int k = 0; k < arr.length; k++)
		{
			int i = arr.length - 1;
			int move = arr.length;
			int[] mergeAble = new int[arr.length]; // 병합 했는지 안했는지
			
			// 배열 내 이동이 없을 때 까지 while loop
			while(true)
			{
				max = Math.max(max, arr[k][i]);
				// 현재칸이 0이면 왼쪽꺼 한칸 오른쪽으로 이동
				if(arr[k][i] == 0 && arr[k][i-1] != 0)
				{
					int temp;
					temp = arr[k][i];
					arr[k][i] = arr[k][i-1];
					arr[k][i-1] = temp;
					if(mergeAble[i-1] == 1)
					{
						mergeAble[i-1] = 0;
						mergeAble[i] = 1;
					}
				}
				// 왼쪽칸이랑 현재 칸이랑 같은 값이면 왼쪽칸 0으로 만들고 현재칸 2x
				else if(arr[k][i-1] == arr[k][i] && arr[k][i] != 0 && mergeAble[i] == 0 && mergeAble[i-1] == 0)
				{
					arr[k][i] *= 2;
					arr[k][i-1] = 0;
					mergeAble[i] = 1;
				}
				else move--;
	
				i--;
				if(i == 0)
				{
					i = arr.length - 1;
					if(move == 1) break;
					move = arr.length;
				}
				
			}
		}
		recu--;
		if(recu == 1) return max;
		return Math.max(max, merge(deepCopy(arr) , recu));
	}
	
	static int merge(int[][] arr, int recu)
	{
		int max = 0;
		
		max = Math.max(max, moveUp(deepCopy(arr), recu));
		max = Math.max(max, moveDown(deepCopy(arr), recu));
		max = Math.max(max, moveLeft(deepCopy(arr), recu));
		max = Math.max(max, moveRight(deepCopy(arr), recu));
		
		
		return max;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		int[][] arr = new int[size][size];
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				arr[i][j] = sc.nextInt();
			}
		}
		
		sc.close();
		// merge(배열, 실행횟수+1)
		int max = 0;
		if(size != 1)
		{
			max = merge(arr, 6);
		}
		if(size == 1) 
		{
			max = arr[0][0];
		}
		System.out.println(max);

	}

}
