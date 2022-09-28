import java.util.Scanner;
public class BOJ_13460 {
	
	static char[][] deepCopy(char[][] arr){
		char[][] result = new char[arr.length][arr[0].length];
	    for (char i=0; i<arr.length; i++) {
	      result[i] = arr[i].clone();
	    }
	    return result;
	  }
	
	
	static int moveUp(char[][] arr, int recu)
	{
		int move = 0;
		// 위부터 아래로 탐색
				for(int i = 1; i < arr[0].length - 1; i++)
				{
					if(arr.length == 3) break;
					// 배열 순회시 패딩 1칸 고려
					int k = 1;
					int index = arr.length - 1;
					
					// 배열 내 이동이 없을 때 까지 while loop
					while(true)
					{
						// 현재칸이 .이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
						if((arr[k+1][i] == 'R' || arr[k+1][i] == 'B') && arr[k][i] == '.')
						{
							char temp;
							temp = arr[k][i];
							arr[k][i] = arr[k+1][i];
							arr[k+1][i] = temp;
							move = 1;
						}
						// 현재칸이 O이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
						else if((arr[k+1][i] == 'R' || arr[k+1][i] == 'B') && arr[k][i] == 'O')
						{
							// 빨간색이 구멍에 들어가면 1 return
							if(arr[k+1][i] == 'R')
							{
								for(int l = k+2; l < arr.length-1; l++)
								{
									if(arr[l][i] == '#') break;
									if(arr[l][i] == 'B') return 99;
								}
								return 13 - recu;
							}
							// 파란색이 구멍에 들어가면 99 return
							if(arr[k+1][i] == 'B')
							{
								return 99;
							}
						}
						else index--;
			
						k++;
						if(k == arr.length-2)
						{
							k = 1;
							if(index == 2) break;
							index = arr.length - 1;
						}
					}
				}
				
				if(move == 0) return 99;
				recu--;
				if(recu == 2) return 99;
				return escape(deepCopy(arr) , recu);
	}
	
	static int moveDown(char[][] arr, int recu)
	{
		int move = 0;
		// 위부터 아래로 탐색
		for(int i = 0; i < arr[0].length-1; i++)
		{
			if(arr.length == 3) break;
			// 배열 순회시 패딩 1칸 고려
			int k = arr.length - 2;
			int index = arr.length - 1;
			
			// 배열 내 이동이 없을 때 까지 while loop
			while(true)
			{
				// 현재칸이 .이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
				if((arr[k-1][i] == 'R' || arr[k-1][i] == 'B') && arr[k][i] == '.')
				{
					char temp;
					temp = arr[k][i];
					arr[k][i] = arr[k-1][i];
					arr[k-1][i] = temp;
					move = 1;
				}
				// 현재칸이 O이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
				else if((arr[k-1][i] == 'R' || arr[k-1][i] == 'B') && arr[k][i] == 'O')
				{
					// 빨간색이 구멍에 들어가면 1 return
					if(arr[k-1][i] == 'R')
					{
						for(int l = k-2; l > 0; l--)
						{
							if(arr[l][i] == '#') break;
							if(arr[l][i] == 'B') return 99;
						}
						return 13 - recu;
					}
					// 파란색이 구멍에 들어가면 11 return
					if(arr[k-1][i] == 'B')
					{
						return 99;
					}
				}
				else index--;
	
				k--;
				if(k == 1)
				{
					k = arr.length - 2;
					if(index == 2) break;
					index = arr.length - 1;
				}
			}
		}
		
		if(move == 0) return 99;
		recu--;
		if(recu == 2) return 99;
		return escape(deepCopy(arr) , recu);
	}
	
