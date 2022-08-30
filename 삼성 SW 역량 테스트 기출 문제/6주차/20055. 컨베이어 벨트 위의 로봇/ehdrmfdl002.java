package solo.study;

import java.util.Scanner;

public class BOJ_20055_컨베이어벨트위의로봇 {
	static int n, k;
	static int[] belt;
	static boolean[] robot;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		belt = new int[n * 2];
		robot = new boolean[n];
		
		for(int i = 0; i < n * 2; i++) {
			belt[i] = sc.nextInt();
		}
		
		int cnt = 0;
		while(true) {
			cnt++;
			bMove();
			rMove();
			
			if(isEnd()) {
				break;
			}
		}
		
		System.out.println(cnt);
	}
	
	private static boolean isEnd() {
		int cnt = 0;
		for(int i = 0; i < n * 2; i++) {
			if(belt[i] == 0)
				cnt++;
			
			if(cnt >= k)
				return true;
		}
		return false;
	}
	
	private static void rMove() {
		if(robot[n - 1])
			robot[n - 1] = false;
		
		for(int i = n - 2; i >= 0; i--){
			if(robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
				robot[i] = false;
				robot[i + 1] = true;
				belt[i + 1]--;
			}
		}
		
		if(!robot[0] && belt[0] >= 1){
	           robot[0] = true;
	           belt[0]--;
	    }
	}
	
	private static void bMove() {
		 int tmp = belt[2 * n - 1];

	        for(int i = 2 * n - 1 ; i > 0 ; i--){
	            belt[i] = belt[i - 1];
	        }
	        belt[0] = tmp;

	        for(int i = n - 1 ; i > 0; i--){
	            robot[i] = robot[i-1];
	        }
	        
	        // n에서 로봇이 내리기 때문에 이동 후에는 0에 로봇이 없음
	        robot[0] = false;
		
	}
}
