package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두용액 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int left = 0;
		int right = N - 1;
		long min = Math.abs(arr[left] + arr[right]);
		int leftIdx = 0;
		int rightIdx = right;
		while(leftIdx < rightIdx) {
			long num = arr[leftIdx] + arr[rightIdx];
			if(Math.abs(num) < min) {
				left = leftIdx;
				right = rightIdx;
				min = Math.abs(num);
				if(num == 0) break;
			}
			if(num > 0) rightIdx -= 1;
			else leftIdx += 1;
		}
		System.out.println(arr[left] + " " + arr[right]);
	}
}
