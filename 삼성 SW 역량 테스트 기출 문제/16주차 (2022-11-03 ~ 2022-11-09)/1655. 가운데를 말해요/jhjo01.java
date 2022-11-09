package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();

        max.offer(Integer.parseInt(br.readLine()));
        sb.append(max.peek()).append("\n");
        min.offer(Integer.parseInt(br.readLine()));
        if(max.peek() > min.peek()) {
            int temp = min.poll();
            min.offer(max.poll());
            max.offer(temp);
        }
        sb.append(max.peek()).append("\n");

        for(int i = 0; i < N-2; i++) {
            if(max.size() <= min.size()) {
                max.offer(Integer.parseInt(br.readLine()));
            } else {
                min.offer(Integer.parseInt(br.readLine()));
            }

            if(max.peek() > min.peek()) min.offer(max.poll());

            if(max.size() < min.size()) max.offer(min.poll());

            if(max.size() < min.size()) sb.append(min.peek()).append("\n");
            else sb.append(max.peek()).append("\n");

        }


        System.out.println(sb);
    }
}
