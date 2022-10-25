package solo.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ_17609_회문 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
//		for(int t = 0; t < T; t++) {
//			String str = br.readLine();
//			StringBuilder sb = new StringBuilder(str);
//			String reverse = sb.reverse().toString();
//			if(str.equals(reverse)) {
//				System.out.println("0");
//				continue;
//			}
//			
//			String tmp1 = "";
//			String tmp2 = "";
//			int len = str.length();
//
//			for(int i = 0; i < len; i++) {
//				char word1 = str.charAt(i), word2 = reverse.charAt(i);
//				if(word1 != reverse.charAt(i)) {
//					tmp1 = str.replace(String.valueOf(word1), "");
//					tmp2 = reverse.replace(String.valueOf(word1), "");
//					if(tmp1.equals(tmp2)) {
//						System.out.println("1");
//						break;
//					}
//					else {
//						tmp1 = "";
//						tmp2 = "";
//						tmp1 = str.replace(String.valueOf(word2), "");
//						tmp2 = reverse.replace(String.valueOf(word2), "");
//						if(tmp1.equals(tmp2)) {
//							System.out.println("1");
//							break;
//						}
//						else {
//							System.out.println("2");
//							break;
//						}
//							
//					}
//				}
//			}
//			
//		}
		
		for(int t = 0; t < T; t++) {
			String str = br.readLine();
			int start = 0;
			int end = str.length() - 1;
			int ans = 0;
			
			while(start < end) {
				if(str.charAt(start) == str.charAt(end)) {
					start++;
					end--;
				}
				else if(check(str.substring(start + 1, end + 1))) {
					ans = 1;
					break;
				}
				else if (check(str.substring(start, end))) {
					ans = 1;
					break;
				}
				else {
					ans = 2;
					break;
				}
			}
			
			System.out.println(ans);
		}
	}

	private static boolean check(String str) {
		int start = 0;
		int end = str.length() - 1;
		
		while(start < end) {
			if(str.charAt(start) == str.charAt(end)) {
				start++;
				end--;
			}
			else
				return false;
		}
		
		return true;
	}
}
