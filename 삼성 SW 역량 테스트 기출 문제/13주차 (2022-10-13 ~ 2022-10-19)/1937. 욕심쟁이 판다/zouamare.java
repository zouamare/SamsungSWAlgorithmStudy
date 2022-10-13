package coding_test.Algo2022년_10월.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* N의 크기가 최대 500이므로 블록의 수는 250000개여서 메모이제이션을 써야 했다.
*
* DP 구현부분에서 애를 많이 먹어서 DP 구현 부분이 제일 어려웠다.
*
* 백준의 내리막길이라는 문제랑 유사한 문제였다.
*
* */
public class BOJ_1937_욕심쟁이판다 {
    static int N , maxBlockCnt, map[][], dp[][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxBlockCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(dp[i][j] != 0)   continue;
                maxBlockCnt = Math.max(maxBlockCnt, DFS(i, j));
            }
        }

        System.out.println(maxBlockCnt);
    }

    private static int DFS(int x, int y) {
        if(dp[x][y] != 0)   return dp[x][y];

        dp[x][y] = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(isVaild(nx, ny) && map[nx][ny] > map[x][y]){
                dp[x][y] = Math.max(dp[x][y], DFS(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }

    private static boolean isVaild(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
