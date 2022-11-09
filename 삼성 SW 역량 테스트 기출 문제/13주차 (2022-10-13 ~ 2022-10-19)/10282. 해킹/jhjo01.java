package BOJ;

import java.util.*;

public class BOJ_10282_해킹 {
    static int N, M, X, max;
    static int[] visited, dp, ans;
    static List<int[]> path[];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        
        for (int test_case = 0; test_case < T; test_case++) {
            
            N = sc.nextInt(); // 컴터 수
            M = sc.nextInt(); // 의존성 수
            X = sc.nextInt(); // 해킹당한거

            path = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                path[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M; i++) {
                int to = sc.nextInt();
                int start =  sc.nextInt();

                path[start].add(new int[]{to, sc.nextInt()});
            }

            dp = new int[N + 1];
            ans = new int[N + 1];
            visited = new int[N + 1];

            PathFinder(X);

            for (int i = 1; i <= N; i++) {
                ans[i] = dp[i];
            }

//            for (int i = 1; i <= N; i++) {
//                visited = new int[N + 1];
//                PathFinder(i);
//                ans[i] += dp[X];
//            }

            max = -1;
            int cnt = 0;

            for (int i = 1; i <= N; i++) {
                if(ans[i] == Integer.MAX_VALUE) continue;
                cnt++;
                max = Math.max(max, ans[i]);
            }

            System.out.println(cnt + " " + max);
            
        }
    }

    private static void PathFinder(int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        Arrays.fill(dp, Integer.MAX_VALUE);

        pq.add(new int[] {x, 0});

        dp[x] = 0;

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int m = temp[0];
            int c = temp[1];

            if(visited[m] == 1) continue;
            visited[m] = 1;

            for(int[] next : path[m]) {
                if(dp[next[0]] > dp[m] + next[1]) {
                    dp[next[0]] = dp[m] + next[1];
                    pq.add(new int[] {next[0], dp[next[0]]});
                }
            }
        }
    }
}
