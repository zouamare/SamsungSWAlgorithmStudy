package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

public class BOJ_5430_AC {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		outer:for (int T = 0; T < t; T++) {
			String cmd = br.readLine();
			int n = Integer.parseInt(br.readLine());
			Deque<Integer> dq = new ArrayDeque<>();
			String temp = br.readLine();
			if(n == 0) {
				for (int i = 0; i < cmd.length(); i++) {
					if(n == 0 && cmd.length() != 0 && cmd.charAt(i) == 'D') {
						System.out.println("error");
						continue outer;
					}
				}
				System.out.println("[]");
				continue outer;
			}
			temp = temp.substring(1, temp.length() - 1);
			String[] arr = temp.split(",");
			for (int i = 0; i < arr.length; i++) {
				dq.add(Integer.parseInt(arr[i]));
			}
			boolean flag = false;
			
			for (int i = 0; i < cmd.length(); i++) {
				if(cmd.charAt(i) == 'R') {
					flag = !flag;
					continue;
				}
				
				if(!flag) {
					if(dq.isEmpty()) {
						System.out.println("error");
						continue outer;
					}
					dq.pollFirst();
				}else {
					if(dq.isEmpty()) {
						System.out.println("error");
						continue outer;
					}
					dq.pollLast();
				}
			}
			if(!flag) System.out.println(dq.toString().replace(" ", ""));
			else {
				if(dq.isEmpty()) {
					System.out.println("[]");
					continue outer;
				}
				StringBuffer sb = new StringBuffer();
				sb.append("[");
				while(!dq.isEmpty()) {
					if(dq.size() != 1) sb.append(dq.pollLast()).append(",");
					else sb.append(dq.poll()).append("]");
				}
				System.out.println(sb.toString());
			}
		}
	}
}