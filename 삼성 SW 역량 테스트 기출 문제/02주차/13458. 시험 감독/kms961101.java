package ex.test.bj;

import java.util.*;
import java.io.*;

public class Bj_13458 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long cnt = 0;
		
		for(int i = 0; i < N; i++) {
			if(arr[i] <= B) {
				cnt++;
				continue;
			}
			else {
				int temp = arr[i] - B;
				cnt += 1;
				if(temp % C == 0) {
					cnt += temp / C;
				}else {
					cnt += temp / C + 1;

				}

			}
		}
		System.out.println(cnt);
	}
}
