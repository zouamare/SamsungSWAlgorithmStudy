import java.util.*;
import java.util.Scanner;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int idx = 0;
        int size = citations.length;
        
        while(true) {
            if(size == idx) {
                break;
            }
            int h = size - idx;
            
            if(citations[idx] >= h) {
                answer = h;
                break;
            }
            idx++;
        }
        
        return answer;
    }
}