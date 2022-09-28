import java.util.Scanner;

public class BOJ_14501 {
	static int N, maxPay, tmp;
	static int[] T, P;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		T = new int[N];
		P = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		
		subSet(0, 0, 0);
		
		System.out.println(maxPay);
	}
	
	private static void subSet(int start, int day, int pay)
	{
		// 기저조건
		if(day > N) return;
		
		maxPay = Math.max(maxPay, pay);
		
		for(int i = start; i < N; i++)
		{
			tmp = T[i];
			if(i + tmp > N) continue;
			subSet(i + tmp, day + tmp, pay + P[i]);
		}
	}
	
}
