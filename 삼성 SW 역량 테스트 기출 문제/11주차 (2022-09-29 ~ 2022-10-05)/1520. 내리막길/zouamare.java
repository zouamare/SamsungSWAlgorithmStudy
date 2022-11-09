package coding_test.Algo2022년_10월.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 블로그 참고 https://steady-coding.tistory.com/142
public class BOJ_1520_내리막길 {
    static int M, N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] data, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());   // 세로의 크기
        N = Integer.parseInt(st.nextToken());   // 가로의 크기

        data = new int[M+1][N+1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp = new int[M+1][N+1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(1, 1));
    }

    private static int dfs(int x, int y) {
        if(x == M && y == N)    return 1;
        if(dp[x][y] != -1)  return dp[x][y];

        dp[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isVaild(nx, ny) && data[x][y] > data[nx][ny]){
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }

    private static boolean isVaild(int x, int y){
        return x > 0 && x <= M && y > 0 && y <= N;
    }
}
