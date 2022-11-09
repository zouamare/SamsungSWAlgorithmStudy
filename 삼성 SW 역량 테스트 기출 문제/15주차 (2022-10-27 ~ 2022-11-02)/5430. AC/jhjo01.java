package BOJ;

import java.io.BufferedReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_5430_AC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();

        // D 첫번째 수를 버린다
        // R 배열을 뒤집는다

        StringBuilder sb = new StringBuilder();

        outer : for (int tc = 0; tc < T; tc++) {
            String cmd = sc.next();
            int len = sc.nextInt();

            Deque<Integer> queue = new ArrayDeque<>();
            boolean dir = true; // true 정방향 false 역방향

            String s = sc.next();
            s = s.substring(1, s.length()-1);
            StringTokenizer st = new StringTokenizer(s, ",");

            for (int i = 0; i < len; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            for (int i = 0, size = cmd.length(); i < size; i++) {
                switch (cmd.charAt(i)) {
                    case 'R':
                        dir = !dir;
                        break;
                    case 'D':
                        if (queue.isEmpty()) {
                            sb.append("error\n");
                            continue outer;
                        }
                        if(dir) {
                            queue.poll();
                        } else {
                            queue.pollLast();
                        }
                        break;
                }
            }

            if(queue.isEmpty()) {
                sb.append("[]\n");
            } else {
                sb.append("[");
                while (!queue.isEmpty()) {
                    if(dir) {
                        sb.append(queue.poll());
                    } else {
                        sb.append(queue.pollLast());
                    }
                    if(queue.size() != 0) sb.append(",");
                }
                sb.append("]\n");
            }
        }
        System.out.print(sb);
    }
}
