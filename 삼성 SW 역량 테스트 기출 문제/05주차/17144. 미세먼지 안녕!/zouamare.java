import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int T;
    static int[][] map;
    static int fineDustCnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int acx;
    static int acy = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int r = 0; r < R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] > 0)
                    fineDustCnt += map[r][c];
                else if(map[r][c] == -1 && acx == 0){
                    acx = r;    // 공기 청정기 윗부분 x 좌표
                }
            }
        }
        turnOnAirCleaner();
        System.out.println(fineDustCnt);
    }

    private static void turnOnAirCleaner() {
        for(int i = 0; i < T; i++){
            // 미세먼지가 확산 된다.
            spreadFineDust();
            runAirCleaner();
        }
    }
    private static void spreadFineDust() {
        int[][] adder = new int[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] < 1)   continue;
                // 주변 네 방향으로 확산 됨.
                // 확산되는 양은 5로 나눈 값
                // 남은 미세먼지 양은 원래 값 - 5로 나눈 값 * 확산된 방향의 개수
                int cnt = 0;
                int amount = map[i][j] / 5;
                for(int d = 0; d < 4; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1)  continue;
                    adder[nx][ny] += amount;
                    cnt++;
                }
                map[i][j] = map[i][j] - amount * cnt;
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                map[i][j] += adder[i][j];
            }
        }
    }

    private static void runAirCleaner() {
        // 공기 청정기에서 바람이 나옴
        // 위쪽 공기청정기의 바람은 반 시계 방향으로 순환한다.
        int x = acx, y = acy + 1;
        int before = 0, now = 0;
        while(y < C) {
            now = map[x][y];
            map[x][y] = before;
            y++;
            before = now;
        }
        y--;
        x--;
        while(x >= 0){
            now = map[x][y];
            map[x][y] = before;
            x--;
            before = now;
        }
        x++;
        y--;
        while(y >= 0){
            now = map[x][y];
            map[x][y] = before;
            y--;
            before = now;
        }
        y++;
        x++;
        while(map[x][y] != -1){
            now = map[x][y];
            map[x][y] = before;
            x++;
            before = now;
        }
        // 남은 now는 버려지는 값
        fineDustCnt -= now;
        // 아래쪽 공기청정기의 바람은 시계 방향으로 순환한다.
        x = acx + 1;
        y = acy + 1;
        before = 0;
        while(y < C){
            now = map[x][y];
            map[x][y] = before;
            y++;
            before = now;
        }
        y--;
        x++;
        while(x < R){
            now = map[x][y];
            map[x][y] = before;
            x++;
            before = now;
        }
        x--;
        y--;
        while(y >= 0){
            now = map[x][y];
            map[x][y] = before;
            y--;
            before = now;
        }
        y++;
        x--;
        while(map[x][y] != -1){
            now = map[x][y];
            map[x][y] = before;
            x--;
            before = now;
        }
        fineDustCnt -= now;
        // 미세먼지가 바람의 방향대로 모두 한칸씩 이동한다.
    }

}