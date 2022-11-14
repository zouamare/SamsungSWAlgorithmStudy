package coding_test.Algo2022년_11월.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562_나이트의이동 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
        StringBuilder ans = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int l = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nx = Integer.parseInt(st.nextToken());  // 현재 위치 nx
            int ny = Integer.parseInt(st.nextToken());  // 현재 위치 ny
            st = new StringTokenizer(br.readLine());
            int tx = Integer.parseInt(st.nextToken());  // 타겟 위치 tx
            int ty = Integer.parseInt(st.nextToken());  // 타겟 위치 ty

            Queue<Point> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[l][l];
            queue.offer(new Point(nx, ny, 0));
            visited[nx][ny] = true;
            while (!queue.isEmpty()) {
                Point now = queue.poll();

                if (now.x == tx && now.y == ty) {   // 위치를 찾았을 때
                    ans.append(now.count).append("\n");
                    break;
                }

                for (int d = 0, size = dx.length; d < size; d++) {
                    int x = now.x + dx[d];
                    int y = now.y + dy[d];
                    if (x < 0 || x >= l || y < 0 || y >= l || visited[x][y]) continue;
                    visited[x][y] = true;
                    queue.add(new Point(x, y, now.count + 1));
                }
            }
        }
        System.out.println(ans);
    }

    static class Point{
        int x, y, count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
