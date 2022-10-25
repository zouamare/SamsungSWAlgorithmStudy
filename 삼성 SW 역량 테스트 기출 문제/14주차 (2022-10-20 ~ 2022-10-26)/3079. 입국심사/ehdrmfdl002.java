package solo.study;

import java.io.*;
import java.util.*;

/*
 * 파라메트릭 서치
 * 1. 정답이 될 수 있는 범위를 설정
 * 2. start에는 x의 최소값, end에는 최대값을 설정
 * 3. 둘의 중간 값을 구해서 답이 되는지 판단
 * 4. 결과에 따라 start와 end의 범위를 줄여 반복
 */

public class BOJ_3079_입국심사 {
	static int n, m; // 심사대 갯수, 인원
	static int time[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		time = new int[n];
		for(int i = 0; i < n; i++) {
			time[i] = Integer.parseInt(br.readLine());
		}
		
		long start = 1;
		// long end = Long.MAX_VALUE;
		long end = 1_000_000_000_000_000_000L;

		
		while(start <= end) {
			long mid = (start + end) / 2;
			// mid초 일 때 각 입국 심사대에 보낼 수 있는 사람의 수를 카운트
			long cnt = 0;
			for(int i = 0; i < n; i++) {
				cnt += (mid / time[i]);
				if(cnt >= m)
					break;
			}
			
			if(cnt >= m)
				end = mid - 1;
			else
				start = mid + 1;
		}
		
		System.out.println(start);
		
	}
}
