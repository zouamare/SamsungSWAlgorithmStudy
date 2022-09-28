package BOJ;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2573_빙산 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, cnt, ans;
    static int[][] map;
    static boolean[][] visited;
    static boolean find;
    public static void main(String[] args) {
        // 1. 빙산 녹는거 구현
        // 2. BFS로 빙산 분리 체크
        //    boolean 배열 하나 새로 만들어서 체크
        //    1) 값 있는 배열 찾은 다음 bfs 탐색하면서 visited 처리
        //    2) visited 처리 하고 탐색 종료 후 visited 처리 안된 값이 있는 칸이 있으면 빙산 쪼개진거
        //    3) 값이 있는 배열이 없으면 빙산이 동시에 녹은거 0 출력

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        // 입력
        for(int r = 0; r < N; r++)
        {
            for(int c = 0; c < M; c++)
            {
                map[r][c] = sc.nextInt();
            }
        }

        // 년 카운트
        cnt = 0;
        ans = 0;
        find = false;

        while(!find)
        {
            // 빙산 녹이기
            melt();
            cnt++;

            // 쪼갠진지 확인
            if(bfs())
            {
                // true가 return 됐다면 빙산이 동시에 다 녹았다
                System.out.println(0);
                return;
            }
        }
        System.out.println(cnt);
    }

    static void melt()
    {
        // 다음 맵을 저장할 변수
        int[][] nMap = new int[N][M];

        // 반복 돌면서 빙하 녹이기
        for(int r = 1; r < N-1; r++)
        {
            for(int c = 1; c < M-1; c++)
            {
                // 현재 칸이 빙하면
                if(map[r][c] != 0)
                {
                    // 주변 물의 칸 수 세기 위한 변수
                    int meltCnt = 0;
                    // Delta배열 이용해서 주변 물의 칸 수 계산
                    for(int i = 0 ; i < 4; i++)
                    {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1 || map[nr][nc] != 0) continue;
                        meltCnt++;
                    }
                    // 현재 빙하 높이에서 주변 물의 칸 수 뺀거 변수에 담음
                    int nHeight = map[r][c] - meltCnt;
                    // nHeight가 0보다 작으면 값 갱신 안해도 됨 클때만 갱신
                    if(nHeight > 0) nMap[r][c] = nHeight;
                }
            }
        }


        map = nMap;
    }

    static boolean bfs()
    {
        // 방문체크용 boolean 배열
        visited = new boolean[N][M];

        // 빙하 쪼개진거 카운트
        int iceCnt = 0;
        // 물이 아닌 칸 카운트해서 빙하 유무 확인
        int zeroCnt = 0;
        // 각 칸마다 BFS 돌리기
        for(int r = 1; r < N-1; r++)
        {
            for(int c = 1; c < M-1; c++)
            {
                // 현재 칸이 물이면 확인 안해도 됨
                if(map[r][c] == 0) continue;

                // 여기 왔으면 물이 아님 빙하 있는거니까 ++
                zeroCnt++;

                // 방문 했으면서 빙하이고 이미 체크한 빙하가 있으면 빙하 쪼개진거니까 return
                if(!visited[r][c] && map[r][c] != 0 && iceCnt != 0)
                {
                    find = true;
                    return false;
                }

                // BFS
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[] {r, c});

                while (!q.isEmpty())
                {
                    int[] temp = q.poll();
                    int cr = temp[0];
                    int cc = temp[1];

                    visited[cr][cc] = true;

                    for(int i = 0; i < 4; i++)
                    {
                        int nr = cr + dr[i];
                        int nc = cc + dc[i];
                        if(nr < 1 || nr > N-2 || nc < 1 || nc > M-2 || map[nr][nc] == 0 || visited[nr][nc]) continue;
                        visited[nr][nc] = true;
                        q.offer(new int[] {nr, nc});
                    }
                }

                // 첫 빙하 체크용
                if(iceCnt == 0) iceCnt++;

            }
        }

        // 빙하가 없으면 return true;
        if(zeroCnt == 0) return true;
        return false;
    }
}
