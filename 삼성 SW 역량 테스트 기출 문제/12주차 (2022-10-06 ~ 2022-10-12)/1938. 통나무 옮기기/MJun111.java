import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int n, dir;
    static char[][] map;
    static Point[] B;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        B = new Point[3];

        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'B') {
                    B[idx1++] = new Point(i, j);
                }
            }
        }

        // 행이 같으면 ㅡ 방향 : dir = 0
        if (B[0].r == B[1].r)
            dir = 0;
        else
            dir = 1;

        bfs();
        System.out.println(0);
    }

    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][n][n];   // 방향, 방문 위치
        q.add(B[1].r);
        q.add(B[1].c);
        q.add(dir);
        q.add(0);
        visited[dir][B[1].r][B[1].c] = true;

        while (!q.isEmpty()) {
            int curR = q.poll();
            int curC = q.poll();
            int curDir = q.poll();
            int cnt = q.poll();

            if (curDir == 0) {
                if (map[curR][curC - 1] == 'E' && map[curR][curC] == 'E' && map[curR][curC + 1] == 'E') {
                    System.out.println(cnt);
                    System.exit(0);
                }
            } else {
                if (map[curR - 1][curC] == 'E' && map[curR][curC] == 'E' && map[curR + 1][curC] == 'E') {
                    System.out.println(cnt);
                    System.exit(0);
                }
            }

            // 4방 탐색
            for (int d = 0; d < 4; d++) {
                int nR = curR + dr[d];
                int nC = curC + dc[d];

                if (curDir == 0) {  // ㅡ
                    if (nR < 0 || nR >= n || nC - 1 < 0 || nC + 1 >= n) continue;
                    if (map[nR][nC - 1] == '1' || map[nR][nC] == '1' || map[nR][nC + 1] == '1')
                        continue;
                } else {
                    if (nR - 1 < 0 || nR + 1 >= n || nC < 0 || nC >= n) continue;
                    if (map[nR - 1][nC] == '1' || map[nR][nC] == '1' || map[nR + 1][nC] == '1')
                        continue;
                }
                if (visited[curDir][nR][nC]) continue;

                visited[curDir][nR][nC] = true;
                q.add(nR);
                q.add(nC);
                q.add(curDir);
                q.add(cnt + 1);
            }

            // 회전
            if (curDir == 0) {  // ㅡ
                // 회전 반경 확인
                if (
                        curR - 1 >= 0 && curR + 1 < n
                        && map[curR - 1][curC - 1] != '1' && map[curR - 1][curC] != '1' && map[curR - 1][curC + 1] != '1'
                        && map[curR + 1][curC - 1] != '1' && map[curR + 1][curC] != '1' && map[curR + 1][curC + 1] != '1'
                ) {
                    if (!visited[1][curR][curC]) {
                        visited[1][curR][curC] = true;
                        q.add(curR);
                        q.add(curC);
                        q.add(1);
                        q.add(cnt + 1);
                    }
                }
            } else {
                // 회전 반경 확인
                if (
                        curC - 1 >= 0 && curC + 1 < n
                        && map[curR - 1][curC - 1] != '1' && map[curR][curC - 1] != '1' && map[curR + 1][curC - 1] != '1'
                        && map[curR - 1][curC + 1] != '1' && map[curR][curC + 1] != '1' && map[curR + 1][curC + 1] != '1'
                ) {
                    if (!visited[0][curR][curC]) {
                        visited[0][curR][curC] = true;
                        q.add(curR);
                        q.add(curC);
                        q.add(0);
                        q.add(cnt + 1);
                    }
                }
            }
        }
    }
}