package coding_test.Algo2022년_10월.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
* 처음에 구현할 때 당연히 지나간 곳은 다시 안가겠지라고 생각을 했는데, 생각해보니 가게되는 경우가 있었다.
* 그런데 확실하지 않아서 고려하지 않았으나 역시였다. 문제에서 지나간 곳은 다시 고려하지 않는다 라는 말이 없었기 때문!!
* 문제의 의도를 내 마음대로 해석하면 안되고 문제를 그대로 이해해야 한다!
*
* 구현시 중요했던 부분
* - 사전 순으로 진행
* - 냄새 체크
* - 물고기 이동
* */


public class BOJ_23290_마법사상어와복제 {
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static List<Integer>[][] map;
    static int[][] smellMap;

    static char[] route;
    static Point[] points;
    static int x, y, deadFishCnt, nextX, nextY;
    private final static int MAP_SIZE = 5;
    public static void main(String[] args) throws IOException {
        doCopyMagic(init());
        printFishCnt();
    }

    private static void printFishCnt() {
        int sum = 0;
        for (int i = 1; i < MAP_SIZE; i++) {
            for (int j = 1; j < MAP_SIZE; j++) {
                sum += map[i][j].size();
            }
        }
        System.out.println(sum);
    }

    private static void doCopyMagic(int T) {
        for (int t = 0; t < T; t++) {
            // 1. 복제 마법 시전
            List<Integer>[][] copyMap = copyMap();
            // 2. 물고기가 이동
            moveFishes();
            // 3. 상어가 이동
            deadFishCnt = 0;
            route = new char[]{(char) (9 + '0'), (char) (9 + '0'), (char) (9 + '0')};
            moveShark(x, y,0, new char[3], new Point[3], 0);
            for (Point point : points) {
                if(map[point.x][point.y].size() > 0)   smellMap[point.x][point.y] = t + 2;
                map[point.x][point.y].clear();
            }
            x = nextX;
            y = nextY;
            // 4. 냄새 사라짐
            DisapperSmell(t);
            // 5. 복제 마법 완료
            completeCopyMagic(copyMap);
        }
    }

    private static void moveFishes() {
        List<Integer>[][] newMap = makeMap();
        for (int i = 1; i < MAP_SIZE; i++) {
            for (int j = 1; j < MAP_SIZE; j++) {
                for (int dir : map[i][j]) {
                    boolean flag = false;
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[(dir - k + 8) % 8];
                        int ny = j + dy[(dir - k + 8) % 8];
                        if(isVaild(nx, ny) && smellMap[nx][ny] == 0 && (nx != x || ny != y)){
                            newMap[nx][ny].add((dir - k + 8) % 8);
                            flag = true;    // 이동했다.
                            break;
                        }
                    }
                    if(!flag)   newMap[i][j].add(dir);
                }
            }
        }
        map = newMap;
    }

    private static void moveShark(int tx, int ty, int depth, char[] tempRoute, Point[] tempPoints, int tempDeadFishCnt) {
        if(depth == 3){
            if(tempDeadFishCnt > deadFishCnt || (tempDeadFishCnt == deadFishCnt && IsFastInDict(tempRoute))){
                deadFishCnt = tempDeadFishCnt;
                route = new char[]{tempRoute[0], tempRoute[1], tempRoute[2]};
                nextX = tx;
                nextY = ty;
                points[0] = new Point(tempPoints[0].x, tempPoints[0].y);
                points[1] = new Point(tempPoints[1].x, tempPoints[1].y);
                points[2] = new Point(tempPoints[2].x, tempPoints[2].y);
            }
            return;
        }
        for (int d = 0; d < 8; d+=2) {
            int nx = tx + dx[d];
            int ny = ty + dy[d];
            if(isVaild(nx, ny)){
                switch (d){
                    case 0:
                        tempRoute[depth] = (char) (2 + '0');
                        break;
                    case 2:
                        tempRoute[depth] = (char) (1 + '0');
                        break;
                    case 4:
                        tempRoute[depth] = (char) (4 + '0');
                        break;
                    case 6:
                        tempRoute[depth] = (char) (3 + '0');
                        break;
                }
                tempPoints[depth] = new Point(nx, ny);
                if(depth == 2 && tempPoints[depth-2].x == tempPoints[depth].x && tempPoints[depth-2].y == tempPoints[depth].y)
                    moveShark(nx, ny, depth+1, tempRoute, tempPoints, tempDeadFishCnt);
                else
                    moveShark(nx, ny, depth+1, tempRoute, tempPoints, tempDeadFishCnt + map[nx][ny].size());
            }
        }
    }

    private static boolean IsFastInDict(char[] tempRoute) {
        if(Integer.compare(Integer.parseInt(String.valueOf(route)), Integer.parseInt(String.valueOf(tempRoute))) == 1)   return true;
        return false;
    }

    private static void completeCopyMagic(List<Integer>[][] copyMap) {
        for (int i = 1; i < MAP_SIZE; i++) {
            for (int j = 1; j < MAP_SIZE; j++) {
                for (int dir : copyMap[i][j]) {
                    map[i][j].add(dir);
                }
            }
        }
    }

    private static void DisapperSmell(int t) {
        if(t == 0)  return;
        for (int i = 1; i < MAP_SIZE; i++) {
            for (int j = 1; j < MAP_SIZE; j++) {
                if(smellMap[i][j] == t){
                    smellMap[i][j] = 0;
                }
            }
        }
    }

    private static List<Integer>[][] copyMap() {
        List<Integer>[][] copyMap = new ArrayList[MAP_SIZE][MAP_SIZE];
        for (int i = 1; i < MAP_SIZE; i++) {
            for (int j = 1; j < MAP_SIZE; j++) {
                copyMap[i][j] = new ArrayList<>();
                for (int dir : map[i][j]) {
                    copyMap[i][j].add(dir);
                }
            }
        }
        return copyMap;
    }


    private static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        map = makeMap();
        smellMap = new int[MAP_SIZE][MAP_SIZE];
        points = new Point[3];

        // 물고기 위치 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()) - 1);
        }

        // 상어 위치 저장
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        route = new char[3];

        return S;
    }

    private static List<Integer>[][] makeMap(){
        List<Integer>[][] tempMap = new ArrayList[MAP_SIZE][MAP_SIZE];

        for (int i = 1; i < MAP_SIZE; i++) {
            for (int j = 1; j < MAP_SIZE; j++) {
                tempMap[i][j] = new ArrayList<>();
            }
        }
        return tempMap;
    }

    private static boolean isVaild(int x, int y){
        return x > 0 && x < MAP_SIZE && y > 0 && y < MAP_SIZE;
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
