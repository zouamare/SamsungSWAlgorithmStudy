package solo.study;

import java.util.*;

public class BOJ_9019_DSLR {
    static class Number {
        int num;
        String command;

        public Number(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int A = sc.nextInt();
            int B = sc.nextInt();

            boolean[] visit = new boolean[10000];
            Queue<Number> q = new LinkedList<>();
            q.offer(new Number(A, ""));
            visit[A] = true;

            while (!q.isEmpty()) {
                int now = q.peek().num;
                String command = q.peek().command;
                q.poll();

                if (now == B) {
                    System.out.println(command);
                    break;
                }

                int next = (now * 2) % 10000;
                if (!visit[next]) {
                    visit[next] = true;
                    q.offer(new Number(next, command + "D"));
                }
                next = now == 0 ? 9999 : now - 1;
                if (!visit[next]) {
                    visit[next] = true;
                    q.offer(new Number(next, command + "S"));
                }

                next = now / 1000 + (now % 1000) * 10;
                if (!visit[next]) {
                    visit[next] = true;
                    q.offer(new Number(next, command + "L"));
                }
                next = now % 10 * 1000 + now / 10;
                if (!visit[next]) {
                    visit[next] = true;
                    q.offer(new Number(next, command + "R"));
                }
            }
        } 
    }

}

