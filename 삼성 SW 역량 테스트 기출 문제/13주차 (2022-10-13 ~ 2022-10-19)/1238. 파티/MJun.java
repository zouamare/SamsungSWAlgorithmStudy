import java.io.*;
import java.util.*;

public class Main {
    static class Road implements Comparable<Road>{
        int end, time;

        public Road(int end, int time) {
            this.end = end;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return this.time - o.time;
        }
    }
    static final int MAX = 987654321;
    static int n, m, x;
    static List<Road>[] list;
    static int[] dist;
    static int[][] dist2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // input
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        list = new ArrayList[m + 1];
        for (int i = 1; i <= m; i++) {
            list[i] = new ArrayList<>();
        }

        dist = new int[n + 1];
        Arrays.fill(dist, MAX);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list[a].add(new Road(b, t));
        }

        // x 마을에서 각 마을까지 거리 구하는 다익스트라
        dijkstra();

        dist2 = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist2[i], MAX);
        }

        // 각 마을에서 x 마을까지 거리 구하는 다익스트라
        for (int i = 1; i <= n; i++) {
            if (i == x) continue;
            dijkstra2(i);
        }

        // 각 마을 -> x 마을 거리 + x 마을 -> 각 마을 거리
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (i == x) continue;
            ans = Math.max(ans, dist[i] + dist2[i][x]);
        }

        System.out.println(ans);
    }

    static void dijkstra() {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(x, 0));
        dist[x] = 0;

        while (!pq.isEmpty()) {
            Road cur = pq.poll();

            if (dist[cur.end] < cur.time) continue;

            for (int i = 0; i < list[cur.end].size(); i++) {
                Road next = list[cur.end].get(i);
                int rTime = dist[cur.end] + next.time;

                if (rTime < dist[next.end]) {
                    dist[next.end] = rTime;
                    pq.add(new Road(next.end, rTime));
                }
            }
        }
    }

    static void dijkstra2(int sp) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(sp, 0));
        dist2[sp][sp] = 0;

        while (!pq.isEmpty()) {
            Road cur = pq.poll();

            if (dist2[sp][cur.end] < cur.time) continue;

            for (int i = 0; i < list[cur.end].size(); i++) {
                Road next = list[cur.end].get(i);
                int rTime = dist2[sp][cur.end] + next.time;

                if (rTime < dist2[sp][next.end]) {
                    dist2[sp][next.end] = rTime;
                    pq.add(new Road(next.end, rTime));
                }
            }
        }
    }
}