	static int moveLeft(char[][] arr, int recu)
	{
		int move = 0;
		// 위부터 아래로 탐색
		for(int k = 1; k < arr.length-1; k++)
		{
			
			if(arr[0].length == 3) break;
			// 배열 순회시 패딩 1칸 고려
			int i = 1;
			int index = arr[0].length - 1;
			
			// 배열 내 이동이 없을 때 까지 while loop
			while(true)
			{
				// 현재칸이 .이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
				if((arr[k][i+1] == 'R' || arr[k][i+1] == 'B') && arr[k][i] == '.')
				{
					char temp;
					temp = arr[k][i];
					arr[k][i] = arr[k][i+1];
					arr[k][i+1] = temp;
					move = 1;
				}
				// 현재칸이 O이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
				else if((arr[k][i+1] == 'R' || arr[k][i+1] == 'B') && arr[k][i] == 'O')
				{
					// 빨간색이 구멍에 들어가면 1 return
					if(arr[k][i+1] == 'R')
					{
						for(int l = i+2; l < arr[0].length-1; l++)
						{
							if(arr[k][l] == '#') break;
							if(arr[k][l] == 'B') return 99;
						}
						return 13 - recu;
					}
					// 파란색이 구멍에 들어가면 11 return
					if(arr[k][i+1] == 'B')
					{
						return 99;
					}
				}
				else index--;
				
				
				i++;
				if(i == arr[0].length-2)
				{
					i = 1;
					if(index == 2) break;
					index = arr[0].length - 1;
				}
			}
			
		}
		
		if(move == 0) return 99;
		recu--;
		if(recu == 2) return 99;
		return escape(deepCopy(arr) , recu);
	}
	
	static int moveRight(char[][] arr, int recu)
	{
		int move = 0;
		// 위부터 아래로 탐색
		for(int k = 1; k < arr.length-1; k++)
		{
			if(arr[0].length == 3) break;
			// 배열 순회시 패딩 1칸 고려
			int i = arr[0].length - 2;
			int index = arr[0].length - 1;
			
			// 배열 내 이동이 없을 때 까지 while loop
			while(true)
			{
				// 현재칸이 .이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
				if((arr[k][i-1] == 'R' || arr[k][i-1] == 'B') && arr[k][i] == '.')
				{
					char temp;
					temp = arr[k][i];
					arr[k][i] = arr[k][i-1];
					arr[k][i-1] = temp;
					move = 1;
				}
				// 현재칸이 O이고 다음 칸이 R 또는 B 이면 아래꺼 한칸 위로 올림
				else if((arr[k][i-1] == 'R' || arr[k][i-1] == 'B') && arr[k][i] == 'O')
				{
					// 빨간색이 구멍에 들어가면 1 return
					if(arr[k][i-1] == 'R')
					{
						for(int l = i-2; l > 0; l--)
						{
							if(arr[k][l] == '#') break;
							if(arr[k][l] == 'B') return 99;
						}
						return 13 - recu;
					}
					// 파란색이 구멍에 들어가면 11 return
					if(arr[k][i-1] == 'B')
					{
						return 99;
					}
				}
				else index--;
	
				i--;
				if(i == 1)
				{
					i = arr[0].length - 2;
					if(index == 2) break;
					index = arr[0].length - 1;
				}
			}
		}
		
		if(move == 0) return 99;
		recu--;
		if(recu == 2) return 99;
		return escape(deepCopy(arr) , recu);
	}
	
	
	static int escape(char[][] arr, int recu)
	{
		int min = 99;
		
		min = Math.min(min, moveUp(deepCopy(arr), recu));
		min = Math.min(min, moveDown(deepCopy(arr), recu));
		min = Math.min(min, moveLeft(deepCopy(arr), recu));
		min = Math.min(min, moveRight(deepCopy(arr), recu));
				
		return min;
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 배열 사이즈 입력
		int size1 = sc.nextInt();
		int size2 = sc.nextInt();
		sc.nextLine();
		char[][] arr = new char[size1][size2];
		
		for(int i = 0; i < size1; i++)
		{
			String temp = sc.nextLine();
			for(int j = 0; j < size2; j++)
			{
				arr[i][j] = temp.charAt(j);
			}
		}
		sc.close();
		
		for(int i = 0; i < size1; i++)
		{
			for(int j = 0; j < size2; j++)
			{
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		
		
		int result = escape(arr, 12);
		if(result == 99) System.out.println(-1);
		else System.out.println(result);
		
	}
}
