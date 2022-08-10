import java.util.Scanner;

public class BOJ_14891 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = 8;
		int[] gear1 = new int[N];
		int[] gear2 = new int[N];
		int[] gear3 = new int[N];
		int[] gear4 = new int[N];
		String temp;
		temp = sc.nextLine();
		for(int i = 0; i < N; i++)
		{
			gear1[i] = temp.charAt(i) - 48;
		}
		temp = sc.nextLine();
		for(int i = 0; i < N; i++)
		{
			gear2[i] = temp.charAt(i) - 48;
		}
		temp = sc.nextLine();
		for(int i = 0; i < N; i++)
		{
			gear3[i] = temp.charAt(i) - 48;
		}
		temp = sc.nextLine();
		for(int i = 0; i < N; i++)
		{
			gear4[i] = temp.charAt(i) - 48;
		}
		
		int K = sc.nextInt();
		int[][] cmd = new int[K][2];
		
		for(int i = 0; i < K; i++)
		{
			cmd[i][0] = sc.nextInt();
			cmd[i][1] = sc.nextInt();
		}
		
		int gear1R = 2;
		int gear2L = 6;
		int gear2R = 2;
		int gear3L = 6;
		int gear3R = 2;
		int gear4L = 6;
		
		
		for(int i = 0; i < K; i++)
		{
			switch(cmd[i][0])
			{
			case 1:
				if(cmd[i][1] == 1) // -
				{
					if(gear1[gear1R] != gear2[gear2L])
					{
						gear1R = (gear1R - 1 + 8) % 8;
						
						if(gear2[gear2R] != gear3[gear3L])
						{
							gear2L = (gear2L + 1 + 8) % 8;
							gear2R = (gear2R + 1 + 8) % 8;
							
							if(gear3[gear3R] != gear4[gear4L])
							{
								gear3L = (gear3L - 1 + 8) % 8;
								gear3R = (gear3R - 1 + 8) % 8;
								gear4L = (gear4L + 1 + 8) % 8;
							}
							else
							{
								gear3L = (gear3L - 1 + 8) % 8;
								gear3R = (gear3R - 1 + 8) % 8;
							}
						}
						else
						{
							gear2L = (gear2L + 1 + 8) % 8;
							gear2R = (gear2R + 1 + 8) % 8;
						}
					}
					else gear1R = (gear1R - 1 + 8) % 8;
				}
				else // +
				{
					if(gear1[gear1R] != gear2[gear2L])
					{
						gear1R = (gear1R + 1 + 8) % 8;
						
						if(gear2[gear2R] != gear3[gear3L])
						{
							gear2L = (gear2L - 1 + 8) % 8;
							gear2R = (gear2R - 1 + 8) % 8;
							
							if(gear3[gear3R] != gear4[gear4L])
							{
								gear3L = (gear3L + 1 + 8) % 8;
								gear3R = (gear3R + 1 + 8) % 8;
								gear4L = (gear4L - 1 + 8) % 8;
							}
							else
							{
								gear3L = (gear3L + 1 + 8) % 8;
								gear3R = (gear3R + 1 + 8) % 8;
							}
						}
						else
						{
							gear2L = (gear2L - 1 + 8) % 8;
							gear2R = (gear2R - 1 + 8) % 8;
						}
					}
					else gear1R = (gear1R + 1 + 8) % 8;
				}
				break;
			case 2:
				if(cmd[i][1] == 1) // -
				{
					if(gear2[gear2R] != gear3[gear3L])
					{
						if(gear1[gear1R] != gear2[gear2L])
						{
							gear1R = (gear1R + 1 + 8) % 8;
						}
						gear2L = (gear2L - 1 + 8) % 8;
						gear2R = (gear2R - 1 + 8) % 8;
						
						if(gear3[gear3R] != gear4[gear4L])
						{
							gear3L = (gear3L + 1 + 8) % 8;
							gear3R = (gear3R + 1 + 8) % 8;
							gear4L = (gear4L - 1 + 8) % 8;
						}
						else
						{
							gear3L = (gear3L + 1 + 8) % 8;
							gear3R = (gear3R + 1 + 8) % 8;
						}
					}
					else
					{
						if(gear1[gear1R] != gear2[gear2L])
						{
							gear1R = (gear1R + 1 + 8) % 8;
						}
						gear2L = (gear2L - 1 + 8) % 8;
						gear2R = (gear2R - 1 + 8) % 8;
					}
					
				}
				else // +
				{
					if(gear2[gear2R] != gear3[gear3L])
					{
						if(gear1[gear1R] != gear2[gear2L])
						{
							gear1R = (gear1R - 1 + 8) % 8;
						}
						gear2L = (gear2L + 1 + 8) % 8;
						gear2R = (gear2R + 1 + 8) % 8;
						
						if(gear3[gear3R] != gear4[gear4L])
						{
							gear3L = (gear3L - 1 + 8) % 8;
							gear3R = (gear3R - 1 + 8) % 8;
							gear4L = (gear4L + 1 + 8) % 8;
						}
						else
						{
							gear3L = (gear3L - 1 + 8) % 8;
							gear3R = (gear3R - 1 + 8) % 8;
						}
					}
					else
					{
						if(gear1[gear1R] != gear2[gear2L])
						{
							gear1R = (gear1R - 1 + 8) % 8;
						}
						gear2L = (gear2L + 1 + 8) % 8;
						gear2R = (gear2R + 1 + 8) % 8;
					}
				}
				break;
			case 3:
				if(cmd[i][1] == 1) // -
				{
					if(gear2[gear2R] != gear3[gear3L])
					{
						if(gear3[gear3R] != gear4[gear4L])
						{
							gear4L = (gear4L + 1 + 8) % 8;
						}
						gear3L = (gear3L - 1 + 8) % 8;
						gear3R = (gear3R - 1 + 8) % 8;
						
						if(gear1[gear1R] != gear2[gear2L])
						{
							gear2L = (gear2L + 1 + 8) % 8;
							gear2R = (gear2R + 1 + 8) % 8;
							gear1R = (gear1R - 1 + 8) % 8;
						}
						else
						{
							gear2L = (gear2L + 1 + 8) % 8;
							gear2R = (gear2R + 1 + 8) % 8;
						}
					}
					else
					{
						if(gear3[gear3R] != gear4[gear4L])
						{
							gear4L = (gear4L + 1 + 8) % 8;
						}
						gear3L = (gear3L - 1 + 8) % 8;
						gear3R = (gear3R - 1 + 8) % 8;
					}
				}
				else // +
				{
					if(gear2[gear2R] != gear3[gear3L])
					{
						if(gear3[gear3R] != gear4[gear4L])
						{
							gear4L = (gear4L - 1 + 8) % 8;
						}
						gear3L = (gear3L + 1 + 8) % 8;
						gear3R = (gear3R + 1 + 8) % 8;
						
						if(gear1[gear1R] != gear2[gear2L])
						{
							gear2L = (gear2L - 1 + 8) % 8;
							gear2R = (gear2R - 1 + 8) % 8;
							gear1R = (gear1R + 1 + 8) % 8;
						}
						else
						{
							gear2L = (gear2L - 1 + 8) % 8;
							gear2R = (gear2R - 1 + 8) % 8;
						}
					}
					else
					{
						if(gear3[gear3R] != gear4[gear4L])
						{
							gear4L = (gear4L - 1 + 8) % 8;
						}
						gear3L = (gear3L + 1 + 8) % 8;
						gear3R = (gear3R + 1 + 8) % 8;
					}
				}
				break;
			case 4:
				if(cmd[i][1] == 1) // -
				{
					if(gear3[gear3R] != gear4[gear4L])
					{
						gear4L = (gear4L - 1 + 8) % 8;
						
						if(gear2[gear2R] != gear3[gear3L])
						{
							gear3L = (gear3L + 1 + 8) % 8;
							gear3R = (gear3R + 1 + 8) % 8;
							if(gear1[gear1R] != gear2[gear2L])
							{
								gear2L = (gear2L - 1 + 8) % 8;
								gear2R = (gear2R - 1 + 8) % 8;
								gear1R = (gear1R + 1 + 8) % 8;
							}
							else
							{
								gear2L = (gear2L - 1 + 8) % 8;
								gear2R = (gear2R - 1 + 8) % 8;
							}
						}
						else
						{
							gear3L = (gear3L + 1 + 8) % 8;
							gear3R = (gear3R + 1 + 8) % 8;
						}
					}
					else gear4L = (gear4L - 1 + 8) % 8;
				}
				else // +
				{
					if(gear3[gear3R] != gear4[gear4L])
					{
						gear4L = (gear4L + 1 + 8) % 8;
						
						if(gear2[gear2R] != gear3[gear3L])
						{
							gear3L = (gear3L - 1 + 8) % 8;
							gear3R = (gear3R - 1 + 8) % 8;
							if(gear1[gear1R] != gear2[gear2L])
							{
								gear2L = (gear2L + 1 + 8) % 8;
								gear2R = (gear2R + 1 + 8) % 8;
								gear1R = (gear1R - 1 + 8) % 8;
							}
							else
							{
								gear2L = (gear2L + 1 + 8) % 8;
								gear2R = (gear2R + 1 + 8) % 8;
							}
						}
						else
						{
							gear3L = (gear3L - 1 + 8) % 8;
							gear3R = (gear3R - 1 + 8) % 8;
						}
					}
					else gear4L = (gear4L + 1 + 8) % 8;
				}
				break;
			}
			
		}
			
		int sum = gear1[(gear1R - 2 + 8) % 8] * 1 + gear2[(gear2R - 2 + 8) % 8] * 2+
				gear3[(gear3R - 2 + 8) % 8] * 4+ gear4[(gear4L + 2 + 8) % 8] * 8;
		System.out.println(sum);
		
	}
}
