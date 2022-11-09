import java.io.*;
import java.util.*;

/*
분기가 많아보여 bfs + dp로 접근했지만, 메모리초과
n이 최대 500이라 bfs 과정에서 메모리가 터질 것 같다는 의견이 있었음
*/

public class Main {
    static int n, ans;
    static int[][] map;
    static int[][] dp;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int res = dfs(i, j);
                ans = Math.max(res, ans);
            }
        }

        System.out.println(ans);
    }

    static int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        dp[r][c] = 1;

        for (int i = 0; i <4; i++) {
            int nr = r + dy[i];
            int nc = c + dx[i];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (map[r][c] >= map[nr][nc]) continue;

            dp[r][c] = Math.max(dfs(nr, nc) + 1, dp[r][c]);
        }

        return dp[r][c];
    }
}