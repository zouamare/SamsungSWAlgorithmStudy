package coding_test.Algo2022년_11월.day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// https://dragon-h.tistory.com/6 참고
// priorityQueue를 써야한다는 것은 바로 알았으나 min, max로 나누어야 하는 풀이는 생각을 못했다.
public class BOJ_1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);

        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(min.size() == max.size()){
                if(!min.isEmpty() && min.peek() < input){
                    max.offer(min.poll());
                    min.offer(input);
                }else{
                    max.offer(input);
                }
            }else{
                if(!max.isEmpty() && max.peek() > input){
                    min.offer(max.poll());
                    max.offer(input);
                }else{
                    min.offer(input);
                }
            }
            ans.append(max.peek()).append("\n");
        }
        System.out.println(ans);
    }
}
