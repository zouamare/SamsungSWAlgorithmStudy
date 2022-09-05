package coding_test.Algo20220901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
    static int N, M;
    static int[][] map;
    static Queue<Iceberg> icebergs;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        icebergs = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0)  icebergs.add(new Iceberg(i, j, map[i][j]));
            }
        }
        startMelt();
    }

    private static void startMelt() {
        int time = 1, cnt;
        boolean[][] visited;
        int[][] newMap;
        while(true){
            if(icebergs.isEmpty()){
                System.out.println(0);
                return;
            }
            // 빙산이 녹는다.
            int size = icebergs.size();
            newMap = makeNewMap();
            for(int i = 0; i < size; i++){
                int zeroCnt = 0;
                Iceberg iceberg = icebergs.poll();
                for(int d = 0; d < 4; d++){
                    int nx = iceberg.x + dx[d];
                    int ny = iceberg.y + dy[d];
                    if(!isValid(nx, ny) || map[nx][ny] != 0)    continue;
                    zeroCnt++;
                }
                // 바다를 접하는 면의 개수를 구했다.
                if(iceberg.height - zeroCnt > 0){
                    newMap[iceberg.x][iceberg.y] -= zeroCnt;
                    iceberg.height -= zeroCnt;
                    icebergs.add(iceberg);
                }else{  // 녹아서 바다가 되었다.
                    newMap[iceberg.x][iceberg.y] = 0;
                }
            }
            map = newMap;
            // 빙산이 연결되어 있는지 체크한다.
            // Queue를 이용하여 DFS를 진행한다. DFS가 1회이상 실행될 경우, 빙산이 분리되었다고 판단함
            cnt = 0;
            visited = new boolean[N][M];
            for(Iceberg iceberg : icebergs){
                if(visited[iceberg.x][iceberg.y])   continue;
                DFS(iceberg.x, iceberg.y, visited);
                cnt++;
            }
            if(cnt > 1) break;
            time++;
        }
        System.out.println(time);
    }

    private static int[][] makeNewMap() {
        int[][] newMap = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    private static void DFS(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(!isValid(nx, ny) || map[nx][ny] == 0 || visited[nx][ny])    continue;
            DFS(nx, ny, visited);
        }
    }

    private static boolean isValid(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Iceberg{
        int x, y, height;

        public Iceberg(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
