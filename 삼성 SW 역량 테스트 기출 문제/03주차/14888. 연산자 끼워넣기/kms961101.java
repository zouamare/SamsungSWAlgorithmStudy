package ex.test.bj;

import java.util.*;

public class BOJ_14888_연산자끼워넣기 {
	static int N, cnt;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int[] num,arithmetic, res;
	static String[] temp, input;
	static boolean[] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		res = new int[N];
		arithmetic = new int[4];
		for(int i = 0; i < N; i++) {
			int a = sc.nextInt();
			num[i] = a;
			res[i] = a;
		}
		for(int i = 0; i < 4; i++) {
			int n = sc.nextInt();
			arithmetic[i] = n;
			cnt += n;
			
		}
		temp = new String[cnt];
		input = new String[cnt];
		isSelected = new boolean[cnt];
		int idx = cnt;
		for(int i = 3; i >= 0; i--) {
			while(arithmetic[i]-- > 0) {
				switch(i) {
				case 3:
					temp[idx - 1] = "/";
					idx--;
					break;
				case 2:
					temp[idx - 1] = "*";
					idx--;
					break;
				case 1:
					temp[idx - 1] = "-";
					idx--;
					break;
				case 0:
					temp[idx - 1] = "+";
					idx--;
					break;
				}
			}
		}
		
		form(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	private static void form(int count) {
		if(count == cnt) {
			for(int i = 0; i < cnt; i++) {
				switch(input[i]) {
				case "+":
					num[i + 1] = add(num[i], num[i + 1]);
					break;
				case "-":
					num[i + 1] = sub(num[i], num[i + 1]);
					break;
				case "*":
					num[i + 1] = mul(num[i], num[i + 1]);
					break;
				case "/":
					num[i + 1] = div(num[i], num[i + 1]);
					break;
				}
			}
			min = Math.min(min, num[cnt]);
			max = Math.max(max, num[cnt]);
			for(int i = 0; i < N; i++) {
				num[i] = res[i];
			}
			return;
		}
		for(int i = 0; i < cnt; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			input[count] = temp[i];
			form(count + 1);
			isSelected[i] = false;
		}
		
	}
	
	private static int add(int x, int y) {
		int add = 0;
		add = x + y;
		return add;
	}
	private static int sub(int x, int y) {
		int sub = 0;
		sub = x - y;
		return sub;

		
	}
	private static int mul(int x, int y) {
		int mul = 0;
		mul = x * y;
		return mul;
		
	}
	private static int div(int x, int y) {
		int div = 0;
		if(x < 0) {
			x *= -1;
			div = (x / y) * -1;
		}else {
			div = x / y;
		}
		return div;
	}
}
