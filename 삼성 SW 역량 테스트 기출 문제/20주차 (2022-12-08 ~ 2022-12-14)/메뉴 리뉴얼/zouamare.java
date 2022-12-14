package coding_test.Algo2022년_12월.day14;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PG_메뉴리뉴얼 {
    HashMap<String, Integer> map;
    int m;
    public String[] solution(String[] orders, int[] course) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        for(int i =0; i<course.length; i++){
            this.map = new HashMap<>();
            this.m = 0;
            for(int j = 0; j<orders.length; j++){
                find(0,"",course[i],0,orders[j]);
            }
            for(String s: map.keySet()){
                if(map.get(s)==m&&m>1)
                    pq.offer(s);
            }
        }
        return PQ_to_array(pq);
    }

    void find(int cnt, String str, int targetNum, int idx, String word){
        if(cnt==targetNum){
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String temps = new String(c);
            map.put(temps, map.getOrDefault(temps,0)+1);
            m = Math.max(m, map.get(temps));
            return;
        }
        for(int i =idx; i<word.length(); i++){
            char now = word.charAt(i);
            find(cnt+1, str+now, targetNum, i+1, word);
        }
    }

    String[] PQ_to_array(PriorityQueue<String> pq){
        String ans[] = new String[pq.size()];
        int k=0;
        while(!pq.isEmpty()){
            ans[k++] = pq.poll();
        }
        return ans;
    }
}
