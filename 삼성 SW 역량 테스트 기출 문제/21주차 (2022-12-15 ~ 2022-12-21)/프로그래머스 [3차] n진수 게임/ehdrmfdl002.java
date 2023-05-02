package solo.study;

import java.util.LinkedList;
import java.util.Queue;

public class PG_n진수게임 {
	class Solution {
		// n : 진법, t : 미리 구할 숫자의 갯수, m : 게임의 참가하는 인원, p : 튜브의 순서 
	    public String solution(int n, int t, int m, int p) {
	        StringBuffer answer = new StringBuffer();
	        Queue<Character> q = new LinkedList<>();
	        q.add('0');
	        
	        outer : for(int i = 0; i <= 1000000; i++) {
	        	StringBuffer sb = new StringBuffer();
	        	int tmp = i;
	        	while(tmp != 0) {
	        		if(tmp % n >= 10) {
	        			sb.append((char)('A' + tmp % n - 10));
	        		}
	        		else {
	        			sb.append(tmp % n);
	        		}
	        		
	        		tmp /= n;
	        	}
	        	sb = sb.reverse();
	        	
	        	for(int j = 0; j < sb.length(); j++) {
	        		q.add(sb.charAt(j));
	        		if(q.size() == t * m)
	        			break outer;
	        	}
	        }
	        
	        int len = q.size();
	        for(int i = 0; i < len; i++) {
	        	if(i % m + 1 == p)
	        		answer.append(q.poll());
	        	else
	        		q.poll();
	        }
	        
	        return answer.toString();
	    }
	}
}
