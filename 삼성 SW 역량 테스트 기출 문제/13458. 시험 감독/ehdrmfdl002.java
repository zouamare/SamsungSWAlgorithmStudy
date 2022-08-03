package solo.study;

import java.util.Scanner;

public class 시험감독 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int [n];
		for(int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int viewer = sc.nextInt();
		int subViewer = sc.nextInt();
		long cnt = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] <= viewer) {
				cnt++;
				continue;
			}
			else {
				cnt++;
				arr[i] -= viewer;
				if(arr[i] % subViewer == 0) {
					cnt += arr[i] / subViewer;
				}
				else if(arr[i] % subViewer != 0) {
					cnt += arr[i] / subViewer + 1;
				}
			}
		}
		System.out.println(cnt);
	}
}
