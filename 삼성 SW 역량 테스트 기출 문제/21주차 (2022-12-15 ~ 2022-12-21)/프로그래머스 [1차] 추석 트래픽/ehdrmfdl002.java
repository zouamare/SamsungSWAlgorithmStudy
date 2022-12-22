package solo.study;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class PG_추석트래픽 {
	class Solution {
		class Date implements Comparable<Date> {
			int h, m, s, t;

			public Date(int h, int m, int s, int t) {
				super();
				this.h = h;
				this.m = m;
				this.s = s;
				this.t = t;
			}

			@Override
			public int compareTo(Date o) {
				if (this.m == o.m && this.h == o.h)
					return this.s = o.s;

				if (this.h == o.h)
					return this.m - o.m;

				return this.h = o.h;
			}
		}

		public int solution(String[] lines) {
			int answer = 0;
			ArrayList<Date> list = new ArrayList<>();

			for (int i = 0; i < lines.length; i++) {
				StringTokenizer st = new StringTokenizer(lines[i], ": ");
				st.nextToken();
				int hour = Integer.parseInt(st.nextToken()) * 60 * 60 * 1000;
				int min = Integer.parseInt(st.nextToken()) * 60 * 1000;
				double num1 = Double.parseDouble(st.nextToken()) * 1000;
				int sec = (int) num1;

				String s = st.nextToken();
				s = s.substring(0, s.length() - 1);
				double num2 = Double.parseDouble(s) * 1000;

				int time = (int) num2;

				list.add(new Date(hour, min, sec, time));
			}
			
			
			for(int i = 0; i < list.size(); i++) {
				Date date1 = list.get(i);
				int traffic = 1;
				int time1 = date1.h + date1.m + date1.s + 1000 - 1;
				for(int j = i + 1; j < list.size(); j++) {
					Date date2 = list.get(j);
					int sub = 0;
					int time2 = date2.h + date2.m + date2.s - date2.t + 1;
					sub = time1 - time2;
					
					if(sub >= 0)
						traffic++;
				}
				answer = Math.max(answer, traffic);
			}

			return answer;
		}
	}
}
