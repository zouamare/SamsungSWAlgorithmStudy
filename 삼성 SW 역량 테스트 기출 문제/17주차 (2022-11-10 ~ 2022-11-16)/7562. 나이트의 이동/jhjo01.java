import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_7562_나이트의이동 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int testcase = 0; testcase < T; testcase++) {
            int N = sc.nextInt();

            boolean[][] visited = new boolean[N][N];

            int cr = sc.nextInt();
            int cc = sc.nextInt();

            int tr = sc.nextInt();
            int tc = sc.nextInt();

            int ans = 0;

            Queue<int[]> q = new ArrayDeque<>();

            q.offer(new int[] {cr, cc, 1});

            if(cr == tr && cc == tc) {
                ans = 0;
                q.poll();
            }

            outer : while (!q.isEmpty()) {
                int[] temp = q.poll();

                int r = temp[0];
                int c = temp[1];
                int cnt = temp[2];



                int nr = 0;
                int nc = 0;

                for(int d = 0; d < 8; d++) {
                    nr = r + dr[d];
                    nc = c + dc[d];

                    if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;

                    if(nr == tr && nc == tc) {
                        ans = cnt;
                        break outer;
                    }
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc, cnt + 1});

                }


            }

            System.out.println(ans);

        }
    }
}
