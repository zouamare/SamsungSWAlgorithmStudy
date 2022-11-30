package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class N17413_1 {
	static String s;
	static boolean flag;
	static Stack st;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s = br.readLine();
		st = new Stack<>();
		
		flag=false;
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='<') {
				reverse(st);
				flag=true;
				System.out.print(s.charAt(i));
			}else if(s.charAt(i)=='>') {
				flag=false;
				System.out.print(s.charAt(i));
			}else if(flag) {
				System.out.print(s.charAt(i));
			}else {
				if(s.charAt(i)==' ') {
					reverse(st);
					System.out.print(s.charAt(i));
				}else {
					st.push(s.charAt(i));
				}
			}
		}
		reverse(st);
	}
	
	private static void reverse(Stack st) {
		
		while(!st.isEmpty()) {
			System.out.print(st.peek());
			st.pop();
		}
	}
}
