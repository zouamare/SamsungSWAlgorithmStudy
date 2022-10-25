package algo;

import java.util.Scanner;

public class BOJ_17609_회문 {
	static int num;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			String s = sc.next();
			num = 2;
			check(false, s, 0, s.length() - 1);
			System.out.println(num);
		}
	}
	
	private static void check(boolean isUsed, String s, int start, int last) {
		for (int i = start; i < s.length(); i++) {
			if(i >= last) break;
			if(s.charAt(i) != s.charAt(last)) {
				if(isUsed) return;
				if(s.charAt(i) == s.charAt(last - 1)) {
					check(true, s, i + 1, last - 2);
				}
				if(num == 2 && s.charAt(i + 1) == s.charAt(last)) {
					check(true, s, i + 2, last - 1);
				}
				return;
			}
			last--;
		}
		if(isUsed) num = 1;
		else num = 0;
		return;
	}
}
