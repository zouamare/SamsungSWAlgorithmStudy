import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minpq = new PriorityQueue<>();

        while (n-- > 0) {
            int input = Integer.parseInt(br.readLine());

            if (minpq.size() < maxpq.size()) {
                minpq.add(input);

                // 작은 값
                if (maxpq.peek() > minpq.peek()) {
                    minpq.add(maxpq.poll());
                    maxpq.add(minpq.poll());
                }
            } else {
                maxpq.add(input);

                // 작은 값
                if (!minpq.isEmpty() && maxpq.peek() > minpq.peek()) {
                    minpq.add(maxpq.poll());
                    maxpq.add(minpq.poll());
                }
            }

            sb.append(maxpq.peek() + "\n");
        }

        System.out.println(sb);
    }
}