import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458 {
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(bf.readLine()); // 시험장의 수
		long sum = 0; // 필요한 감독관의 수
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringTokenizer st2 = new StringTokenizer(bf.readLine());
		
		int obs = Integer.parseInt(st2.nextToken()); // 총감독
		int subObs = Integer.parseInt(st2.nextToken()); //보조감독
				
		while(st.hasMoreTokens())
		{
			int temp = Integer.parseInt(st.nextToken()); //첫번째 호출
			temp = temp - obs; // 총 감독관은 무조건 1명
			sum++;
			
			// temp가 0보다 작거나 같으면 총감독관 1명으로 가능
			if(temp <= 0) continue;

			sum = sum + temp / subObs;
			if(temp % subObs > 0) sum++;
		}

		System.out.println(sum);
		
		
		
//		int size = sc.nextInt(); // 시험장의 수
//		int[] testSite = new int[size]; // 시험장별 수험생 수
//		int obs, subObs; // 감독, 보조감독
//		long sum = 0; // 필요한 감독관의 수
		
//		for(int i = 0; i < size; i++)
//		{
//			testSite[i] = sc.nextInt();
//		}
//		obs = sc.nextInt();
//		subObs = sc.nextInt();
//		
//		for(int i = 0; i < size; i++)
//		{
//			int temp = testSite[i];
//			temp = temp - obs; // 총 감독관은 무조건 1명
//			sum++;
//			
//			// temp가 0보다 작거나 같으면 총감독관 1명으로 가능
//			if(temp <= 0) continue;
//
//			sum = sum + temp / subObs;
//			if(temp % subObs > 0) sum++;
//			
//			
//		}
//		
//		System.out.println(sum);
	}
}
