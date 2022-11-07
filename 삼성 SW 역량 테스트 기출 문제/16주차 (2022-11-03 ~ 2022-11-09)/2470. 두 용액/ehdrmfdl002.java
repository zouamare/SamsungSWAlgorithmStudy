package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_2470_두용액 {
	static int N, start, end, minAns, maxAns;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		start = 0;
		end = arr.length - 1;

		Arrays.sort(arr);

		solve(start, end);

		System.out.println(arr[minAns] + " " + arr[maxAns]);
	}

	public static void solve(int start, int end) {
		int min = Integer.MAX_VALUE;
		while (start < end) {
			int num = arr[start] + arr[end];
			int mid = Math.abs(num);
			if (min > mid) {
				min = mid;
				minAns = start;
				maxAns = end;
			}
			if (num < 0)
				start += 1;
			else
				end -= 1;
		}
	}
}