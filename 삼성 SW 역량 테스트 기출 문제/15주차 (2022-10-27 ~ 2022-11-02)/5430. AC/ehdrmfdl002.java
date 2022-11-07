package solo.study;

import java.io.*;
import java.util.*;

public class BOJ_5430_AC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			boolean error = false, cursor = false; // false : front, true : back
			String[] str1 = br.readLine().split("");
			br.readLine();
			String str2 = br.readLine();
			ArrayList<String> list = new ArrayList<>(Arrays.asList(str2.substring(1, str2.length() - 1).split(",")));

			if (list.get(0).equals(""))
				list.remove(0);
			
			for (String w : str1) {
				switch (w) {
				case "R":
					cursor = !cursor;
					break;
				case "D":
					if (list.size() == 0) {
						error = true;
						break;
					} else if (cursor)
						list.remove(list.size() - 1);
					else
						list.remove(0);
				}
			}
			
			if(cursor)
				Collections.reverse(list);
			
			if(error)
				System.out.println("error");
			else
				System.out.println("[" + String.join(",", list) + "]");
		}
	}
}