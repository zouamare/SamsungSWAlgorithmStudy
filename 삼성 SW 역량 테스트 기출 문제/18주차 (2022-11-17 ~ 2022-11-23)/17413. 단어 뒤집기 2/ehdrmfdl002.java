package solo.study;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_17413_단어뒤집기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack<>();
		boolean flag = false;
		
		String str = sc.nextLine();
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '<') {
				print(stack);
				flag = true;
				System.out.print(str.charAt(i));
			}
			else if(str.charAt(i) == '>') {
				flag = false;
				System.out.print(str.charAt(i));
			}
			else if(flag)
				System.out.print(str.charAt(i));
			else {
				if(str.charAt(i) == ' ') {
					print(stack);
					System.out.print(str.charAt(i));
				}
				else
					stack.add(str.charAt(i));
			}
		}
		print(stack);
	}

	private static void print(Stack<Character> stack) {
		while(!stack.empty()) {
			System.out.print(stack.peek());
			stack.pop();
		}
	}
}	
