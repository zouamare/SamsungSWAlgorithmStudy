package BOJ;

import java.util.Arrays;
import java.util.Scanner;
/*
    처음엔 BFS로 풀라고 했는데 메모리 초과가 났다.
    그래서 DFS로 풀라고 했는데 시간 초과가 났다.

    그래서 DFS + 메모라이제이션을 사용하기로 했다.

    DP의 핵심..! 한번 계산한건 또 계산 안한다는 것을 이용해서..
    처음에 visited배열을 -1로 초기화 해주고
    한번 방문한 곳에 목적지에 도달 가능한 경우의 수를 표시해준다.

    이렇게 하면 visited에 -1이 아닌 곳은 해당 위치에서 도달 가능한 경우의 수가 되니까 계산 안해도 됨

 */
public class BOJ_1520_내리막길_DFS {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map, visited;
    static int N, M, ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                map[r][c] = sc.nextInt();
            }
        }

        ans = 0;

        dfs(0, 0, 0);

        System.out.println(ans);

    }


    private static int dfs(int r, int c, int cnt) {
        if(r == N-1 && c == M-1) {
            ans++;
            return 1;
        }
        int nCnt = 0;

        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if(map[nr][nc] >= map[r][c]) continue;
            if(visited[nr][nc] != -1) {
                ans += visited[nr][nc];
                nCnt += visited[nr][nc];
                continue;
            }

            nCnt += dfs(nr, nc, cnt);
        }

        visited[r][c] = nCnt;

        return nCnt;
    }
}
