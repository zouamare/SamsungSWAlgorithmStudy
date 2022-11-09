import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
    구현문제
    문제가 시키는대로 풀었음
    아마도?

 */
public class BOJ_23290_마법사상어와복제 {
    // 좌 좌상 상 우상 우 우하 하 좌하
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int S, M, sr, sc;
    static List<Integer>[][] map, nMap;
    static int[][] smell;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

         map = new ArrayList[5][5];
        nMap = new ArrayList[5][5];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                map[i][j] = new ArrayList<>();
                nMap[i][j] = new ArrayList<>();
            }
        }

         smell = new int[5][5];

         // r, c, d
         for(int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());
             map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken())-1);
         }

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());

        for (int s = 0; s < S; s++) {
            // 0. 물고기 복사
            copy(map, nMap);

            // 1. 모든 물고기 한칸 이동
            moveFish();

            // 2. 상어 3칸 이동
            moveShark();

            // 3. 2번 전 연습에서 생긴 물고기 냄새가 사라진다
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 5; j++) {
                    if (smell[i][j] > 0) smell[i][j]--;
                }
            }

            // 4. 물고기가 이동하기 전 모습 그대로 복사가 된다.
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 5; j++) {
                    map[i][j].addAll(nMap[i][j]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                ans = ans + map[i][j].size();
            }
        }

        System.out.println(ans);
    }

    private static void copy(List<Integer>[][] map, List<Integer>[][] nMap) {
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                nMap[i][j] = new ArrayList<>();
                for(int k = 0, size = map[i][j].size();  k < size; k++) {
                    nMap[i][j].add(map[i][j].get(k));
                }
            }
        }
    }
    private static void moveShark() {
        // 2. 상어가 연속해서 3칸 이동한다.
        //  2-2 이동 중 격자범위 벗어나는 칸이 있으면 이동 불가능
        //  2-3 이동 중 물고기가 있는 칸으로 이동하면 그 칸에 있는 물고기는 제외되고 물고기 냄새를 남긴다.
        //  2-4 제외되는 물고기 수가 제일 많은 방향으로 이동한다
        //  2-5

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        List<int[]> next = new ArrayList<>();

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] {sr, sc, 0, 0, 0, -1});

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int ssr = temp[0];
            int ssc = temp[1];
            int cnt = temp[2];
            int fish = temp[3];
            int pri = temp[4];
            int prev = temp[5];

            if(cnt == 3) {
                next.add(temp);
                continue;
            }

            for(int d = 0; d < 4; d++) {

                int nr = ssr + dr[d];
                int nc = ssc + dc[d];

                if(nr < 1 || nr > 4 || nc < 1 || nc > 4) continue;


                int nFish = 0;
                if(cnt != 1) {
                    if (d == prev) {
                        nFish = fish;
                    } else {
                        nFish = fish + map[nr][nc].size();
                    }
                }
                else nFish = fish + map[nr][nc].size();

                int nPri = 0;
                switch (cnt) {
                    case 0:
                        nPri = pri + (d+1) * 100;
                        break;
                    case 1:
                        nPri = pri + (d+1) * 10;
                        break;
                    case 2:
                        nPri = pri + (d+1);
                        break;
                }

                queue.offer(new int[] {nr, nc, cnt+1, nFish, nPri, (d+2)%4});
            }
        }

        Collections.sort(next, (o1, o2) -> {
            if(o1[3] == o2[3]) return o1[4] - o2[4];
            return o2[3] - o1[3];
        });
        // 1상 2좌 3하 4우
        int[] temp = next.get(0);

        int prev = temp[4] % 10 -1;
        int pprev = (temp[4] / 10) % 10 -1;
        int ppprev = temp[4] / 100 -1;

        int nr = sr + dr[ppprev];
        int nc = sc + dc[ppprev];
        if(!map[nr][nc].isEmpty()) {
            map[nr][nc] = new ArrayList<>();
            smell[nr][nc] = 3;
        }

        nr = nr + dr[pprev];
        nc = nc + dc[pprev];
        if(!map[nr][nc].isEmpty()) {
            map[nr][nc] = new ArrayList<>();
            smell[nr][nc] = 3;
        }

        nr = nr + dr[prev];
        nc = nc + dc[prev];
        if(!map[nr][nc].isEmpty()) {
            map[nr][nc] = new ArrayList<>();
            smell[nr][nc] = 3;
        }

        sr = temp[0];
        sc = temp[1];

    }

    private static void moveFish() {
        // 모든 물고기 한칸 이동
        //  1-1 상어가 있는 칸 or 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동 못한다.
        //  1-2 이동이 불가능하면 이동가능한 방향이 나올 때 까지 반시계 45도 회전하고 이동가능한 칸이 없으면 이동안함
        //  1-3

        List<Integer>[][] tMap;
        tMap = new ArrayList[5][5];
        init(tMap);

        for(int r = 1; r < 5; r++) {
            for(int c = 1; c < 5; c++) {
                outer : for(int i = 0, size = map[r][c].size(); i < size; i++) {
                    int dir = map[r][c].get(i);
                    for(int d = 0; d < 8; d++) {
                        int nDir = (dir - d + 8) % 8;

                        int nr = r + dr[nDir];
                        int nc = c + dc[nDir];

                        if(nr < 1 || nr > 4 || nc < 1 || nc > 4 || (sr == nr && sc == nc) || smell[nr][nc] != 0) continue;

                        tMap[nr][nc].add(nDir);

                        continue outer;
                    }
                    tMap[r][c].add(map[r][c].get(i));
                }
            }
        }
        copy(tMap, map);
    }

    private static void init(List<Integer>[][] map) {
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
    }


}
