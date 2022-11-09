package coding_test.Algo20220926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
*   [중요 로직]
*
*   1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
*   2. 온도가 조절됨 -> 사방 탐색
*   3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
*
*   [신경써야 할 것]
*   벽의 위치, 온도 조절
*
*   진짜 위의 둘 때문에 시간 엄청 잡아 먹었다.
*
*   가능하면 그림을 그려가면서 풀 것. 머리로만 풀려니까 잘 안풀린다.
*   그림으로 규칙을 찾아가면서 풀어야겠다.
*
*   그리고 온도 조절.. ㅎㅎ.. 틀리지 않았다고 생각했는데,
*   '동시에' 일어나게 되므로 한 방향으로 온도를 나눠줘도 다른 방향으로는 초기 온도로 비교를 했어야 했는데,
*   그 부분 처리가 감소한 값으로 온도를 나눠주는 방식으로 구현이 되어 있었다.
*
*   + 벽이 다른 것은 같은 자리를 차지할 수 없다.
*   0번 벽 정보와 1번 벽 정보를 함께 저장하면 안됨.....
* */

public class BOJ_23289_온풍기안녕 {
    static int R, C, K;
    static int[][] map;
    static boolean[][][] wall;
    static List<Heater> heaters;
    static List<Zone> zones;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(turnOnHeater());
    }

    private static int turnOnHeater() {
        int chocolateCnt = 0;
        while(allZoneIsNotValid()){
            if(chocolateCnt == 101) break;
            // 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
            startHeatingOneTime();
            // 2. 온도가 조절됨
            controlTemperature();
            // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            decreaseTemperature();
            // 4. 초콜렛을 하나 먹는다.
            chocolateCnt++;
        }
        return chocolateCnt;
    }

    private static void startHeatingOneTime() {
        for(Heater heater : heaters){
            boolean[][] visited = new boolean[R][C];
            letsHeating(heater.x + dx[heater.dir], heater.y + dy[heater.dir], heater.dir, 5, visited);
        }
    }

    private static void letsHeating(int x, int y, int dir, int value, boolean[][] visited) {
        visited[x][y] = true;
        map[x][y] += value;
        if(value == 1)  return;
        if(dx[dir] == 0){
            if(isValid(x-1, y+dy[dir]) && !visited[x-1][y+dy[dir]] && !isStuck(x, y, 3) && !isStuck(x-1, y, dir))
                letsHeating(x-1, y + dy[dir], dir, value - 1, visited);

            if(isValid(x, y+dy[dir]) && !visited[x][y+dy[dir]] && !isStuck(x, y, dir))
                letsHeating(x, y + dy[dir], dir, value - 1, visited);

            if(isValid(x+1, y+dy[dir]) && !visited[x+1][y+dy[dir]] && !isStuck(x, y, 1) && !isStuck(x+1, y, dir))
                letsHeating(x+1, y + dy[dir], dir, value - 1, visited);
        }else{
            if(isValid(x+dx[dir], y-1) && !visited[x+dx[dir]][y-1] && !isStuck(x, y, 2) && !isStuck(x, y-1, dir))
                letsHeating(x + dx[dir], y - 1, dir, value - 1, visited);

            if(isValid(x+dx[dir], y) && !visited[x+dx[dir]][y] && !isStuck(x, y, dir))
                letsHeating(x + dx[dir], y, dir, value - 1, visited);

            if(isValid(x+dx[dir], y+1) && !visited[x+dx[dir]][y+1] && !isStuck(x, y, 0) && !isStuck(x, y+1, dir))
                letsHeating(x + dx[dir], y + 1, dir, value - 1, visited);
        }
    }

    private static void controlTemperature() {
        int[][] adder = new int[R][C];  // 추가되는 값을 저장할 배열

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int d = 0; d < 2; d++) {   // 우측이랑 하단만 진행
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if(isValid(nx, ny) && !isStuck(i, j, d)){
                        int gap = Math.abs(map[i][j] - map[nx][ny]);
                        if(map[i][j] > map[nx][ny]){
                            adder[i][j] -= gap/4;
                            adder[nx][ny] += gap/4;
                        }
                        else if(map[i][j] < map[nx][ny]){
                            adder[nx][ny] -= gap/4;
                            adder[i][j] += gap/4;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += adder[i][j];
            }
        }
    }

    private static boolean isStuck(int i, int j, int d) {
        switch (d){
            case 0:
                if(wall[i][j][1]) return true;
                break;
            case 1:
                if(wall[i+1][j][0])   return true;
                break;
            case 2:
                if(wall[i][j-1][1])   return true;
                break;
            case 3:
                if(wall[i][j][0]) return true;
                break;
        }
        return false;
    }

    private static void decreaseTemperature() {
        for (int i = 0; i < R; i++) {
            if(map[i][0] > 0)   map[i][0]--;
            if(map[i][C-1] > 0) map[i][C-1]--;
        }

        for (int i = 1; i < C - 1; i++) {
            if(map[0][i] > 0)   map[0][i]--;
            if(map[R-1][i] > 0) map[R-1][i]--;
        }
    }

    private static boolean allZoneIsNotValid() {
        for(Zone zone : zones){
            if(map[zone.x][zone.y] < K) return true;
        }
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());   // 모든 칸의 온도 기준
        map = new int[R][C];
        heaters = new ArrayList<>();
        zones = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                switch (Integer.parseInt(st.nextToken())){
                    case 1:
                        heaters.add(new Heater(i, j, 0));
                        break;
                    case 2:
                        heaters.add(new Heater(i, j, 2));
                        break;
                    case 3:
                        heaters.add(new Heater(i, j, 3));
                        break;
                    case 4:
                        heaters.add(new Heater(i, j, 1));
                        break;
                    case 5:
                        zones.add(new Zone(i, j));
                        break;
                }
            }
        }

        wall = new boolean[R][C][2];
        int wallCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < wallCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            if(Integer.parseInt(st.nextToken()) == 0){
                wall[x][y][0] = true;
            }else{
                wall[x][y][1] = true;
            }
        }
    }

    private static boolean isValid(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static class Heater{
        int x, y, dir;

        public Heater(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static class Zone{
        int x, y;

        public Zone(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
