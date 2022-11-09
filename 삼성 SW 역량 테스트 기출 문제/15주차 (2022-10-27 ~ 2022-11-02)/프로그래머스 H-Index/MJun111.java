import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		int[] citations = {4, 4, 4};
		System.out.println(solution(citations));
	}
	
	static public int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;
        // m[h] : h번 이상 인용된 논문 수
        int[] m = new int[citations.length + 1];
        
        for (int i = 1; i <= citations.length; i++) {
        	for (int j = 0; j < n; j++) {
        		if (citations[j] >= i) {
        			m[i]++;
        		}
        	}
        }
        
        for (int i = n; i > 0; i--) {
        	// i번 이상 인용된 논문 수 i개 이상 && n - m[i] : 전체에서 i번 이상 인용된 논문 수 i개 이하
        	if (m[i] >= i && (n - m[i]) <= i) {
        		answer = i;
        		break;
        	}
        }
        
        return answer;
    }
	
}