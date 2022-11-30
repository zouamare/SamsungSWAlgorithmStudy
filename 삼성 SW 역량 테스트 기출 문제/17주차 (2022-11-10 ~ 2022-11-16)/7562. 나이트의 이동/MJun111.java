import java.io.*;
import java.util.*;

public class Main {
    static int l, r, c, gr, gc;
    static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            l = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            gr = Integer.parseInt(st.nextToken());
            gc = Integer.parseInt(st.nextToken());

            if (r == gr && c == gc) {
                sb.append("0\n");
                continue;
            }

            sb.append(bfs() + "\n");
        }
        System.out.print(sb);
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[l][l];

        q.add(new int[] {r, c, 0});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr < 0 || nc < 0 || nr >= l || nc >= l) continue;
                if (visited[nr][nc]) continue;
                if (nr == gr && nc == gc) return cur[2] + 1;

                visited[nr][nc] = true;
                q.add(new int[] {nr, nc, cur[2] + 1});
            }
        }
        return 0;
    }
}