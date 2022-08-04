import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_3190 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 배열 크기 입력
		int size = sc.nextInt();
		int[][] map = new int[size+2][size+2];
		
		// 배열 바깥 2로 채우고 안쪽 0으로 채움
		for(int i = 0; i < size+2; i++)
			Arrays.fill(map[i], 2);
		for(int i = 1; i < size+1; i++)
			Arrays.fill(map[i], 1, size+1, 0);
		
		// 사과 위치 입력
		int a = sc.nextInt();
		for (int i = 0; i < a; i++)
		{
			int c = sc.nextInt();
			int r = sc.nextInt();
			map[c][r] = 1;
		}
		
		// 뱀의 방향 전환 정보 입력
		int c = sc.nextInt();
		int[] time = new int[c];
		char[] turn = new char[c];
		
		for (int i = 0; i < c; i++)
		{
			time[i] = sc.nextInt();
			turn[i] = sc.next().charAt(0);
		}
		
		// 입력 확인
//		for (int i = 0; i < size+2; i++)
//		{
//			for(int j = 0; j < size+2; j++)
//			{
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		
//		for(int i = 0; i < c; i++)
//		{
//			System.out.println(time[i] + " " + turn[i]);
//		}
		

		
		
		
		// 뱀이 보고있는 방향으로 전진
		//   - 앞에 아무것도 없으면 머리, 꼬리 한칸씩 이동
		//   - 꼬리가 달려있으면 꼬리도 따라와야함
		// 전진할 방향에 벽 또는 자신이 있는지 확인
		//   - 지금까지 경과된 시간 return
		// 전진할 방향에 사과가 있는지 확인
		//   - 머리는 한칸 이동, 꼬리 이동 안함
		// 시간이 되면 방향 전환
		//   - 방향전환시 
		
		
		// 게임 시작
		int currX = 1;	// 뱀의 현재 x 좌표
		int currY = 1;	// 뱀의 현재 y 좌표
		int timeCnt = 0; // 시간 체크용
		int direction = 4; // 뱀의 진행 방향 1:상 2:하 3:좌 4:우  default:우
		
		List<Integer> tailX = new ArrayList<>(); // 뱀의 꼬리 좌표 저장용
		List<Integer> tailY = new ArrayList<>(); // 뱀의 꼬리 좌표 저장용
		
		
		//
		// 전진하면 머리가 움직인 위치랑 꼬리 끝 위치랑 바꿔주면 오케이?
		//
		
		boolean live = true;
		while(live)
		{
			timeCnt++;
			// 뱀이 보고있는 방향으로 전진
			// 우선순위 방향전환 -> 벽 or 자신이 있는지 검사 -> 전진 -> 사과먹기
			switch(direction)
			{
			case 1: // 상 y-1
				if(map[currY - 1][currX] == 2) live = false; // 앞이 벽이나 자신이면 뱀 사망
				if(map[currY - 1][currX] != 1) // 사과가 아니면 그냥 전진
				{
					if(tailX.size() == 0) // 사과를 하나도 안먹어서 길이가 1인 상태
					{
						map[currY][currX] = 0;
						map[--currY][currX] = 2;
					}
					else // 사과를 하나라도 먹어서 길이가 2이상인 상태
					{
						map[tailY.get(0)][tailX.get(0)] = 0;
						tailX.remove(0);
						tailY.remove(0);
						tailX.add(currX);
						tailY.add(currY);
						map[--currY][currX] = 2;
					}
					
					
					
				}
				else // 사과면 꼬리는 그대로 있고 전진해서 길이 늘림
				{
					tailX.add(currX);
					tailY.add(currY);
					map[--currY][currX] = 2;
				}
				break;
			case 2: // 하 y+1
				if(map[currY + 1][currX] == 2) live = false; // 앞이 벽이나 자신이면 뱀 사망
				if(map[currY + 1][currX] != 1) // 사과가 아니면 그냥 전진
				{
					if(tailX.size() == 0) // 사과를 하나도 안먹어서 길이가 1인 상태
					{
						map[currY][currX] = 0;
						map[++currY][currX] = 2;
					}
					else // 사과를 하나라도 먹어서 길이가 2이상인 상태
					{
						map[tailY.get(0)][tailX.get(0)] = 0;
						tailX.remove(0);
						tailY.remove(0);
						tailX.add(currX);
						tailY.add(currY);
						map[++currY][currX] = 2;
					}
					
					
					
				}
				else // 사과면 꼬리는 그대로 있고 전진해서 길이 늘림
				{
					tailX.add(currX);
					tailY.add(currY);
					map[++currY][currX] = 2;
				}
				break;
			case 3: // 좌 x-1
				if(map[currY][currX - 1] == 2) live = false; // 앞이 벽이나 자신이면 뱀 사망
				
				
				if(map[currY][currX - 1] != 1) // 사과가 아니면 그냥 전진
				{
					if(tailX.size() == 0) // 사과를 하나도 안먹어서 길이가 1인 상태
					{
						map[currY][currX] = 0;
						map[currY][--currX] = 2;
					}
					else // 사과를 하나라도 먹어서 길이가 2이상인 상태
					{
						map[tailY.get(0)][tailX.get(0)] = 0;
						tailX.remove(0);
						tailY.remove(0);
						tailX.add(currX);
						tailY.add(currY);
						map[currY][--currX] = 2;
					}
					
					
					
				}
				else // 사과면 꼬리는 그대로 있고 전진해서 길이 늘림
				{
					tailX.add(currX);
					tailY.add(currY);
					map[currY][--currX] = 2;
				}
				break;
			case 4: // 우 x+1
				if(map[currY][currX + 1] == 2) live = false; // 앞이 벽이나 자신이면 뱀 사망
				
				
				if(map[currY][currX + 1] != 1) // 사과가 아니면 그냥 전진
				{
					if(tailX.size() == 0) // 사과를 하나도 안먹어서 길이가 1인 상태
					{
						map[currY][currX] = 0;
						map[currY][++currX] = 2;
					}
					else // 사과를 하나라도 먹어서 길이가 2이상인 상태
					{
						map[tailY.get(0)][tailX.get(0)] = 0;
						tailX.remove(0);
						tailY.remove(0);
						tailX.add(currX);
						tailY.add(currY);
						map[currY][++currX] = 2;
					}
					
					
					
				}
				else // 사과면 꼬리는 그대로 있고 전진해서 길이 늘림
				{
					tailX.add(currX);
					tailY.add(currY);
					map[currY][++currX] = 2;
				}
				
				
				break;
				
				
			}

			// 방향 전환
			// 방향 전환할 시간이 됐는지 검사
			for(int i = 0; i < time.length; i++)
			{
				if(timeCnt == time[i])
				{
					switch(direction)
					{
					case 1:
						if(turn[i] == 'L') direction = 3;
						if(turn[i] == 'D') direction = 4;
						break;
					case 2:
						if(turn[i] == 'L') direction = 4;
						if(turn[i] == 'D') direction = 3;
						break;
					case 3:
						if(turn[i] == 'L') direction = 2;
						if(turn[i] == 'D') direction = 1;
						break;
					case 4:
						if(turn[i] == 'L') direction = 1;
						if(turn[i] == 'D') direction = 2;
						break;
					}
				}
			}
			
			
//			for (int k = 0; k < size+2; k++)
//			{
//				for(int j = 0; j < size+2; j++)
//				{
//					System.out.print(map[k][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		
//		System.out.println("Time : " + timeCnt);
		System.out.println(timeCnt);
	
	}
}
